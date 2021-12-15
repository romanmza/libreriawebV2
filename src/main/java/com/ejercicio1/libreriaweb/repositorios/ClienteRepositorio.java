package com.ejercicio1.libreriaweb.repositorios;

import com.ejercicio1.libreriaweb.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String> {

    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    public Cliente buscarclienteporid(@Param("id") String id);

    @Query("SELECT c FROM Cliente c")
    public List<Cliente> listarclientes();

    //buscar cliente por nombre
    @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    public Cliente buscarClientePorNombre(@Param("nombre") String nombre);
    
 

}