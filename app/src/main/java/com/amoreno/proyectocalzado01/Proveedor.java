package com.grupal.proyectoNoelia.entidad;

public class Proveedor {
    private int idProveedor;
    private String rucProveedor;
    private String razonSocialProveedor;
    private String direccionProveedor;
    private String telefonoProveedor;

    public Proveedor(String rucProveedor, String razonSocialProveedor, String direccionProveedor, String telefonoProveedor) {
        this.rucProveedor = rucProveedor;
        this.razonSocialProveedor = razonSocialProveedor;
        this.direccionProveedor = direccionProveedor;
        this.telefonoProveedor = telefonoProveedor;
    }

    public Proveedor(int idProveedor, String rucProveedor, String razonSocialProveedor, String direccionProveedor, String telefonoProveedor) {
        this.idProveedor = idProveedor;
        this.rucProveedor = rucProveedor;
        this.razonSocialProveedor = razonSocialProveedor;
        this.direccionProveedor = direccionProveedor;
        this.telefonoProveedor = telefonoProveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }
}
