package com.vedruna.proyectomutimedia.model;

import java.io.Serializable;

/**
 * Clase que representa un producto.
 */
public class Product implements Serializable {
    // Se definen los campos ID, nombre, precio y imagen para almacenar la información del producto
    private int productID;
    private String name;
    private float price;
    private String imageUrl;

    /**
     * Constructor vacío de la clase.
     */
    public Product() {
    }

    /**
     * Constructor que inicializa todos los campos de la clase con los valores proporcionados.
     *
     * @param productID Identificador del producto.
     * @param name      Nombre del producto.
     * @param price     Precio del producto.
     * @param imageUrl  URL de la imagen del producto.
     */
    public Product(int productID, String name, float price, String imageUrl) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    /**
     * Obtiene el ID del producto.
     *
     * @return ID del producto.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return Precio del producto.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     *
     * @return URL de la imagen del producto.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Establece el ID del producto.
     *
     * @param productID ID del producto.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param name Nombre del producto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price Precio del producto.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Establece la URL de la imagen del producto.
     *
     * @param imageUrl URL de la imagen del producto.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Método que devuelve una representación en cadena del producto.
     *
     * @return Representación en cadena del producto.
     */
    @Override
    public String toString() {
        return "Id: " + getProductID() + "Name: " + getName() + "Price: " + getPrice() + "URL: " + getImageUrl();
    }

}
