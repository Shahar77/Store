// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

/**
 * Represents an entity that can be displayed in the store UI.
 * Provides basic textual information for presentation purposes.
 */
public interface StoreEntity {

    /**
     * Returns a short display name of the entity.
     *
     * @return display name
     */
    String getDisplayName();

    /**
     * Returns detailed textual information about the entity.
     *
     * @return detailed description
     */
    String getDisplayDetails();
}
