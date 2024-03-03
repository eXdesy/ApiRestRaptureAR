package com.app.arrapture.controller;

import com.app.arrapture.dto.ProductDTO;
import com.app.arrapture.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Controlador para manejar operaciones relacionadas con productos.
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    /** Lista de productos */
    private final List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Apple Vision Pro","Apple Vision Pro (256GB)", 4447, "https://store.bananacomputer.com/cmsAdmin/uploads/o_1hkrdkvd11v9pn251s8gn6ggrca.jpg"),
            new Product(2, "Meta Quest 3","Meta Quest 3 (128GB)", 349, "https://media.cnn.com/api/v1/images/stellar/prod/meta-quest-3-accessories-lead-cnnu.jpg?c=original"),
            new Product(3, "Apple Vision Pro","Apple Vision Pro (256GB)", 4447, "https://www.lavanguardia.com/andro4all/hero/2023/07/apple-vision-pro.1688731771.6856.jpg?width=1200"),
            new Product(4, "Meta Quest 3","Meta Quest 3 (256GB)", 349, "https://static.standard.co.uk/2023/09/26/11/newFile-17.jpg?crop=8:5,smart&quality=75&auto=webp&width=1024")
    ));

    /**
     * Obtiene todos los productos.
     * @return La lista de productos.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    /**
     * Obtiene un producto por su ID.
     * @param id El ID del producto.
     * @return El producto encontrado.
     */
    @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id) {
        Product product = findById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Crea un nuevo producto.
     * @param dto La información del producto a crear.
     * @return El producto creado.
     */
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        int index = products.isEmpty()? 1 : getLastIndex() + 1;
        Product product = Product.builder().id(index).name(dto.getName()).description(dto.getDescription()).price(dto.getPrice()).url(dto.getUrl()).build();
        products.add(product);
        return ResponseEntity.ok(product);
    }

    /**
     * Actualiza un producto existente.
     * @param id El ID del producto a actualizar.
     * @param dto La nueva información del producto.
     * @return El producto actualizado.
     */
    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody ProductDTO dto) {
        Product product = findById(id);
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setUrl(dto.getUrl());
        return ResponseEntity.ok(product);
    }

    /**
     * Elimina un producto por su ID.
     * @param id El ID del producto a eliminar.
     * @return La respuesta de eliminación.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") int id) {
        Product product = findById(id);
        if (product != null) {
            products.remove(product);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene el último índice de la lista de productos.
     * @return El último índice.
     */
    private int getLastIndex() {
        return products.stream().max(Comparator.comparing(Product::getId)).map(Product::getId).orElse(0);
    }

    /**
     * Encuentra un producto por su ID.
     * @param id El ID del producto a buscar.
     * @return El producto encontrado o nulo si no se encuentra.
     */
    private Product findById(int id) {
        return products.stream().filter(product -> product.getId() == id).findAny().orElse(null);
    }

}
