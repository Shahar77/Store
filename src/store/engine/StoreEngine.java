// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

/**
 * Represents something that can be displayed in the UI with a name and details.
 */
public interface StoreEntity{
    /**
     * @return short display name
     */
    String getDisplayName();

    /**
     * @return detailed display string
     */
    String getDisplayDetails();
}
