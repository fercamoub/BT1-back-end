package com.example.DestroyableToy.service;

import com.example.DestroyableToy.model.CategoryOverview;
import com.example.DestroyableToy.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private Map<Long, Product> products = new HashMap<>();
    private long idCounter = 1L;

    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }
    public Product getProductById(Long id){
        return products.get(id);
    }
    public Product createProduct(Product product){
        product.setId(idCounter++);
        products.put(product.getId(), product);
        return product;
    }
    public Product updateProduct(Long id, Product updatedProduct ){
        Product productToUpdate = products.get(id);
        if (productToUpdate != null){
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setCategory(updatedProduct.getCategory());
            productToUpdate.setPrice(updatedProduct.getPrice());
            productToUpdate.setStock(updatedProduct.getStock());
            productToUpdate.setExpirationDate(updatedProduct.getExpirationDate());
        }
        return productToUpdate;
    }
    public void deleteProduct(Long id){
        products.remove(id);
    }

    public Product refillStock(long id) {
        Product product = products.get(id);
        if (product != null) {
            product.setStock(product.getStock()+ 10);
        }
        return product;
    }

    public Product emptyStock(long id) {
        Product product = products.get(id);
        if (product != null) {
            product.setStock(0);
        }
        return product;
    }

    public void insertMockData() {
        createProduct(new Product(null, "Leche Entera", 1.99f, 50, "Lácteos", LocalDate.of(2025, 6, 1)));
        createProduct(new Product(null, "Pan Integral", 0.99f, 100, "Panadería", LocalDate.of(2025, 5, 30)));
        createProduct(new Product(null, "Manzana Roja", 0.50f, 200, "Frutas", LocalDate.of(2025, 6, 10)));
        createProduct(new Product(null, "Yogur Natural", 1.20f, 80, "Lácteos", LocalDate.of(2025, 6, 5)));
        createProduct(new Product(null, "Queso Cheddar", 2.50f, 60, "Lácteos", LocalDate.of(2025, 7, 15)));
        createProduct(new Product(null, "Jugo de Naranja", 1.75f, 90, "Bebidas", LocalDate.of(2025, 8, 1)));
        createProduct(new Product(null, "Cereal de Maíz", 3.40f, 120, "Desayuno", LocalDate.of(2026, 1, 10)));
        createProduct(new Product(null, "Tomate", 0.70f, 150, "Verduras", LocalDate.of(2025, 6, 3)));
        createProduct(new Product(null, "Café Molido", 4.99f, 75, "Bebidas", LocalDate.of(2026, 2, 20)));
        createProduct(new Product(null, "Galletas de Avena", 2.10f, 110, "Snacks", LocalDate.of(2025, 9, 15)));
        createProduct(new Product(null, "Huevos", 2.20f, 180, "Huevería", LocalDate.of(2025, 6, 12)));
        createProduct(new Product(null, "Pasta Spaghetti", 1.30f, 130, "Despensa", LocalDate.of(2026, 3, 1)));
        createProduct(new Product(null, "Aceite de Oliva", 5.99f, 60, "Despensa", LocalDate.of(2026, 5, 15)));
        createProduct(new Product(null, "Pechuga de Pollo", 6.49f, 45, "Carnes", LocalDate.of(2025, 5, 28)));
        createProduct(new Product(null, "Helado de Vainilla", 3.75f, 70, "Congelados", LocalDate.of(2026, 1, 5)));
    }

    public List<CategoryOverview> getProductOverviewByCategory() {
        Map<String, List<Product>> grouped = new HashMap<>();

        for (Product product : products.values()) {
            grouped.computeIfAbsent(product.getCategory(), k -> new ArrayList<>()).add(product);
        }

        List<CategoryOverview> overviewList = new ArrayList<>();

        for (Map.Entry<String, List<Product>> entry : grouped.entrySet()) {
            String category = entry.getKey();
            List<Product> productList = entry.getValue();

            int totalStock = productList.stream().mapToInt(Product::getStock).sum();
            float totalValue = (float) productList.stream()
                    .mapToDouble(p -> p.getPrice() * p.getStock())
                    .sum();
            float averagePrice = productList.isEmpty() ? 0 : totalValue / totalStock;

            overviewList.add(new CategoryOverview(category, totalStock, totalValue, averagePrice));
        }

        return overviewList;
    }



}
