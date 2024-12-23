package org.example.vmsproject.controller;

import org.example.vmsproject.dto.ProductDTO;
import org.example.vmsproject.entity.Product;
import org.example.vmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable("id") long id) {
        List<Product> products = productService.getAllProducts(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO request) {
        productService.addProduct(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")long id,@RequestBody ProductDTO product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/totalProduct")
    public ResponseEntity<?> getTotalLess() {
        int result = productService.totalProducts();
        return ResponseEntity.ok(result);
    }

}
