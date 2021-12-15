package com.ejercicio1.libreriaweb.repositorios;

import com.ejercicio1.libreriaweb.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {

    @Query("SELECT c FROM Autor c WHERE c.id = :id")
    public Autor buscarautorporid(@Param("id") String id);

    @Query("SELECT c FROM Autor c")
    public List<Autor> listarautores();

    //buscar autor por nombre
    @Query("SELECT c FROM Autor c WHERE c.nombre = :nombre")
    public Autor buscarAutorPorNombre(@Param("nombre") String nombre);

}
