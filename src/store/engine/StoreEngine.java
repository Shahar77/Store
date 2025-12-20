// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.engine;

import store.cart.Cart;
import store.cart.CartItem;
import store.core.Customer;
import store.orders.Order;
import store.products.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Central logic engine of the store.
 * Manages products, customers and orders.
 */
public class StoreEngine{
    private List<Product> products;
    private List<Order> allOrders;
    private List<Customer> customers;
    private int nextOrderId;

    /**
     * Creates a new store engine.
     */
    public StoreEngine(){
        products=new ArrayList<>();
        allOrders=new ArrayList<>();
        customers=new ArrayList<>();
        nextOrderId=1;
    }

    /**
     * Adds a product to the store.
     * @param p product to add
     */
    public void addProduct(Product p){
        products.add(p);
    }

    /**
     * Returns all available products.
     * @return list of products with stock>0
     */
    public List<Product> getAvailableProducts(){
        List<Product> res=new ArrayList<>();
        for(Product p:products){
            if(p.getStock()>0)res.add(p);
        }
        return res;
    }

    /**
     * Registers a new customer.
     * @param c customer
     * @return true if unique username
     */
    public boolean registerCustomer(Customer c){
        for(Customer ex:customers){
            if(ex.getUsername().equals(c.getUsername()))return false;
        }
        customers.add(c);
        return true;
    }

    /**
     * Creates an order from a cart.
     * @param cart cart to checkout
     * @return created order
     */
    public Order createOrderFromCart(Cart cart){
        List<CartItem> copy=new ArrayList<>(cart.getItems());
        Order order=new Order(nextOrderId++,copy,cart.calculateTotal());
        allOrders.add(order);
        return order;
    }
}
