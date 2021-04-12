package com.ceiba.biblioteca.infraestructura.controlador;


import com.ceiba.biblioteca.dominio.model.Libro;
import com.ceiba.biblioteca.dominio.model.Prestamo;
import com.ceiba.biblioteca.dominio.servicio.ServicioCrearPrestamo;
import com.ceiba.biblioteca.dominio.servicio.ServicioObtenerPrestamo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    private ServicioCrearPrestamo servicioCrearPrestamo;
    private ServicioObtenerPrestamo servicioObtenerPrestamo;

    public PrestamoControlador(final ServicioCrearPrestamo servicioCrearPrestamo, ServicioObtenerPrestamo servicioObtenerPrestamo) {
        this.servicioCrearPrestamo = servicioCrearPrestamo;
        this.servicioObtenerPrestamo = servicioObtenerPrestamo;
    }

    @PostMapping
    public Prestamo crear(@RequestBody Libro libro) {
        return servicioCrearPrestamo.crear(libro);
    }

    @GetMapping("/{id-prestamo}")
    private Prestamo obtener(@PathVariable("id-prestamo") Long idPrestamo) {
        return servicioObtenerPrestamo.obtener(idPrestamo);
    }
}


