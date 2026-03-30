package com.example.baitap.controller;

import com.example.baitap.model.Book;
import com.example.baitap.service.BookService;
import com.example.baitap.service.CartService;
import com.example.baitap.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotalAmount());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam int bookId, @RequestParam int quantity) {
        Book book = bookService.getBookById(bookId);
        if (book != null) {
            cartService.addToCart(book, quantity);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable int id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam int bookId, @RequestParam int quantity) {
        cartService.updateQuantity(bookId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String showCheckout(Model model) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("total", cartService.getTotalAmount());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@RequestParam String name, @RequestParam String address, @RequestParam String phone, Model model) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/books";
        }
        
        com.example.baitap.model.Order order = orderService.placeOrder(name, address, phone, cartService.getItems());
        cartService.clear();
        
        model.addAttribute("order", order);
        return "checkout_success";
    }
}
