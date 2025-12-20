// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

/**
 * Interface for objects that can be saved to a file.
 * Implementing classes define how their data is persisted.
 */
public interface Persistable {

    /**
     * Saves the object to a file at the given path.
     *
     * @param path destination file path
     */
    void saveToFile(String path);
}
