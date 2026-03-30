/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DAraizaProgramacionNCapasMaven;

import com.digis01.DAraizaProgramacionNCapasMaven.ML.Result;
import com.digis01.DAraizaProgramacionNCapasMaven.ML.Usuario;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import static org.slf4j.helpers.Reporter.error;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    
    
    private static String RutaBase = "http://localhost:8080/auth/login";
    
    @GetMapping
    public String Login (@RequestParam(value = "error", required = false) String error, Model model) {
         model.addAttribute("usuario", new Usuario());
        if (error != null) {
            model.addAttribute("errorlogin", error);
        }

        return "Login";

    }
    
    
    
    @PostMapping
    public String PostLogin(@ModelAttribute("username")  String username, @ModelAttribute("password") String password, HttpSession httpSession){
        Result result = new Result();
                
        try{
                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders httpHeader = new HttpHeaders();
                    httpHeader.setContentType(MediaType.APPLICATION_JSON);
                    
                    Map<String, Object> userbody =  new HashMap<>();
                    userbody.put("username", username);
                    userbody.put("password", password);
                    
                    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(userbody, httpHeader);

            ResponseEntity<Result> responseLogin = restTemplate.exchange(RutaBase,
                    HttpMethod.POST,
                    requestEntity,
                    Result.class);

            if (responseLogin.getBody() != null) {
                Result resultR = responseLogin.getBody();
                
                String token = (String) resultR.object;
                httpSession.setAttribute("token", token);

                if (resultR.correct) {
                    return "redirect:/usuario";
                }
                return "/login";
            }
                    
                    
                    
                    
            
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
         
        return "GetAll";
    }
}
