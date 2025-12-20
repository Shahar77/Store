// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.cart;

import store.products.Product;

/**
 * Represents a single item inside the shopping cart.
 */
public class CartItem{
    private Product product;
    private int quantity;

    /**
     * Creates a new cart item.
     * @param product product to store
     * @param quantity quantity amount
     */
    public CartItem(Product product,int quantity){
        this.product=product;
        this.quantity=quantity;
    }

    /**
     * Returns the product.
     */
    public Product getProduct(){
        return product;
    }

    /**
     * Returns quantity.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Updates quantity.
     * @param q new quantity
     * @return true if valid
     */
    public boolean setQuantity(int q){
        if(q<=0)return false;
        quantity=q;
        return true;
    }

    /**
     * Calculates total price for this item.
     */
    public double getTotalPrice(){
        return product.getPrice()*quantity;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof CartItem))return false;
        CartItem other=(CartItem)o;
        return product.equals(other.product);
    }
}

