package com.ejercicio1.libreriaweb.servicios;

import com.ejercicio1.libreriaweb.entidades.Editorial;
import com.ejercicio1.libreriaweb.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejercicio1.libreriaweb.errores.ErrorServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialrepositorio;

    @Transactional
    public void creareditorial(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
        }
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        //System.out.println("Flag 3: editorial.Nombre: " + editorial.getNombre());
        editorialrepositorio.save(editorial);

    }

    //Método para listar editoriales:
    public List<Editorial> listarEditoriales() {
        List<Editorial> editoriales = editorialrepositorio.listareditoriales();
        return editoriales;
    }

    //Método para buscar editorial por nombre
    public Editorial buscarEditorialporNombre(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
        }
        Editorial editorial = editorialrepositorio.buscarEditorialPorNombre(nombre);

        return editorial;
    }
    
    
    //método para buscar una editorial por ID
        public Editorial buscarEditorialPorId(String id) {
        Editorial editorial = editorialrepositorio.buscareditorialporid(id);
        return editorial;
    }
    
    
    //servicio para dar de baja un editorial:
    @Transactional
    public void baja(String id) {
        Optional<Editorial> respuesta = editorialrepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(false);

            editorialrepositorio.save(editorial);
        }
    }
    //servicio para dar de alta un editorial:

    @Transactional
    public void alta(String id) {
        Optional<Editorial> respuesta = editorialrepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(true);

            editorialrepositorio.save(editorial);
        }
    }
    
    
    //Método para modificar un libro:
    @Transactional

    public void modificareditorial(String id, String nombre) throws ErrorServicio {

        Optional<Editorial> respuesta = editorialrepositorio.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();

            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
            }

            editorial.setNombre(nombre);

            //Persistimos el nuevo objeto libro (que incluye los dos nuevos objetos editorial y Editorial)
            editorialrepositorio.save(editorial);
        } else {
            throw new ErrorServicio("Error de base de datos");
        }

    }


}
