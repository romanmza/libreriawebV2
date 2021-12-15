package com.ejercicio1.libreriaweb.repositorios;

import com.ejercicio1.libreriaweb.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

    @Query("SELECT c FROM Editorial c WHERE c.id = :id")
    public Editorial buscareditorialporid(@Param("id") String id);

    @Query("SELECT c FROM Editorial c")
    public List<Editorial> listareditoriales();
    
        //buscar editorial por nombre
    @Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
     public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);

}
