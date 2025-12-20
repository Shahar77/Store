// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.orders;

import store.cart.CartItem;
import store.core.Persistable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single order in the store system.
 * Contains items,total amount,status and creation date.
 */
public class Order implements Persistable{

    private int orderID;
    private List<CartItem> items;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    /**
     * Creates a new order.
     * @param orderID unique order identifier
     * @param items list of cart items
     * @param totalAmount total price of the order
     */
    public Order(int orderID,List<CartItem> items,double totalAmount){
        this.orderID=orderID;
        this.items=new ArrayList<>();
        for(int i=0;i<items.size();i++){
            this.items.add(items.get(i));
        }
        this.totalAmount=totalAmount;
        this.status=OrderStatus.NEW;
        this.createdAt=LocalDateTime.now();
    }

    /**
     * @return order ID
     */
    public int getOrderID(){
        return orderID;
    }

    /**
     * @return total amount of the order
     */
    public double getTotalAmount(){
        return totalAmount;
    }

    /**
     * @return order status
     */
    public OrderStatus getStatus(){
        return status;
    }

    /**
     * @return creation date and time
     */
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    /**
     * Returns a copy of the order items.
     * @return list of cart items
     */
    public List<CartItem> getItems(){
        return new ArrayList<>(items);
    }

    /**
     * Changes order status from NEW to PAID.
     * @return true if status changed
     */
    public boolean pay(){
        if(status==OrderStatus.NEW){
            status=OrderStatus.PAID;
            return true;
        }
        return false;
    }

    /**
     * Changes order status from PAID to SHIPPED.
     * @return true if status changed
     */
    public boolean ship(){
        if(status==OrderStatus.PAID){
            status=OrderStatus.SHIPPED;
            return true;
        }
        return false;
    }

    /**
     * Changes order status from SHIPPED to DELIVERED.
     * @return true if status changed
     */
    public boolean deliver(){
        if(status==OrderStatus.SHIPPED){
            status=OrderStatus.DELIVERED;
            return true;
        }
        return false;
    }

    /**
     * Saves the order to a file.
     * Implementation will be handled by IO classes.
     * @param path file path
     */
    @Override
    public void saveToFile(String path){
    }

    /**
     * Orders are equal if they have the same ID.
     * @param o other object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(!(o instanceof Order)){
            return false;
        }
        Order other=(Order)o;
        return orderID==other.orderID;
    }

    /**
     * @return hash code based on order ID
     */
    @Override
    public int hashCode(){
        return Objects.hash(orderID);
    }

    /**
     * @return textual representation of the order
     */
    @Override
    public String toString(){
        return "Order{orderID="+orderID+
               ",status="+status+
               ",totalAmount="+totalAmount+
               ",createdAt="+createdAt+"}";
    }
}
