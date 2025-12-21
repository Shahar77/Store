// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.engine;

import store.cart.Cart;
import store.cart.CartItem;
import store.orders.Order;
import store.products.Product;

import java.util.ArrayList;
import java.util.List;
import store.orders.OrderManager;


/**
 * Central business logic of the store system.
 * Responsible for managing products and creating orders.
 */
public class StoreEngine {

    private List<Product> products;
    private int nextOrderId;
    private OrderManager orderManager;


    /**
     * Creates a new StoreEngine with empty product list
     * and initializes the order ID counter.
     */
    public StoreEngine() {
        this.products = new ArrayList<>();
        this.nextOrderId = 1;
        orderManager = new OrderManager();

    }

    /**
     * Adds a product to the store.
     *
     * @param p product to add
     */
    public void addProduct(Product p) {
        if (p != null) {
            products.add(p);
        }
    }

    /**
     * Returns all products that are currently in stock.
     *
     * @return list of available products
     */
    public List<Product> getAvailableProducts() {
        List<Product> available = new ArrayList<>();
        for (Product p : products) {
            if (p.getStock() > 0) {
                available.add(p);
            }
        }
        return available;
    }

    /**
     * Creates an order from the given shopping cart.
     * The cart will be cleared after successful order creation.
     *
     * @param cart shopping cart
     * @return created Order or null if cart is empty or null
     */
    public Order createOrderFromCart(Cart cart) {
        if (cart == null || cart.getItems().isEmpty()) {
            return null;
        }

        List<CartItem> itemsCopy = new ArrayList<>();
        for (CartItem ci : cart.getItems()) {
            itemsCopy.add(ci);
        }

        Order order = new Order(
                nextOrderId++,
                itemsCopy,
                cart.calculateTotal()
        );

        cart.clear();
        return order;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }
    /**
     * Returns a copy of all products in the store (including out of stock).
     *
     * @return list of all products
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Clears current products and replaces them with the given list.
     *
     * @param newProducts products to set
     */
    public void setProducts(List<Product> newProducts) {
        products.clear();
        if (newProducts != null) {
            products.addAll(newProducts);
        }
    }

}
