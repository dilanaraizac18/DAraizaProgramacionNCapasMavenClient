/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DAraizaProgramacionNCapasMaven;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    
    private static String RutaBase = "http://localhost:8080";
    @GetMapping
    public String Login(@RequestParam(value = "error", required = false) String error,  Model model){
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<>
        
        
        
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        
        
        return "Login";
    }
}
