// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.cart;

import store.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart for a customer.
 */
public class Cart{
    private List<CartItem> items;

    /**
     * Creates an empty cart.
     */
    public Cart(){
        items=new ArrayList<>();
    }

    /**
     * Adds a product with quantity.
     * If product already exists, increases its quantity.
     * @param p product
     * @param quantity quantity
     * @return true if added
     */
    public boolean addItem(Product p,int quantity){
        if(quantity<=0||p==null)return false;
        for(int i=0;i<items.size();i++){
            CartItem ci=items.get(i);
            if(ci.getProduct().equals(p)){
                ci.setQuantity(ci.getQuantity()+quantity);
                return true;
            }
        }
        items.add(new CartItem(p,quantity));
        return true;
    }

    /**
     * Removes a product from the cart.
     * @param p product
     * @return true if removed
     */
    public boolean removeItem(Product p){
        if(p==null)return false;
        for(int i=0;i<items.size();i++){
            CartItem ci=items.get(i);
            if(ci.getProduct().equals(p)){
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Decreases quantity of a product in the cart by 1.
     * If quantity becomes 0, removes the item.
     * @param p product
     * @return true if changed
     */
    public boolean decreaseItem(Product p){
        if(p==null)return false;
        for(int i=0;i<items.size();i++){
            CartItem ci=items.get(i);
            if(ci.getProduct().equals(p)){
                if(ci.getQuantity()>1){
                    ci.setQuantity(ci.getQuantity()-1);
                }else{
                    items.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates total price of all items.
     * @return total
     */
    public double calculateTotal(){
        double total=0;
        for(int i=0;i<items.size();i++){
            total+=items.get(i).getTotalPrice();
        }
        return total;
    }

    /**
     * Clears the cart.
     */
    public void clear(){
        items.clear();
    }

    /**
     * @return list of items
     */
    public List<CartItem> getItems(){
        return items;
    }
}

    /**
     * Returns the list of cart items.
     *
     * @return list of CartItem objects
     */
    public List<CartItem> getItems() {
        return items;
    }
}
