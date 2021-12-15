package com.ejercicio1.libreriaweb.servicios;

import com.ejercicio1.libreriaweb.entidades.Autor;
import com.ejercicio1.libreriaweb.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejercicio1.libreriaweb.errores.ErrorServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorrepositorio;

    @Transactional
    public void crearautor(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
        }
        Autor autor = new Autor();
        autor.setNombre(nombre);

        //System.out.println("Flag 3: autor.Nombre: " + autor.getNombre());
        autorrepositorio.save(autor);
    }
    
    //método para buscar un autor por ID
        public Autor buscarAutorPorId(String id) {
        Autor autor = autorrepositorio.buscarautorporid(id);
        return autor;
    }

    //Método para modificar un autor:
    @Transactional

    public void modificarAutor(String id, String nombre) throws ErrorServicio {

        Optional<Autor> respuesta = autorrepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            if (nombre == null || nombre.isEmpty()) {
                throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
            }

            autor.setNombre(nombre);

            //Persistimos el nuevo objeto libro (que incluye los dos nuevos objetos Autor y Editorial)
            autorrepositorio.save(autor);
        } else {
            throw new ErrorServicio("Error de base de datos");
        }

    }

    //Método para listar autores:
    public List<Autor> listarAutores() {
        List<Autor> autores = autorrepositorio.listarautores();
        return autores;
    }

    //Método para buscar autor por nombre
    public Autor buscarAutorporNombre(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
        }

        //    Optional<Autor> respuesta = autorrepositorio.findById(id);
        Autor autor = autorrepositorio.buscarAutorPorNombre(nombre);
        System.out.println("autor serivcio. Nombre: " + nombre);

        return autor;
    }

    //servicio para dar de baja un autor:
    @Transactional
    public void baja(String id) {
        Optional<Autor> respuesta = autorrepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);

            autorrepositorio.save(autor);
        }
    }
    //servicio para dar de alta un autor:

    @Transactional
    public void alta(String id) {
        Optional<Autor> respuesta = autorrepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(true);

            autorrepositorio.save(autor);
        }
    }

}
