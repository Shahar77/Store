package store.engine;

import store.cart.Cart;
import store.orders.Order;
import store.products.Product;

import java.util.ArrayList;
import java.util.List;

public class StoreEngine {

    private List<Product> products;

    public StoreEngine() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        if (products == null) {
            this.products = new ArrayList<>();
        } else {
            this.products = products;
        }
    }

    public void addProduct(Product p) {
        if (p == null) return;
        products.add(p);
    }

    public Order createOrderFromCart(Cart cart) {
        if (cart == null) return null;
        if (cart.getItems().isEmpty()) return null;

        return new Order(
                store.orders.OrderManager.nextId(),
                cart.getItems(),
                cart.getTotalPrice()
        );
    }
    public List<Product> getAllProducts() {
        return products;
    }
    public java.util.List<store.products.Product> getAvailableProducts() {
        java.util.List<store.products.Product> res = new java.util.ArrayList<>();

        for (store.products.Product p : getAllProducts()) {
            if (p != null && p.getStock() > 0) {
                res.add(p);
            }
        }

        return res;
    }


}
