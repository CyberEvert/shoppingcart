package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final ProductRepository productRepository;

    private static double TAX_RATE = 22;



    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public String addProduct(Product product) {
        productRepository.save(product);
        return "Product added";
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public String deleteByName(String name) {
        productRepository.deleteByName(name);
        return name;
    }

    public double calculateCartTotal() {
        List<Product> products = productRepository.findAll();
        double total = 0;
        for (Product product : products) {

            total += product.calculateTotal();
        }
        System.out.println("Scheduler total: " + (total * calculateTaxRate()));

        return total * calculateTaxRate();
    }
    public double calculateTaxRate(){
        return 1 + TAX_RATE / 100;
    }
    public double calculateDiscountTotal(boolean hasMembership) {
        List<Product> products = productRepository.findAll();
        double total = 0;

        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        if(hasMembership){
            total *=0.9;
        }else{
            total = calculateCartTotal();
        }
        return total;
    }
}
