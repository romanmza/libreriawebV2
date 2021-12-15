package com.ejercicio1.libreriaweb.repositorios;

import com.ejercicio1.libreriaweb.entidades.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, String> {

    @Query("SELECT c FROM Prestamo c WHERE c.id = :id")
    public Prestamo buscarprestamoporid(@Param("id") String id);

    @Query("SELECT c FROM Prestamo c")
    public List<Prestamo> listarprestamos();

//    //buscar prestamo por nombre
//    @Query("SELECT c FROM Prestamo c WHERE c.nombre = :nombre")
//    public Prestamo buscarprestamoPorNombre(@Param("nombre") String nombre);

}