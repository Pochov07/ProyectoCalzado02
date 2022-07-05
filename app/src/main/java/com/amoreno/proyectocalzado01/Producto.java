package com.grupal.proyectoNoelia.entidad;

public class Producto {
    private int idProducto;
    private String codProducto;
    private String nombProducto;
    private String colorProducto;
    private int stockProducto;
    private int tallaProducto;
    private double precioProducto;

    public Producto(int idProducto, String codProducto, String nombProducto, String colorProducto, int stockProducto, int tallaProducto, double precioProducto) {
        this.idProducto = idProducto;
        this.codProducto = codProducto;
        this.nombProducto = nombProducto;
        this.colorProducto = colorProducto;
        this.stockProducto = stockProducto;
        this.tallaProducto = tallaProducto;
        this.precioProducto = precioProducto;
    }

    public Producto(String codProducto, String nombProducto, String colorProducto, int stockProducto, int tallaProducto, double precioProducto) {
        this.codProducto = codProducto;
        this.nombProducto = nombProducto;
        this.colorProducto = colorProducto;
        this.stockProducto = stockProducto;
        this.tallaProducto = tallaProducto;
        this.precioProducto = precioProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombProducto() {
        return nombProducto;
    }

    public void setNombProducto(String nombProducto) {
        this.nombProducto = nombProducto;
    }

    public String getColorProducto() {
        return colorProducto;
    }

    public void setColorProducto(String colorProducto) {
        this.colorProducto = colorProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public int getTallaProducto() {
        return tallaProducto;
    }

    public void setTallaProducto(int tallaProducto) {
        this.tallaProducto = tallaProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

}
