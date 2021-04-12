package com.ceiba.biblioteca.infraestructura.repositorio.dao;

import com.ceiba.biblioteca.dominio.model.Prestamo;
import com.ceiba.biblioteca.infraestructura.entidad.PrestamoEntidad;
import com.ceiba.biblioteca.infraestructura.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.infraestructura.repositorio.builder.PrestamoBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DaoPrestamo implements IDaoPrestamo {

    private RepositorioPrestamo repositorioPrestamo;

    public DaoPrestamo(RepositorioPrestamo repositorioPrestamo) {

        this.repositorioPrestamo = repositorioPrestamo;
    }

    public Prestamo crear(Prestamo prestamo) {
        PrestamoEntidad prestamoCreado = repositorioPrestamo.save(PrestamoBuilder.convertirAEntidad(prestamo));
        return PrestamoBuilder.convertirAModelo(prestamoCreado);
    }



    @Override
    public List<Prestamo> obtenerPrestamosPorUsuario(String identificacionUsuario) {
        List<PrestamoEntidad> prestamos = repositorioPrestamo.findByIdentificacionUsuario(identificacionUsuario);
        return prestamos.stream().map(PrestamoBuilder::convertirAModelo).collect(Collectors.toList());
    }

    @Override
    public Prestamo obtenerPorId(Long idPrestamo) throws Exception {
        Optional<PrestamoEntidad> prestamoEntidad = repositorioPrestamo.findById(idPrestamo);

        if(!prestamoEntidad.isPresent()) {
            throw new Exception("El prestamo no existe");
        }
        return PrestamoBuilder.convertirAModelo(prestamoEntidad.get());
    }
}
