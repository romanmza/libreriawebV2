package com.ejercicio1.libreriaweb.servicios;

import com.ejercicio1.libreriaweb.entidades.Autor;
import com.ejercicio1.libreriaweb.entidades.Editorial;
import com.ejercicio1.libreriaweb.entidades.Libro;
import com.ejercicio1.libreriaweb.errores.ErrorServicio;
import com.ejercicio1.libreriaweb.repositorios.AutorRepositorio;
import com.ejercicio1.libreriaweb.repositorios.EditorialRepositorio;
import com.ejercicio1.libreriaweb.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio librorepositorio;
    @Autowired
    private AutorRepositorio autorrepositorio;
    @Autowired
    private EditorialRepositorio editorialrepositorio;

    //private EditorialRepositorio editorialrepositorio;
    //Métodos CRUD:
    //Método para crear un libro:
    @Transactional
    public void crearlibro(Long isbn, String titulo, Integer anio, Integer ejemplares, String nombreautor, String nombreeditorial) throws ErrorServicio {
        Libro libro1 = new Libro();

        validacion(isbn, titulo, anio, ejemplares, nombreautor, nombreeditorial);
        libro1.setIsbn(isbn);
        libro1.setTitulo(titulo);
        libro1.setAnio(anio);
        libro1.setEjemplares(ejemplares);
        libro1.setEjemplaresPrestados(0);
        libro1.setEjemplaresRestantes(ejemplares);

        Autor autor = autorrepositorio.buscarAutorPorNombre(nombreautor);

        Editorial editorial = editorialrepositorio.buscarEditorialPorNombre(nombreeditorial);

        libro1.setAutor(autor);
        libro1.setEditorial(editorial);
        
        
//        String encriptada = new BCryptPasswordEncoder().encode(clave);
//        libro1.setClave(encriptada);

        System.out.println("Libro :" + libro1);

        //Este comando persiste en la base de datos el objeto libro con todos sus atributos
        //como tiene objetos como atributos (autor y editorial) estos serán también persistidos en sus respectivas tablas
        //por la instrucción CascadeType=ALL del ManytoOne en la entidad Libro.
        librorepositorio.save(libro1);
        //Mostramos  por consola el contenido del objeto Libro persistido.
        System.out.println(libro1);

    }

    //Método para listar libros:
    public List<Libro> listarLibros() {
        List<Libro> libros = librorepositorio.listarlibros();
        return libros;
    }

    public Libro buscarlibroporid(String id) {
        Libro libro = librorepositorio.buscarLibroPorId(id);
        return libro;
    }

    public Libro buscarlibroporisbn(String isbn) {
        Libro libro = librorepositorio.buscarlibroporisbn(isbn);
        return libro;
    }

    //servicio para dar de baja un libro:
    @Transactional
    public void baja(String id) {
        Optional<Libro> respuesta = librorepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(false);

            librorepositorio.save(libro);
        }
    }
    //servicio para dar de alta un libro:

    @Transactional
    public void alta(String id) {
        Optional<Libro> respuesta = librorepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(true);

            librorepositorio.save(libro);
        }
    }

//Método para modificar un libro:
    @Transactional

    public void modificarlibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, String nombreautor, String nombreeditorial) throws ErrorServicio {

        Optional<Libro> respuesta = librorepositorio.findById(id);

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            validacion(isbn, titulo, anio, ejemplares, nombreautor, nombreeditorial);

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            
            System.out.println(libro);

            Autor autor = autorrepositorio.buscarAutorPorNombre(nombreautor);     
            Editorial editorial = editorialrepositorio.buscarEditorialPorNombre(nombreeditorial);
            
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            //Persistimos el nuevo objeto libro (que incluye los dos nuevos objetos Autor y Editorial)
            librorepositorio.save(libro);
        } else {
            throw new ErrorServicio("Error de base de datos");
        }

    }

    private void validacion(Long isbn, String titulo, Integer anio, Integer ejemplares, String nombreautor, String nombreeditorial) throws ErrorServicio {
        String isbn_length = String.valueOf(isbn);
        if (isbn == null || isbn_length.length() < 13) {
            throw new ErrorServicio("El código de ISBN no debe ser nulo ni menor a 13 caracteres.");
        }

        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El título no puede estar vacío o nulo.");
        }

        if (anio == null || anio == 0) {
            throw new ErrorServicio("El año no puede estar vacío ni ser igual a cero.");
        }

        if (ejemplares == null || ejemplares <= 0) {
            throw new ErrorServicio("La candidad mínima de ejemplares debe ser mayor a cero.");
        }

        if (nombreautor == null || nombreautor.isEmpty()) {
            throw new ErrorServicio("El nombre del Autor no puede estar vacío o nulo.");
        }

        if (nombreeditorial == null || nombreeditorial.isEmpty()) {
            throw new ErrorServicio("El nombre de la Editorial no puede estar vacío o nulo.");
        }
    }

}
