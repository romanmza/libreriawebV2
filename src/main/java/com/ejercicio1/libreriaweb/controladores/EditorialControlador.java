package com.ejercicio1.libreriaweb.controladores;

import com.ejercicio1.libreriaweb.entidades.Editorial;
import com.ejercicio1.libreriaweb.servicios.EditorialServicio;
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
@RequestMapping("/editorial")

public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialservicio;

    @GetMapping("/registro") //localhost:8080/editorial/registro
    public String formulario() {

        return "form-editorial";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo,
            @RequestParam String nombre
    ) {

        try {

            editorialservicio.creareditorial(nombre);

            modelo.put("exito", "Registro exitoso");

            List<Editorial> listaeditoriales = editorialservicio.listarEditoriales();
            modelo.addAttribute("editoriales", listaeditoriales);

            return "list-editorial";

        } catch (Exception e) {

            modelo.put("error", "Falto algun dato");
            return "form-editorial";
        }
    }

    //controlador para listar editoriales
    @GetMapping("/lista") //localhost:8080/editorial/lista

    public String lista(ModelMap modelo) {
        List<Editorial> listaEditoriales = editorialservicio.listarEditoriales();
        modelo.addAttribute("editoriales", listaEditoriales);

        return "list-editorial";
    }

    //controlador para dar de baja una editorial
    @GetMapping("/baja/{id}")
    public String baja(ModelMap modelo, @PathVariable String id) {
        try {
            editorialservicio.baja(id);
            List<Editorial> listaEditoriales = editorialservicio.listarEditoriales();
            modelo.addAttribute("editoriales", listaEditoriales);
            modelo.put("exito", "Modificación exitosa.");
            return "list-editorial";

        } catch (Exception e) {
            modelo.put("error", "Error al modificar atributo.");
            return "form-editorial";
        }

    }
    
    //controlador para dar de alta una editorial
    @GetMapping("/alta/{id}")
    public String alta(ModelMap modelo, @PathVariable String id) {
        try {
            editorialservicio.alta(id);
            List<Editorial> listaEditoriales = editorialservicio.listarEditoriales();
            modelo.addAttribute("editoriales", listaEditoriales);
            modelo.put("exito", "Modificación exitosa.");
            return "list-editorial";

        } catch (Exception e) {
            modelo.put("error", "Error al modificar atributo.");
            return "form-editorial";
        }

    }

   //controlador para modificar una editorial existente
    @GetMapping("/modificar/{id}") //PATHVARIABLE
    public String modificar(ModelMap modelo, @PathVariable String id) {

        //traemos todos los atributos de la editorial según el ID elegido
        modelo.addAttribute("editorial", editorialservicio.buscarEditorialPorId(id));

        return "form-editorial-modif";
    }

    @PostMapping("/modificar/{id}")
    public String modificarEditorial(ModelMap modelo,
            @RequestParam String id,
            @RequestParam String nombre
    ) {
        try {
            editorialservicio.modificareditorial(id, nombre);
            //una vez modificado, volvemos a mostrar la lista de editoriales

            List<Editorial> listaeditoriales = editorialservicio.listarEditoriales();
            modelo.addAttribute("editoriales", listaeditoriales);

            modelo.put("exito", "Modificación exitosa.");
            return "list-editorial";

        } catch (Exception e) {
            modelo.put("error", "Falto algun dato");
            return "list-editorial";
        }
    }
    
    
    
    
}
