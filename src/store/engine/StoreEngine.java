// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.engine;

import store.cart.Cart;
import store.cart.CartItem;
import store.core.Customer;
import store.orders.Order;
import store.orders.OrderManager;
import store.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Central logic engine of the store.
 * Manages products, customers and creating orders.
 */
public class StoreEngine{
    private List<Product> products;
    private List<Order> allOrders;
    private List<Customer> customers;
    private int nextOrderId;
    private Customer activeCustomer;

    /**
     * Creates a new engine with empty lists.
     */
    public StoreEngine(){
        products=new ArrayList<>();
        allOrders=new ArrayList<>();
        customers=new ArrayList<>();
        nextOrderId=1;
        activeCustomer=null;
    }

    /**
     * Sets active customer (current session).
     * @param customer active customer
     */
    public void setActiveCustomer(Customer customer){
        activeCustomer=customer;
    }

    /**
     * @return active customer or null
     */
    public Customer getActiveCustomer(){
        return activeCustomer;
    }

    /**
     * Adds a new product into the store.
     * @param p product to add
     */
    public void addProduct(Product p){
        products.add(p);
    }

    /**
     * Returns only products that still have stock.
     * @return list of available products
     */
    public List<Product> getAvailableProducts(){
        List<Product> available=new ArrayList<>();
        for(int i=0;i<products.size();i++){
            Product p=products.get(i);
            if(p.getStock()>0)available.add(p);
        }
        return available;
    }

    /**
     * Registers a customer if the username is not taken.
     * @param c customer
     * @return true if registered
     */
    public boolean registerCustomer(Customer c){
        for(int i=0;i<customers.size();i++){
            Customer existing=customers.get(i);
            if(existing.getUsername().equals(c.getUsername()))return false;
        }
        customers.add(c);
        return true;
    }

    /**
     * @return current cart of the active customer
     */
    public Cart getCart(){
        if(activeCustomer==null)return null;
        return activeCustomer.getCart();
    }

    /**
     * Adds a product to active customer's cart.
     * @param p product
     * @param quantity quantity
     * @return true if added
     */
    public boolean addToCart(Product p,int quantity){
        if(activeCustomer==null)return false;
        return activeCustomer.addToCart(p,quantity);
    }

    /**
     * Turns a cart into an order and stores it.
     * @param cart cart
     * @return new order
     */
    public Order createOrderFromCart(Cart cart){
        if(cart==null||cart.getItems().isEmpty())return null;
        double totalAmount=cart.calculateTotal();
        List<CartItem> itemsCopy=new ArrayList<>(cart.getItems());
        Order newOrder=new Order(nextOrderId++,itemsCopy,totalAmount);
        allOrders.add(newOrder);
        OrderManager.add(newOrder);
        return newOrder;
    }

    /**
     * @return all orders created in this engine
     */
    public List<Order> getAllOrders(){
        return new ArrayList<>(allOrders);
    }
}
