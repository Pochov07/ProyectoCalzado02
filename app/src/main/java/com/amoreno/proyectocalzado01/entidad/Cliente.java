package com.grupal.proyectoNoelia.entidad;

public class Cliente {
    private int idCliente;
    private String dniCliente;
    private String  nombresCliente;
    private String  apellidosCliente;
    private String telefonoCliente;

    public Cliente(String dniCliente, String nombresCliente, String apellidosCliente, String telefonoCliente) {
        this.dniCliente = dniCliente;
        this.nombresCliente = nombresCliente;
        this.apellidosCliente = apellidosCliente;
        this.telefonoCliente = telefonoCliente;
    }

    public Cliente(int idCliente, String dniCliente, String nombresCliente, String apellidosCliente, String telefonoCliente) {
        this.idCliente = idCliente;
        this.dniCliente = dniCliente;
        this.nombresCliente = nombresCliente;
        this.apellidosCliente = apellidosCliente;
        this.telefonoCliente = telefonoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombresCliente() {
        return nombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }

    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
}
