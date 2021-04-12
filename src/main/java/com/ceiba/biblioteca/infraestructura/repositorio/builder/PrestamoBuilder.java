package com.ceiba.biblioteca.infraestructura.repositorio.builder;

import com.ceiba.biblioteca.dominio.model.Prestamo;
import com.ceiba.biblioteca.infraestructura.entidad.PrestamoEntidad;

public class PrestamoBuilder {

    public static PrestamoEntidad convertirAEntidad(Prestamo prestamo) {
        PrestamoEntidad prestamoEntidad = new PrestamoEntidad();
        prestamoEntidad.setId(prestamo.getId());
        prestamoEntidad.setFechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion());
        prestamoEntidad.setIsbn(prestamo.getIsbn());
        prestamoEntidad.setIdentificacionUsuario(prestamo.getIdentificacionUsuario());
        prestamoEntidad.setTipoUsuario(prestamo.getTipoUsuario());
        return prestamoEntidad;
    }

    public static Prestamo convertirAModelo(PrestamoEntidad prestamoEntidad) {
        Prestamo prestamo =  new Prestamo();
        prestamo.setId(prestamoEntidad.getId());
        prestamo.setIsbn(prestamoEntidad.getIsbn());
        prestamo.setFechaMaximaDevolucion(prestamoEntidad.getFechaMaximaDevolucion());
        prestamo.setIdentificacionUsuario(prestamoEntidad.getIdentificacionUsuario());
        prestamo.setTipoUsuario(prestamoEntidad.getTipoUsuario());
        return prestamo;
    }
}
