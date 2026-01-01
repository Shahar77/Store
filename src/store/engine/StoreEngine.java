package store.engine;

import store.cart.Cart;
import store.orders.Order;
import store.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Core engine of the store system.
 * Responsible for managing products and creating orders from carts.
 */
public class StoreEngine {

    /**
     * List of all products in the store.
     */
    private List<Product> products;

    /**
     * Creates a new StoreEngine with an empty product list.
     */
    public StoreEngine() {
        this.products=new ArrayList<>();
    }

    /**
     * Returns the list of all products.
     *
     * @return list of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the product list.
     * If the given list is null, initializes an empty list.
     *
     * @param products list of products to set
     */
    public void setProducts(List<Product> products) {
        if(products==null){
            this.products=new ArrayList<>();
        } else {
            this.products=products;
        }
    }

    /**
     * Adds a product to the store.
     * Ignores null values.
     *
     * @param p product to add
     */
    public void addProduct(Product p) {
        if(p==null){
            return;
        }
        products.add(p);
    }

    /**
     * Creates an order from a given cart.
     * Returns null if the cart is null or empty.
     *
     * @param cart shopping cart
     * @return created order or null if not valid
     */
    public Order createOrderFromCart(Cart cart) {
        if(cart==null){
            return null;
        }
        if(cart.getItems().isEmpty()){
            return null;
        }

        return new Order(
                store.orders.OrderManager.nextId(),
                cart.getItems(),
                cart.getTotalPrice()
        );
    }

    /**
     * Returns all products in the store.
     *
     * @return list of all products
     */
    public List<Product> getAllProducts() {
        return products;
    }

    /**
     * Returns only products that are available in stock.
     *
     * @return list of available products
     */
    public List<Product> getAvailableProducts() {
        List<Product> res=new ArrayList<>();

        for(Product p:getAllProducts()){
            if(p!=null && p.getStock()>0){
                res.add(p);
            }
        }

        return res;
    }
}
