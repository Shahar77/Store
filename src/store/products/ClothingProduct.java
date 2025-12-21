// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents a clothing product sold in the store.
 * Extends Product with clothing size information.
 */
public class ClothingProduct extends Product {

    private String size;

    /**
     * Creates a new ClothingProduct.
     *
     * @param name product name
     * @param price product price
     * @param stock amount in stock
     * @param description product description
     * @param category product category, must be CLOTHING
     * @param color display color
     * @param imagePath path to product image
     * @param size clothing size
     */
    public ClothingProduct(String name, double price, int stock,
                           String description, Category category,
                           Color color, String imagePath,
                           String size) {
        super(name, price, stock, description, Category.CLOTHING, color, imagePath);
        this.size = size;
    }

    /**
     * Returns a detailed textual representation of the clothing product.
     *
     * @return clothing product details
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nSize: " + size;
    }
}
