package org.eldar.challenge;

public class Cliente {
    private String nombre;
    private String appelido;
    private Integer dni;

    public Cliente(String nombre, String appelido, Integer dni) {
        this.nombre = nombre;
        this.appelido = appelido;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAppelido() {
        return appelido;
    }

    public void setAppelido(String appelido) {
        this.appelido = appelido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }
}
