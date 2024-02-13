package com.vedruna.proyectomutimedia.interfaz;

import com.vedruna.proyectomutimedia.dto.ProductDTO;
import com.vedruna.proyectomutimedia.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Interfaz que define las operaciones básicas CRUD para productos.
 */
public interface CRUDInterface {
    /**
     * Obtiene todos los productos.
     *
     * @return Una llamada asíncrona que devuelve una lista de productos.
     */
    @GET("/product")
    Call<List<Product>> getAll();

    /**
     * Crea un nuevo producto.
     *
     * @param dto DTO del producto que se va a crear.
     * @return Una llamada asíncrona que devuelve el producto creado.
     */
    @POST("/product")
   Call<Product>create(@Body ProductDTO dto);

    /**
     * Actualiza un producto existente.
     *
     * @param id          ID del producto que se va a actualizar.
     * @param productDTO  DTO del producto actualizado.
     * @return Una llamada asíncrona que devuelve el producto actualizado.
     */
    @PUT("/product/{id}")
    Call<Product> actualizar(@Path("id") int id, @Body ProductDTO productDTO);

    /**
     * Elimina un producto existente.
     *
     * @param id ID del producto que se va a eliminar.
     * @return Una llamada asíncrona que no devuelve ningún resultado.
     */
    @DELETE("/product/{id}")
    Call<Void>delete(@Path("id")int id);

}
