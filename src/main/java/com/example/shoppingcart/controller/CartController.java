package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product product) {
        cartService.addProduct(product);
        return product.getName();
    }

    @GetMapping("/get-all-cart-items")
    public List<Product> getAllCartItems() {
        return cartService.getAllProducts();
    }

    @DeleteMapping("/delete-product-by-name/{name}")
    public void deleteByName(@PathVariable("name") String name) {
        cartService.deleteByName(name);
    }

    @GetMapping("/total")
    public double calculateTotal() {
        return cartService.calculateCartTotal();
    }
}