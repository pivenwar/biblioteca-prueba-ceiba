package com.ceiba.biblioteca.dominio.excepcion;

public class DominioExcepcion extends RuntimeException{

    public DominioExcepcion(String mensaje) {
        super(mensaje);
    }
}
