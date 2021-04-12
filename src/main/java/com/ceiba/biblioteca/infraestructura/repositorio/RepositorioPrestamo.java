package com.ceiba.biblioteca.infraestructura.repositorio;

import com.ceiba.biblioteca.infraestructura.entidad.PrestamoEntidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioPrestamo extends CrudRepository<PrestamoEntidad, Long> {


    List<PrestamoEntidad> findByIdentificacionUsuario(String identificacionUsuario);
}
