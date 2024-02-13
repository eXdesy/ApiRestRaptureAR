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

public interface CRUDInterface {
    // Define las operaciones básicas CRUD:

    // Obtener todos los productos
    @GET("/product")
    Call<List<Product>> getAll();

    // Crear un nuevo producto
    @POST("/product")
   Call<Product>create(@Body ProductDTO dto);

    // Actualizar un producto existente
    @PUT("/product/{id}")
    Call<Product> actualizar(@Path("id") int id, @Body ProductDTO productDTO);

    // Eliminar un producto existente
    @DELETE("/product/{id}")
    Call<Void>delete(@Path("id")int id);

}
