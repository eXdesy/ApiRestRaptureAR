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
            new Product(1, "Meta Quest 3","Gafas de realidad virtual - 128 GB, Realidad mixta revolucionaria, Potente rendimiento, Paquete Asgard’s Wrath 2, Blanco", 549, "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_123786881?x=600&y=450&format=jpg&quality=80&sp=yes&strip=yes&trim&ex=600&ey=450&align=center&resizesource&unsharp=1.5x1+0.7+0.02&cox=0&coy=0&cdx=600&cdy=450"),
            new Product(2, "Apple Vision Pro","Apple Vision Pro (256GB)", 4447, "https://images.stockx.com/images/Apple-Vision-Pro.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&q=90&dpr=2&trim=color&updated_at=1686069484?height=78&width=78"),
            new Product(3, "PLAYSTATION VR","Gafas de PlayStation VR para PlayStation 4 y descubrirás una ventana a nuevas realidades.", 179, "https://media.game.es/COVERV2/3D_L/125/125227.png")
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
