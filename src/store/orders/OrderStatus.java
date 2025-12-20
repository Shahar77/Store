// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.orders;

/**
 * Enumeration representing the different states of an order.
 * Describes the life cycle of an order in the system.
 */
public enum OrderStatus {

    /** Order was created but not paid yet */
    NEW,

    /** Payment was completed successfully */
    PAID,

    /** Order was shipped to the customer */
    SHIPPED,

    /** Order was delivered to the customer */
    DELIVERED
}
