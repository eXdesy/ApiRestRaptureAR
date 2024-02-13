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
            new Product(1, "product1", 100, "https://media.es.wired.com/photos/647f54f7e1c13787dd8e3361/16:9/w_2240,c_limit/Apple-Vision-Pro-Hands-On-Gear-Featured-GettyImages-1496190487.jpg"),
            new Product(2, "product2", 100, "https://media.es.wired.com/photos/647f54f7e1c13787dd8e3361/16:9/w_2240,c_limit/Apple-Vision-Pro-Hands-On-Gear-Featured-GettyImages-1496190487.jpg"),
            new Product(3, "product3", 100, "https://media.es.wired.com/photos/647f54f7e1c13787dd8e3361/16:9/w_2240,c_limit/Apple-Vision-Pro-Hands-On-Gear-Featured-GettyImages-1496190487.jpg"),
            new Product(4, "product4", 100, "https://media.es.wired.com/photos/647f54f7e1c13787dd8e3361/16:9/w_2240,c_limit/Apple-Vision-Pro-Hands-On-Gear-Featured-GettyImages-1496190487.jpg")
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
        Product product = Product.builder().id(index).name(dto.getName()).price(dto.getPrice()).build();
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
        product.setPrice(dto.getPrice());
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
