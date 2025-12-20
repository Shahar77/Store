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
 * Central logic engine of the store system.
 * Manages products, customers and order creation.
 */
public class StoreEngine{
    private List<Product> products;
    private List<Order> orders;
    private List<Customer> customers;
    private int nextOrderId;
    private Customer activeCustomer;

    /**
     * Creates a new StoreEngine with empty collections.
     */
    public StoreEngine(){
        products=new ArrayList<>();
        orders=new ArrayList<>();
        customers=new ArrayList<>();
        nextOrderId=1;
        activeCustomer=null;
    }

    /**
     * Sets the active customer for the current session.
     * @param customer active customer
     */
    public void setActiveCustomer(Customer customer){
        activeCustomer=customer;
    }

    /**
     * Returns the currently active customer.
     * @return active customer or null
     */
    public Customer getActiveCustomer(){
        return activeCustomer;
    }

    /**
     * Adds a product to the store catalog.
     * @param product product to add
     */
    public void addProduct(Product product){
        if(product!=null){
            products.add(product);
        }
    }

    /**
     * Returns all products that are in stock.
     * @return list of available products
     */
    public List<Product> getAvailableProducts(){
        List<Product> available=new ArrayList<>();
        for(int i=0;i<products.size();i++){
            Product p=products.get(i);
            if(p.getStock()>0){
                available.add(p);
            }
        }
        return available;
    }

    /**
     * Registers a new customer if username is unique.
     * @param customer customer to register
     * @return true if registration succeeded
     */
    public boolean registerCustomer(Customer customer){
        if(customer==null)return false;
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getUsername().equals(customer.getUsername())){
                return false;
            }
        }
        customers.add(customer);
        return true;
    }

    /**
     * Returns the cart of the active customer.
     * @return cart or null if no active customer
     */
    public Cart getCart(){
        if(activeCustomer==null)return null;
        return activeCustomer.getCart();
    }

    /**
     * Adds a product to the active customer's cart.
     * @param product product to add
     * @param quantity quantity
     * @return true if added successfully
     */
    public boolean addToCart(Product product,int quantity){
        if(activeCustomer==null)return false;
        return activeCustomer.addToCart(product,quantity);
    }

    /**
     * Creates an order from the given cart.
     * @param cart cart to convert
     * @return created order or null
     */
    public Order createOrderFromCart(Cart cart){
        if(cart==null||cart.getItems().isEmpty())return null;
        double total=cart.calculateTotal();
        List<CartItem> itemsCopy=new ArrayList<>(cart.getItems());
        Order order=new Order(nextOrderId++,itemsCopy,total);
        orders.add(order);
        return order;
    }

    /**
     * Returns all orders created by the engine.
     * @return list of orders
     */
    public List<Order> getAllOrders(){
        return new ArrayList<>(orders);
    }
}
