// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

/**
 * SystemUpdatable represents a component that can be updated periodically.
 * This is a preparation for multi-threading in the next exercise.
 */
public interface SystemUpdatable {

    /**
     * Performs an update operation.
     */
    void update();
}
