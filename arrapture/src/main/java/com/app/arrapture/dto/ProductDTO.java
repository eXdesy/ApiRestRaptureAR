package com.app.arrapture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductDTO {
    // Nombre del producto de DTO
    private String name;
    // Precio del producto de DTO
    private int price;
    // Imagen del producto de DTO
    private String url;
}