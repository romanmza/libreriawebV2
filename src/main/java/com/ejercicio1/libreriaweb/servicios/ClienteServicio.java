package com.ejercicio1.libreriaweb.servicios;

import com.ejercicio1.libreriaweb.entidades.Cliente;
import com.ejercicio1.libreriaweb.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejercicio1.libreriaweb.errores.ErrorServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienterepositorio;

    @Transactional
    public void crearcliente(Long documento, String nombre, String apellido, String telefono) throws ErrorServicio {

        Cliente cliente = new Cliente();

        validacion(documento, nombre, apellido, telefono);

        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);

        clienterepositorio.save(cliente);
    }

    //método para buscar un cliente por ID
    public Cliente buscarClientePorId(String id) {
        Cliente cliente = clienterepositorio.buscarclienteporid(id);
        return cliente;
    }

    //Método para modificar un cliente:
    @Transactional

    public void modificarCliente(String id, Long documento, String nombre, String apellido, String telefono) throws ErrorServicio {

        Optional<Cliente> respuesta = clienterepositorio.findById(id);

        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();

            validacion(documento, nombre, apellido, telefono);

            cliente.setDocumento(documento);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);

            //Persistimos el nuevo objeto libro (que incluye los dos nuevos objetos Cliente y Editorial)
            clienterepositorio.save(cliente);
        } else {
            throw new ErrorServicio("Error de base de datos");
        }

    }

    //Método para listar clientes:
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = clienterepositorio.listarclientes();
        return clientes;
    }

    //Método para buscar cliente por nombre
    public Cliente buscarClienteporNombre(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
        }

        //    Optional<Cliente> respuesta = clienterepositorio.findById(id);
        Cliente cliente = clienterepositorio.buscarClientePorNombre(nombre);
        System.out.println("cliente serivcio. Nombre: " + nombre);

        return cliente;
    }

    //servicio para dar de baja un cliente:
    @Transactional
    public void baja(String id) {
        Optional<Cliente> respuesta = clienterepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setAlta(false);

            clienterepositorio.save(cliente);
        }
    }
    //servicio para dar de alta un cliente:

    @Transactional
    public void alta(String id) {
        Optional<Cliente> respuesta = clienterepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setAlta(true);

            clienterepositorio.save(cliente);
        }
    }

    private void validacion(Long documento, String nombre, String apellido, String telefono) throws ErrorServicio {

        if (documento == null || documento == 0) {
            throw new ErrorServicio("El Documento no puede estar vacío o ser igual a cero.");
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El nombre no puede estar vacío o nulo.");
        }

    }

}
