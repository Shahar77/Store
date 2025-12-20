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
 */
public class Order implements Persistable{
    private int orderID;
    private List<CartItem> items;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    /**
     * Creates a new order.
     * @param orderID order id
     * @param items items list
     * @param totalAmount total amount
     */
    public Order(int orderID,List<CartItem> items,double totalAmount){
        this.orderID=orderID;
        this.items=new ArrayList<>(items);
        this.totalAmount=totalAmount;
        status=OrderStatus.NEW;
        createdAt=LocalDateTime.now();
    }

    /**
     * @return order id
     */
    public int getOrderID(){
        return orderID;
    }

    /**
     * @return total amount
     */
    public double getTotalAmount(){
        return totalAmount;
    }

    /**
     * @return status
     */
    public OrderStatus getStatus(){
        return status;
    }

    /**
     * @return created time
     */
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    /**
     * Returns copy of items list.
     * @return items copy
     */
    public List<CartItem> getItems(){
        return new ArrayList<>(items);
    }

    /**
     * Moves status from NEW to PAID.
     * @return true if changed
     */
    public boolean pay(){
        if(status==OrderStatus.NEW){
            status=OrderStatus.PAID;
            return true;
        }
        return false;
    }

    /**
     * Moves status from PAID to SHIPPED.
     * @return true if changed
     */
    public boolean ship(){
        if(status==OrderStatus.PAID){
            status=OrderStatus.SHIPPED;
            return true;
        }
        return false;
    }

    /**
     * Moves status from SHIPPED to DELIVERED.
     * @return true if changed
     */
    public boolean deliver(){
        if(status==OrderStatus.SHIPPED){
            status=OrderStatus.DELIVERED;
            return true;
        }
        return false;
    }

    /**
     * Saves the order to a file (not implemented here).
     * @param path file path
     */
    @Override
    public void saveToFile(String path){
    }

    /**
     * Orders equal if same orderID.
     * @param o other
     * @return true if equal
     */
    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(!(o instanceof Order))return false;
        Order other=(Order)o;
        return orderID==other.orderID;
    }

    /**
     * @return hash code
     */
    @Override
    public int hashCode(){
        return Objects.hash(orderID);
    }

    /**
     * @return summary string
     */
    @Override
    public String toString(){
        return "Order{orderID="+orderID+", status="+status+", totalAmount="+totalAmount+", createdAt="+createdAt+"}";
    }
}
