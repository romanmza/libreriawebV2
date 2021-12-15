package com.ejercicio1.libreriaweb.controladores;

import com.ejercicio1.libreriaweb.entidades.Cliente;
import com.ejercicio1.libreriaweb.servicios.ClienteServicio;
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
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteservicio;

    @GetMapping("/registro") //localhost:8080/cliente/registro
    public String formulario() {

        return "form-cliente";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo,
            @RequestParam Long documento,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String telefono
    ) {

        try {

            clienteservicio.crearcliente(documento, nombre, apellido, telefono);

            modelo.put("exito", "Registro exitoso");

            List<Cliente> listaclientes = clienteservicio.listarClientes();
            modelo.addAttribute("clientes", listaclientes);

            return "list-cliente";

        } catch (Exception e) {

            modelo.put("error", "Falto algun dato");
            return "form-cliente";
        }
    }

    //controlador para listar clientes
    @GetMapping("/lista") //localhost:8080/cliente/lista

    public String lista(ModelMap modelo) {
        List<Cliente> listaClientes = clienteservicio.listarClientes();
        modelo.addAttribute("clientes", listaClientes);

        return "list-cliente";
    }

    //controlador para dar de baja un cliente
    @GetMapping("/baja/{id}")
    public String baja(ModelMap modelo, @PathVariable String id) {
        try {
            clienteservicio.baja(id);
            List<Cliente> listaClientes = clienteservicio.listarClientes();
            modelo.addAttribute("clientes", listaClientes);
            modelo.put("exito", "Modificación exitosa.");
            return "list-cliente";

        } catch (Exception e) {
            modelo.put("error", "Error al modificar atributo.");
            return "form-cliente";
        }

    }

    //controlador para dar de alta un cliente
    @GetMapping("/alta/{id}")
    public String alta(ModelMap modelo, @PathVariable String id) {
        try {
            clienteservicio.alta(id);
            List<Cliente> listaClientes = clienteservicio.listarClientes();
            modelo.addAttribute("clientes", listaClientes);
            modelo.put("exito", "Modificación exitosa.");
            return "list-cliente";

        } catch (Exception e) {
            modelo.put("error", "Error al modificar atributo.");
            return "form-cliente";
        }

    }

    //controlador para modificar un cliente existente
    @GetMapping("/modificar/{id}") //PATHVARIABLE
    public String modificar(ModelMap modelo, @PathVariable String id) {

        //traemos todos los atributos del cliente según el ID elegido
        modelo.addAttribute("cliente", clienteservicio.buscarClientePorId(id));

        return "form-cliente-modif";
    }

    @PostMapping("/modificar/{id}")
    public String modificarCliente(ModelMap modelo,
            @RequestParam String id,
            @RequestParam Long documento,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String telefono
    ) {
        try {
            clienteservicio.modificarCliente(id, documento, nombre, apellido, telefono);
            //una vez modificado, volvemos a mostrar la lista de clientes

            List<Cliente> listaClientes = clienteservicio.listarClientes();
            modelo.addAttribute("clientes", listaClientes);

            modelo.put("exito", "Modificación exitosa.");
            return "list-cliente";

        } catch (Exception e) {
            modelo.put("error", "Falto algun dato");
            return "list-cliente";
        }
    }

}
