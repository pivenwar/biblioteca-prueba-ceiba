package com.ceiba.biblioteca.infraestructura.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class LibroEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String isbn;
    private String identificaciónUsuario;
    private Integer tipoUsuario;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentificaciónUsuario() {
        return identificaciónUsuario;
    }

    public void setIdentificaciónUsuario(String identificaciónUsuario) {
        this.identificaciónUsuario = identificaciónUsuario;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


}
