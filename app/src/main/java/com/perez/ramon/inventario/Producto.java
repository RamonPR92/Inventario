package com.perez.ramon.inventario;

/**
 * Created by Ramon on 23/01/2018.
 */

class Producto {
    private int id;
    private String nombre;
    private String marca;
    private float precio;
    private float cantidad;

    public Producto(String nombre, String marca, float precio, float cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        if (id != producto.id) return false;
        if (Float.compare(producto.precio, precio) != 0) return false;
        if (Float.compare(producto.cantidad, cantidad) != 0) return false;
        if (!nombre.equals(producto.nombre)) return false;
        return marca.equals(producto.marca);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + marca.hashCode();
        result = 31 * result + (precio != +0.0f ? Float.floatToIntBits(precio) : 0);
        result = 31 * result + (cantidad != +0.0f ? Float.floatToIntBits(cantidad) : 0);
        return result;
    }
}
