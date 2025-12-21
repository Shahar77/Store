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
import store.products.StockManageable;

import java.util.ArrayList;
import java.util.List;

/**
 * Central engine of the store system.
 * Manages products, customers, cart and order creation.
 * Acts as the API layer between Model and GUI.
 */
public class StoreEngine{

    private List<Product> products;
    private List<Order> allOrders;
    private List<Customer> customers;
    private Cart cart;
    private int nextOrderId;
    private Customer activeCustomer;

    /**
     * Creates a new StoreEngine with empty collections.
     */
    public StoreEngine(){
        products=new ArrayList<>();
        allOrders=new ArrayList<>();
        customers=new ArrayList<>();
        cart=new Cart();
        nextOrderId=1;
    }

    /**
     * Adds a product to the store.
     * @param p product to add
     */
    public void addProduct(Product p){
        if(p!=null){
            products.add(p);
        }
    }

    /**
     * Returns all products in the store.
     * @return list of all products
     */
    public List<Product> getAllProducts(){
        return new ArrayList<>(products);
    }

    /**
     * Returns only products with stock greater than zero.
     * @return list of available products
     */
    public List<Product> getAvailableProducts(){
        List<Product> available=new ArrayList<>();
        for(int i=0;i<products.size();i++){
            Product p=products.get(i);
            if(p instanceof StockManageable){
                if(((StockManageable)p).getStock()>0){
                    available.add(p);
                }
            }
        }
        return available;
    }

    /**
     * Registers a new customer if username is unique.
     * @param c customer to register
     * @return true if registered,false otherwise
     */
    public boolean registerCustomer(Customer c){
        if(c==null){
            return false;
        }
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getUsername().equals(c.getUsername())){
                return false;
            }
        }
        customers.add(c);
        return true;
    }

    /**
     * Returns the active shopping cart.
     * @return cart instance
     */
    public Cart getCart(){
        return cart;
    }

    /**
     * Adds one unit of a product to the cart.
     * Checks stock only if product supports stock management.
     * @param p product to add
     * @return true if added successfully
     */
    public boolean addToCart(Product p){
        if(p==null){
            return false;
        }
        if(p instanceof StockManageable){
            if(((StockManageable)p).getStock()<=0){
                return false;
            }
        }
        return cart.addItem(p,1);
    }

    /**
     * Creates an order from the current cart.
     * Copies cart items, saves order and clears cart.
     * @return created Order or null if cart is empty
     */
    public Order checkout(){
        if(cart.getItems().isEmpty()){
            return null;
        }
        double total=cart.calculateTotal();
        List<CartItem> itemsCopy=new ArrayList<>();
        for(int i=0;i<cart.getItems().size();i++){
            itemsCopy.add(cart.getItems().get(i));
        }
        Order order=new Order(nextOrderId,itemsCopy,total);
        allOrders.add(order);
        nextOrderId++;
        cart.clear();
        return order;
    }

    /**
     * Sets the currently active customer.
     * @param customer active customer
     */
    public void setActiveCustomer(Customer customer){
        this.activeCustomer=customer;
    }

    /**
     * @return currently active customer
     */
    public Customer getActiveCustomer(){
        return activeCustomer;
    }

    /**
     * Returns all orders created in the system.
     * @return list of orders
     */
    public List<Order> getOrderHistory(){
        return new ArrayList<>(allOrders);
    }
}
