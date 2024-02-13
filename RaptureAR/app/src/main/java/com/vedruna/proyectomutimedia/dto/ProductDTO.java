package com.vedruna.proyectomutimedia.dto;

/**
 * Clase que representa un producto con su nombre, precio e URL de imagen.
 */
public class ProductDTO {
    // Las variables de la clase que representan:
    private String name; // El nombre
    private float price; // El precio
    private String imageUrl; // La URL de la imagen

    /**
     * Constructor por defecto de la clase ProductDTO.
     */
    public ProductDTO() {

    }

    /**
     * Constructor que inicializa los campos name, price e imageUrl.
     *
     * @param name     El nombre del producto.
     * @param price    El precio del producto.
     * @param imageUrl La URL de la imagen del producto.
     */
    public ProductDTO(String name, float price, String imageUrl) {
        this.name = name;
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
