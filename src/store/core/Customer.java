// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

import store.cart.Cart;
import store.products.Product;
import store.products.StockManageable;

/**
 * Represents a customer in the store system.
 * A customer owns a shopping cart and can add products to it.
 */
public class Customer extends User{

    private Cart cart;

    /**
     * Creates a new customer.
     * @param username unique username
     */
    public Customer(String username){
        super(username);
        cart=new Cart();
    }

    /**
     * Returns the customer's shopping cart.
     * @return cart
     */
    public Cart getCart(){
        return cart;
    }

    /**
     * Adds a product to the cart with a given quantity.
     * Checks stock only if the product supports stock management.
     * @param p product to add
     * @param quantity quantity to add
     * @return true if added successfully
     */
    public boolean addToCart(Product p,int quantity){
        if(p==null||quantity<=0){
            return false;
        }
        if(p instanceof StockManageable){
            if(((StockManageable)p).getStock()<quantity){
                return false;
            }
        }
        return cart.addItem(p,quantity);
    }
}
