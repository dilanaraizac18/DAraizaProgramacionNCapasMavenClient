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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        }
        return ("GetAll");
    }
    
    @GetMapping ("{idusuario}")
    public String GetById(@PathVariable ("idusuario") int idusuario, Model model){
        
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<Result> responseEntity = restTemplate.exchange(rutaBase + "/demo/api", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<Result>(){});
        
        if(responseEntity.getStatusCode().value()==200){
            Result result  =responseEntity.getBody();
            model.addAttribute("usuario", result);
        }
            
            return "GetAll";
    }
    
    
    
    
}


