package com.app.arrapture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un DTO de producto en el sistema.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductDTO {
    /** Nombre del producto. */
    private String name;

    /** Precio del producto. */
    private int price;

    /** URL de la imagen del producto. */
    private String url;
}
