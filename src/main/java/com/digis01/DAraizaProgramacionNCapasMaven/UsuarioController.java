package com.digis01.DAraizaProgramacionNCapasMaven;


import com.digis01.DAraizaProgramacionNCapasMaven.ML.Colonia;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Direccion;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.ErroresArchivo;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Pais;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario;


import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Rol;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
 

        private static String rutaBase = "http://localhost:8080";


    @GetMapping
    public String Usuarios(Model model) {

        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<Result<Usuario>> responseEntity = restTemplate.exchange(rutaBase + "/demo/api", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<Result<Usuario>>(){});
        
        if(responseEntity.getStatusCode().value()==200){
            Result result = responseEntity.getBody();
            model.addAttribute("usuarios", result.objects );
            model.addAttribute("usuario", new Usuario());
            
        }
        
        return ("GetAll");
    }
    
    @GetMapping ("{idusuario}")
    public String GetById(@PathVariable ("idusuario") int idusuario, Model model){
        
        RestTemplate restTemplate = new RestTemplate();
        try{
        ResponseEntity<Result<Usuario>> responseEntity = restTemplate.exchange(rutaBase + "/demo/api/{idusuario}", HttpMethod.GET, null, new ParameterizedTypeReference<Result<Usuario>>(){}, idusuario);
        
        if(responseEntity.getStatusCode().value()==200){
            Result result  =responseEntity.getBody();
            model.addAttribute("usuario", result.object);
            
            model.addAttribute("direccion", new Direccion());
                //roles
                ResponseEntity<Result> responseRoles = restTemplate.exchange(rutaBase + "/api/rol",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Result>() {
                });
//                model.addAttribute("roles", responseRoles.getBody().objects);
//
//                //roles
//                ResponseEntity<Result> responsePaises = restTemplate.exchange(rutaBase + "/api/pais",
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<Result>() {
//                });
//                model.addAttribute("paises", responsePaises.getBody().objects);
//
//                Result<Usuario> usuario = responseEntity.getBody();
//
//                int idPais = usuario.object.Direcciones.get(0).colonia.municipio.estado.pais.getIdPais();
//                int idEstado = usuario.object.Direcciones.get(0).colonia.municipio.estado.getIdEstado();
//                int idMunicipio = usuario.object.Direcciones.get(0).colonia.municipio.getIdMunicipio();
//                int idColonia = usuario.object.Direcciones.get(0).colonia.getIdColonia();
//
//                if (idEstado != 0) {
//                    //guardo el valor
//                    model.addAttribute("estados", restTemplate.getForObject(rutaBase + "/api/usuario/Estado/{identificador}}", List.class, idPais));
//                    if (idMunicipio != 0) {
//                        //guardo el valor
//                        model.addAttribute("municipios", restTemplate.getForObject(rutaBase + "/api/usuario/Municipio/{identificador}", List.class, idEstado));
//                        if (idColonia != 0) {
//                            //guardo el valor
//                            model.addAttribute("colonias", restTemplate.getForObject(rutaBase + "/api/usuario/Colonia/{identificador}", List.class, idMunicipio));
//
//                        }
//                    }
//
//                }
            
        }
        }catch(Exception ex){
            System.out.println("Error en details: " + ex.getLocalizedMessage());
        }
            
            return "Details";
    }
    
    @GetMapping("formulario")
    public String Formulario(Model model){
        Result result = new Result();
        
        RestTemplate restTemplate = new RestTemplate();
        
         model.addAttribute("usuario", new Usuario());
         
         

        try {
            //paises
            ResponseEntity<Result> responsePaises = restTemplate.exchange(rutaBase + "/api/pais",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Result>() {
            });
            model.addAttribute("paises", responsePaises.getBody().objects);

            //roles
            ResponseEntity<Result> responseRoles = restTemplate.exchange(rutaBase + "/api/rol",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Result>() {
            });
            model.addAttribute("roles", responseRoles.getBody().objects);
        } catch (Exception ex) {
            System.err.println("Error en RestTemplate: " + ex.getMessage());
            model.addAttribute("error", "Error de conexión: " + ex.getLocalizedMessage());
        }

        return "Formulario";
    }
    
        @PostMapping("form")                                                                                                        //del model vienen todas las modificaciones
    public String Accion(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, @RequestParam("imagenFile") MultipartFile imagenFile, Model model) {
        Result result = new Result();
        RestTemplate restTemplate = new RestTemplate();
        try {

            if (bindingResult.hasErrors()) {
                model.addAttribute("usuario", usuario);
                model.addAttribute("paises", restTemplate.getForObject(rutaBase + "/api/usuario/Pais", List.class));
                model.addAttribute("roles", restTemplate.getForObject(rutaBase + "/api/usuario/Rol", List.class));

                //guardo en variables los id
                int idPais = usuario.Direcciones.get(0).colonia.municipio.estado.pais.getIdPais();
                int idEstado = usuario.Direcciones.get(0).colonia.municipio.estado.getIdEstado();
                int idMunicipio = usuario.Direcciones.get(0).colonia.municipio.getIdMunicipio();
                int idColonia = usuario.Direcciones.get(0).colonia.getIdColonia();

                if (idEstado != 0) {
                    //guardo el valor
                    model.addAttribute("estados", restTemplate.getForObject(rutaBase + "/api/usuario/Estado?identificador=" + idEstado, List.class));
                    if (idMunicipio != 0) {
                        //guardo el valor
                        model.addAttribute("municipios", restTemplate.getForObject(rutaBase + "/api/usuario/Municipio?identificador=" + idMunicipio, List.class));
                        if (idColonia != 0) {
                            //guardo el valor
                            model.addAttribute("colonias", restTemplate.getForObject(rutaBase + "/api/usuario/Colonia?identificador=" + idColonia, List.class));

                        }
                    }

                }
                return "form";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            usuario.setImagen(null);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            body.add("datos", usuario);

            if (!imagenFile.isEmpty()) {
                body.add("imagen", imagenFile.getResource());
            }

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<Result> response = restTemplate.exchange(rutaBase + "/api/usuario",
                    HttpMethod.POST,
                    requestEntity,
                    Result.class);

            result.correct = true;
            if (result.correct) {
                return "redirect:/usuario";
            } else {
                model.addAttribute("error", response.getBody().errorMessage);
                return "form";
            }

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return "redirect:/usuario";
    }
    


    @GetMapping("/delete/{idusuario}")
    public String DeteleUsuario(@PathVariable("idusuario") int identificador, RedirectAttributes redirectAttributes) {
        Result result = new Result();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Result> responseBorrar = restTemplate.exchange(rutaBase + "/demo/api/delete/{idusuario}",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Result>() {
        },
                identificador);

        result.correct = true;

        if (result.correct) {
            redirectAttributes.addFlashAttribute("mensaje", "Borrado exitosamente");
            return "redirect:/usuario";
        } else {
            return "redirect:/usuario";
        }

    }
    
    
    
    
    
    
}


