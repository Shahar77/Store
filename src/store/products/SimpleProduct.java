// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * SimpleProduct is a basic product type used mainly for loading products from files.
 * It does not add extra fields beyond the base Product fields.
 */
public class SimpleProduct extends Product {

    /**
     * Creates a new SimpleProduct.
     *
     * @param name product name
     * @param price product price
     * @param stock amount in stock
     * @param description product description
     * @param category product category
     * @param color display color
     * @param imagePath path to image file
     */
    public SimpleProduct(String name, double price, int stock,
                         String description, Category category,
                         Color color, String imagePath) {
        super(name, price, stock, description, category, color, imagePath);
    }
}
