package com.app.arrapture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa un producto en el sistema.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    // ID del producto
    private int id;
    // Nombre del producto
    private String name;
    // Descripcion del producto
    private String description;
    // Precio del producto
    private int price;
    // URL de la imagen del producto
    private String url;

    /**
     * Obtiene el ID del producto.
     *
     * @return El ID del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     *
     * @param id El nuevo ID del producto.
     */
    public void setId(int id) {
        this.id = id;
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
     * Establece la descripcion del producto.
     *
     * @param description El nuevo descripcion del producto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la descripcion del producto.
     *
     * @return El descripcion del producto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param name El nuevo nombre del producto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price El nuevo precio del producto.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     *
     * @return La URL de la imagen del producto.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la URL de la imagen del producto.
     *
     * @param url La nueva URL de la imagen del producto.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
