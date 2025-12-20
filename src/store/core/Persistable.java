// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

/**
 * Interface for objects that can be saved to a file.
 */
public interface Persistable{
    /**
     * Saves the object to a given file path.
     * @param path destination path
     */
    void saveToFile(String path);
}

