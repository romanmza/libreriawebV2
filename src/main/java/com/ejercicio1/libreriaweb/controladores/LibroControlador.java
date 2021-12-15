package com.ejercicio1.libreriaweb.controladores;

import com.ejercicio1.libreriaweb.entidades.Autor;
import com.ejercicio1.libreriaweb.entidades.Editorial;
import com.ejercicio1.libreriaweb.entidades.Libro;
import com.ejercicio1.libreriaweb.servicios.AutorServicio;
import com.ejercicio1.libreriaweb.servicios.EditorialServicio;

import com.ejercicio1.libreriaweb.servicios.LibroServicio;
import java.util.List;
import org.hibernate.cfg.annotations.ListBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroservicio;
    @Autowired
    private AutorServicio autorservicio;    
    @Autowired
    private EditorialServicio editorialervicio;

    @GetMapping("/registro") //localhost:8080/libro/registro
    public String formulario(ModelMap modelo) {
        
        //traemos todos los autores para el comando select
        List <Autor> listaautores = autorservicio.listarAutores();        
        modelo.addAttribute("autorSelect", listaautores);  
        //traemos todas las editoriales para el comando select
        List <Editorial> listaeditoriales = editorialervicio.listarEditoriales();
        modelo.addAttribute("editorialSelect", listaeditoriales); 

        return "form-libro";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo,
            @RequestParam Long isbn,
            @RequestParam String titulo,
            @RequestParam Integer anio,
            @RequestParam Integer ejemplares,
            @RequestParam String nombreautor,
            @RequestParam String nombreeditorial
    ) {

        try {

            libroservicio.crearlibro(isbn, titulo, anio, ejemplares, nombreautor, nombreeditorial);

            modelo.put("exito", "Registro exitoso");

            return "form-libro";

        } catch (Exception e) {

            modelo.put("error", "Falto algun dato");
            return "form-libro";
        }
    }

    //controlador para listar libros
    @GetMapping("/lista") //localhost:8080/libro/lista

    public String lista(ModelMap modelo) {
        List<Libro> listaLibros = libroservicio.listarLibros();
        modelo.addAttribute("libros", listaLibros);
        return "list-libro";
    }
    
    
    
//    @GetMapping("/") //localhost:8080/autor/lista
//
//    public String autoresSelect(ModelMap modelo) {
//        List<Autor> listaAutores = autorservicio.listarAutores();
//        modelo.addAttribute("autoresSelect", listaAutores);
//
//        return "lista";
//    }
    


////Donde se envia el valor seleccionado
//    @PostMapping("/search")
//    public String departamentosPost(@ModelAttribute("departamentos") Departamento departamentos) {
//        LOGGER.info("Has elegido: "+departamentos);
//        return "search";
//    }
//    
    
    
    
    

    //controlador para dar de baja un libro
    @GetMapping("/baja/{id}")
    public String baja(ModelMap modelo, @PathVariable String id) {
        try {
            libroservicio.baja(id);
            List<Libro> listaLibros = libroservicio.listarLibros();
            modelo.addAttribute("libros", listaLibros);
            modelo.put("exito", "Modificación exitosa.");
            return "list-libro";

        } catch (Exception e) {
            modelo.put("error", "Error al modificar atributo.");
            return "form-libro";
        }

    }

    //controlador para dar de alta un libro
    @GetMapping("/alta/{id}")
    public String alta(ModelMap modelo, @PathVariable String id) {
        try {
            libroservicio.alta(id);
            List<Libro> listaLibros = libroservicio.listarLibros();
            modelo.addAttribute("libros", listaLibros);
            modelo.put("exito", "Modificación exitosa.");
            return "list-libro";

        } catch (Exception e) {
            modelo.put("error", "Error al modificar atributo.");
            return "form-libro";
        }

    }

    //controlador para modificar un libro existente
    @GetMapping("/modificar/{id}") //PATHVARIABLE
    public String modificar(ModelMap modelo, @PathVariable String id) {

        //traemos todos los atributos del libro según el ID elegido
        modelo.addAttribute("libro", libroservicio.buscarlibroporid(id));
  
        //traemos todos los autores para el comando select
        List <Autor> listaautores = autorservicio.listarAutores();        
        modelo.addAttribute("autorSelect", listaautores);  
        //traemos todas las editoriales para el comando select
        List <Editorial> listaeditoriales = editorialervicio.listarEditoriales();
        modelo.addAttribute("editorialSelect", listaeditoriales); 


        return "form-libro-modif";
    }
    
    
    
    

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo,
            @RequestParam String id,
            @RequestParam Long isbn,
            @RequestParam String titulo,
            @RequestParam Integer anio,
            @RequestParam Integer ejemplares,
            @RequestParam String nombreautor,
            @RequestParam String nombreeditorial
    ) {
        try {
            libroservicio.modificarlibro(id, isbn, titulo, anio, ejemplares, nombreautor, nombreeditorial);
            //una vez modificado, volvemos a mostrar la lista de libros
            List<Libro> listaLibros = libroservicio.listarLibros();
            modelo.addAttribute("libros", listaLibros);
            modelo.put("exito", "Modificación exitosa.");
            return "list-libro";
            
        } catch (Exception e) {
            modelo.put("error", "Falto algun dato");
            return "list-libro";
        }
    }

}
