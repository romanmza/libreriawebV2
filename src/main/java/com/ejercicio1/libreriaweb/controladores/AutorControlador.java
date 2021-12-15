package com.ejercicio1.libreriaweb.controladores;

import com.ejercicio1.libreriaweb.entidades.Autor;
import com.ejercicio1.libreriaweb.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")

public class AutorControlador {

    @Autowired
    private AutorServicio autorservicio;

    @GetMapping("/registro") //localhost:8080/autor/registro
    public String formulario() {

        return "form-autor";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo,
            @RequestParam String nombre
    ) {

        try {

            autorservicio.crearautor(nombre);

            modelo.put("exito", "Registro exitoso");

            List<Autor> listaautores = autorservicio.listarAutores();
            modelo.addAttribute("autores", listaautores);

            return "list-autor";

        } catch (Exception e) {

            modelo.put("error", "Falto algun dato");
            return "form-autor";
        }
    }

    //controlador para listar autores
    @GetMapping("/lista") //localhost:8080/autor/lista

    public String lista(ModelMap modelo) {
        List<Autor> listaAutores = autorservicio.listarAutores();
        modelo.addAttribute("autores", listaAutores);

        return "list-autor";
    }

    //controlador para dar de baja un autor
    @GetMapping("/baja/{id}")
    public String baja(ModelMap modelo, @PathVariable String id) {
        try {
            autorservicio.baja(id);
            List<Autor> listaAutores = autorservicio.listarAutores();
            modelo.addAttribute("autores", listaAutores);
            modelo.put("exito", "Modificación exitosa.");
            return "list-autor";

        } catch (Exception e) {
            modelo.put("error", "Error al modificar atributo.");
            return "form-autor";
        }

    }

    //controlador para dar de alta un autor
    @GetMapping("/alta/{id}")
    public String alta(ModelMap modelo, @PathVariable String id) {
        try {
            autorservicio.alta(id);
            List<Autor> listaAutores = autorservicio.listarAutores();
            modelo.addAttribute("autores", listaAutores);
            modelo.put("exito", "Modificación exitosa.");
            return "list-autor";

        } catch (Exception e) {
            modelo.put("error", "Error al modificar atributo.");
            return "form-autor";
        }

    }

    //controlador para modificar un autor existente
    @GetMapping("/modificar/{id}") //PATHVARIABLE
    public String modificar(ModelMap modelo, @PathVariable String id) {

        //traemos todos los atributos del autor según el ID elegido
        modelo.addAttribute("autor", autorservicio.buscarAutorPorId(id));

        return "form-autor-modif";
    }

    @PostMapping("/modificar/{id}")
    public String modificarAutor(ModelMap modelo,
            @RequestParam String id,
            @RequestParam String nombre
    ) {
        try {
            autorservicio.modificarAutor(id, nombre);
            //una vez modificado, volvemos a mostrar la lista de autores

            List<Autor> listaAutores = autorservicio.listarAutores();
            modelo.addAttribute("autores", listaAutores);

            modelo.put("exito", "Modificación exitosa.");
            return "list-autor";

        } catch (Exception e) {
            modelo.put("error", "Falto algun dato");
            return "list-autor";
        }
    }

}
