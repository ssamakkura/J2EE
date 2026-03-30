package com.example.baitap.service;

import com.example.baitap.model.Book;
import com.example.baitap.model.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

@Service
@SessionScope
public class CartService {
    private Map<Integer, CartItem> items = new HashMap<>();

    public void addToCart(Book book, int quantity) {
        if (items.containsKey(book.getId())) {
            CartItem existing = items.get(book.getId());
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            items.put(book.getId(), new CartItem(book, quantity));
        }
    }

    public void removeFromCart(int bookId) {
        items.remove(bookId);
    }

    public void updateQuantity(int bookId, int quantity) {
        if (items.containsKey(bookId)) {
            if (quantity <= 0) {
                items.remove(bookId);
            } else {
                items.get(bookId).setQuantity(quantity);
            }
        }
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public double getTotalAmount() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clear() {
        items.clear();
    }
}
