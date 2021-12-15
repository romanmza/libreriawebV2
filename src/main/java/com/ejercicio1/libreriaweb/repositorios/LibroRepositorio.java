package com.ejercicio1.libreriaweb.repositorios;

import com.ejercicio1.libreriaweb.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface LibroRepositorio extends JpaRepository<Libro, String> {

    @Query("SELECT c FROM Libro c WHERE c.isbn= :isbn")
    public Libro buscarlibroporisbn(@Param("isbn") String isbn);

    @Query("SELECT c FROM Libro c WHERE c.autor.nombre = :nombreautor")
    public List<Libro> listarlibrosporautor(@Param("nombreautor") String nombreautor);

    @Query("SELECT c FROM Libro c")
    public List<Libro> listarlibros();
    
    

    @Query("SELECT c FROM Libro c WHERE c.id = :id ")
    public Libro buscarLibroPorId(@Param("id") String id);



}
