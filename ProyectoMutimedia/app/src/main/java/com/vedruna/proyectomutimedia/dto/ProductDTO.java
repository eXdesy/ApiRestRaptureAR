package com.vedruna.proyectomutimedia.dto;

public class ProductDTO {
    // Las variables de la clase que representan:
    private String name; // El nombre
    private float price; // El precio
    private String imageUrl; // La URL de la imagen

    // Constructor por defecto de la clase
    public ProductDTO() {

    }

    // Constructor que inicializa los campos name, price e imageUrl
    public ProductDTO(String name, float price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Métodos getter y setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
