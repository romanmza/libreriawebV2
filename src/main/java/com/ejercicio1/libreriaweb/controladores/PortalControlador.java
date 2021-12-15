package com.ejercicio1.libreriaweb.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //le indica al framework de spring que esta clase es del tipo controlador, y donde
//spring vendrá primero a buscar los templates
@RequestMapping("/") //va a escuchar la url que va a escuchar el controlador, en este caso es el root, o sea a partir de la barra
public class PortalControlador {

//    @GetMapping("/")  
//    public String index() {
//        return "index.html";
//    }
//    @GetMapping("/") //esta anotación estoy diciendo que este método, cuando encuentre la barra, me renderice lo que voy a retornar 
//            //en este caso el index.html
//    String home(ModelMap model) {
//        String nombre = "Roman";
//        model.addAttribute("nombre", nombre);
//        return "index.html";
//    }
    @GetMapping("/")//localhost:8080/
    public String index() {

        return "index";
    }
}
