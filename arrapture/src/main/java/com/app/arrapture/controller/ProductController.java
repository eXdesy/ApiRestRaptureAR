package com.app.arrapture.controller;

import com.app.arrapture.dto.ProductDTO;
import com.app.arrapture.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    // Creación de la tabla productos
    private final List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "product1", 100, "https://media.es.wired.com/photos/647f54f7e1c13787dd8e3361/16:9/w_2240,c_limit/Apple-Vision-Pro-Hands-On-Gear-Featured-GettyImages-1496190487.jpg"),
            new Product(2, "product2", 100, "https://media.es.wired.com/photos/647f54f7e1c13787dd8e3361/16:9/w_2240,c_limit/Apple-Vision-Pro-Hands-On-Gear-Featured-GettyImages-1496190487.jpg"),
            new Product(3, "product3", 100, "https://media.es.wired.com/photos/647f54f7e1c13787dd8e3361/16:9/w_2240,c_limit/Apple-Vision-Pro-Hands-On-Gear-Featured-GettyImages-1496190487.jpg"),
            new Product(4, "product4", 100, "https://media.es.wired.com/photos/647f54f7e1c13787dd8e3361/16:9/w_2240,c_limit/Apple-Vision-Pro-Hands-On-Gear-Featured-GettyImages-1496190487.jpg")
    ));

    // Llamar todos los elementos la tabla productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    // Llamar un elemento de la tabla productos según su ID
    @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id) {
        Product product = findById(id);
        return ResponseEntity.ok(product);
    }

    // Crear un producto en la tabla productos
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        int index = products.isEmpty()? 1 : getLastIndex() + 1;
        Product product = Product.builder().id(index).name(dto.getName()).price(dto.getPrice()).build();
        products.add(product);
        return ResponseEntity.ok(product);
    }

    // Actualizar un producto en la tabla productos
    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody ProductDTO dto) {
        Product product = findById(id);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return ResponseEntity.ok(product);
    }

    // Eliminar un producto en la tabla productos
    @DeleteMapping("{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") int id) {
        Product product = findById(id);
        if (product != null) {
            products.remove(product);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }    }

    // Método para obtener un producto entero
    private int getLastIndex() {
        return products.stream().max(Comparator.comparing(Product::getId)).map(Product::getId).orElse(0);
    }

    // Método para encontrar un producto
    private Product findById(int id) {
        return products.stream().filter(product -> product.getId() == id).findAny().orElse(null);
    }

}