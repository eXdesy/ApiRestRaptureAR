package com.app.arrapture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    // ID del producto
    private int id;
    // Nombre del producto
    private String name;
    // Precio del producto
    private int price;
    // Imagen del producto
    private String url;
}

