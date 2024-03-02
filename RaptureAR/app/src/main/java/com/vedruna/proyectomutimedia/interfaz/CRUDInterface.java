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
 * Interfaz que define las operaciones CRUD (Crear, Leer, Actualizar, Borrar) para la gestión de productos.
 */
public interface CRUDInterface {

    /**
     * Obtiene la lista de todos los productos.
     *
     * @return Una llamada (Call) que devuelve una lista de productos.
     */
    @GET("product")
    Call<List<Product>> getAll();

    /**
     * Crea un nuevo producto.
     *
     * @param dto El objeto DTO (ProductDTO) que representa el nuevo producto a crear.
     * @return Una llamada (Call) que devuelve el producto creado.
     */
    @POST("product")
    Call<Product>create(@Body ProductDTO dto);

    /**
     * Actualiza un producto existente.
     *
     * @param id         El ID del producto a actualizar.
     * @param productDTO El objeto DTO (ProductDTO) con los nuevos datos del producto.
     * @return Una llamada (Call) que devuelve el producto actualizado.
     */
    @PUT("product/{id}")
    Call<Product> actualizar(@Path("id") int id, @Body ProductDTO productDTO);

    /**
     * Elimina un producto existente.
     *
     * @param id El ID del producto a eliminar.
     * @return Una llamada (Call) que no devuelve ningún resultado.
     */
    @DELETE("product/{id}")
    Call<Void>delete(@Path("id")int id);

}