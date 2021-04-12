package com.ceiba.biblioteca.dominio.servicio;

import com.ceiba.biblioteca.dominio.excepcion.DominioExcepcion;
import com.ceiba.biblioteca.dominio.model.Libro;
import com.ceiba.biblioteca.dominio.model.Prestamo;
import com.ceiba.biblioteca.infraestructura.repositorio.dao.IDaoPrestamo;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ServicioCrearPrestamo {

    public static final int USUARIO_AFILIADO = 1;
    public static final int USUARIO_EMPLEADO = 2;
    public static final int USUARIO_INVITADO = 3;
    public static final int USUARIO_DESCONOCIDO = 5;

    public static final String MENSAJE_PRESTAMO_EXISTENTE_USUARIO_INVITADO = "El usuario con identificación %s ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";
    public static final String MENSAJE_USUARRIO_NO_PERMITIDO = "Tipo de usuario no permitido en la biblioteca";

    private final IDaoPrestamo daoPrestamo;

    public ServicioCrearPrestamo(IDaoPrestamo daoPrestamo) {
        this.daoPrestamo = daoPrestamo;
    }

    public Prestamo crear(Libro libro) {
        Prestamo prestamo = new Prestamo();
        prestamo.setIsbn(libro.getIsbn());
        prestamo.setIdentificacionUsuario(libro.getIdentificacionUsuario());
        prestamo.setTipoUsuario(libro.getTipoUsuario());
        validaPrestamoExistenteUsuarioInvitado(libro);
        calcularFechaDevolucionUsuario(prestamo);
        return daoPrestamo.crear(prestamo);
    }


    private void validaPrestamoExistenteUsuarioInvitado(Libro libro) {
        if (libro.getTipoUsuario() == USUARIO_INVITADO) {
            List<Prestamo> prestamos = daoPrestamo.obtenerPrestamosPorUsuario(libro.getIdentificacionUsuario());

            if (prestamos != null && !prestamos.isEmpty()) {

                throw new DominioExcepcion(String.format(MENSAJE_PRESTAMO_EXISTENTE_USUARIO_INVITADO, libro.getIdentificacionUsuario()));
            }
        }
    }

    private void calcularFechaDevolucionUsuario(Prestamo prestamo) {
        LocalDate fecha = LocalDate.now();
        LocalDate fechaPrestamo = LocalDate.now();

        if (prestamo.getTipoUsuario() == USUARIO_DESCONOCIDO) {
            throw new DominioExcepcion(MENSAJE_USUARRIO_NO_PERMITIDO);
        }
        if (prestamo.getTipoUsuario() == USUARIO_AFILIADO) {
            fecha = agregarDias(fechaPrestamo, 10);
        }
        if (prestamo.getTipoUsuario() == USUARIO_EMPLEADO) {
            fecha = agregarDias(fechaPrestamo, 8);
        }
        if (prestamo.getTipoUsuario() == USUARIO_INVITADO) {
            fecha = agregarDias(fechaPrestamo, 7);
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        prestamo.setFechaMaximaDevolucion(formato.format(fecha));
    }

    public LocalDate agregarDias(LocalDate fecha, int days) {
        LocalDate result = fecha;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
}
