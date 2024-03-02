package com.vedruna.proyectomutimedia.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Clase que representa un producto con su nombre, precio e URL de imagen.
 */
public class ProductDTO {
    // Las variables de la clase que representan:
    private String name; // El nombre
    private String description; // La descripción
    private float price; // El precio
    @SerializedName("url")
    private String imageUrl; // Cambio de "url" a "imageUrl"

    /**
     * Constructor por defecto de la clase ProductDTO.
     */
    public ProductDTO() {

    }

    /**
     * Constructor que inicializa los campos name, price e imageUrl.
     *
     * @param name     El nombre del producto.
     * @param description     La descripción del producto.
     * @param price    El precio del producto.
     * @param imageUrl La URL de la imagen del producto.
     */
    public ProductDTO(String name, String description, float price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param name El nombre del producto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return la descripción del producto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripcion del producto.
     *
     * @param description la Descripcion del producto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price El precio del producto.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     *
     * @return La URL de la imagen del producto.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Establece la URL de la imagen del producto.
     *
     * @param imageUrl La URL de la imagen del producto.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
