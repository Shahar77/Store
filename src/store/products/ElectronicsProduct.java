// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents an electronic product sold in the store.
 * Extends Product with warranty and brand information.
 */
public class ElectronicsProduct extends Product {

    private int warrantyMonths;
    private String brand;

    /**
     * Creates a new ElectronicsProduct.
     *
     * @param name product name
     * @param price product price
     * @param stock amount in stock
     * @param description product description
     * @param category product category, must be ELECTRONICS
     * @param color display color
     * @param imagePath path to product image
     * @param warrantyMonths warranty duration in months
     * @param brand product brand
     */
    public ElectronicsProduct(String name, double price, int stock,
                              String description, Category category,
                              Color color, String imagePath,
                              int warrantyMonths, String brand) {
        super(name, price, stock, description, Category.ELECTRONICS, color, imagePath);
        this.warrantyMonths = warrantyMonths;
        this.brand = brand;
    }

    /**
     * Returns a detailed textual representation of the electronic product.
     *
     * @return electronics product details
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nWarranty Months: " + warrantyMonths
                + "\nBrand: " + brand;
    }
}
