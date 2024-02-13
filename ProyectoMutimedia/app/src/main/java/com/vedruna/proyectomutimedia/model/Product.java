package com.vedruna.proyectomutimedia.model;

import java.io.Serializable;

public class Product implements Serializable {
    // Se definen los campos ID, nombre, precio y imagen para almacenar la información del producto
    private int productID;
    private String name;
    private float price;
    private String imageUrl;

    // Constructor vacío de la clase
    public Product() {
    }
    // Constructor que inicializa todos los campos de la clase con los valores proporcionados
    public Product(int productID, String name, float price, String imageUrl) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Los getters y setters
    public int getProductID() {
        return productID;
    }
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }
    public void setPrice(float precio) {
        this.price = precio;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Id: "+getProductID()+"Name: "+ getName()+"Price: "+getPrice()+ "URL: "+getImageUrl();
    }
}
