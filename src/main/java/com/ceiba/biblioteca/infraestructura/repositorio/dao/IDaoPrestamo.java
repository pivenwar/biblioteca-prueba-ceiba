package com.ceiba.biblioteca.infraestructura.repositorio.dao;

import com.ceiba.biblioteca.dominio.model.Prestamo;

import java.util.List;

public interface IDaoPrestamo {

   Prestamo crear(Prestamo prestamo);

   List<Prestamo> obtenerPrestamosPorUsuario(String identificacionUsuario);

   Prestamo obtenerPorId(Long idPrestamo) throws Exception;
}
