package com.example.DestroyableToy.controller;

import com.example.DestroyableToy.model.CategoryOverview;
import com.example.DestroyableToy.model.Product;
import com.example.DestroyableToy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/overview")
    public List<CategoryOverview> getProductOverview() {
        return productService.getProductOverviewByCategory();
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }
    @PutMapping("/{id}/instock")
    public Product refillProduct(@PathVariable long id) {
        return productService.refillStock(id);
    }

    @PutMapping("/{id}/outstock")
    public Product emptyProduct(@PathVariable long id) {
        return productService.emptyStock(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
    }
}
