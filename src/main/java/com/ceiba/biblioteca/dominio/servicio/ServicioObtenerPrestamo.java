package com.ceiba.biblioteca.dominio.servicio;

import com.ceiba.biblioteca.dominio.excepcion.DominioExcepcion;
import com.ceiba.biblioteca.dominio.model.Prestamo;
import com.ceiba.biblioteca.infraestructura.repositorio.dao.IDaoPrestamo;
import org.springframework.stereotype.Service;

@Service
public class ServicioObtenerPrestamo {

    private IDaoPrestamo daoPrestamo;

    public ServicioObtenerPrestamo(final IDaoPrestamo daoPrestamo) {
        this.daoPrestamo = daoPrestamo;
    }

    public Prestamo obtener(Long idPrestamo) {
        try {
            return daoPrestamo.obtenerPorId(idPrestamo);
        } catch (Exception exception) {
            throw new DominioExcepcion(exception.getMessage());
        }
    }
}
