// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import store.core.Persistable;
import store.core.StoreEntity;

import java.awt.Color;

/**
 * Abstract base class representing a product sold in the store.
 * Provides common fields and behavior for all product types.
 */
public abstract class Product implements PricedItem, StockManageable, Persistable, StoreEntity {

    private String name;
    private double price;
    private int stock;
    private String description;
    private Category category;
    private Color color;
    private String imagePath;

    /**
     * Creates a new product with the given parameters.
     *
     * @param name product name
     * @param price product price
     * @param stock amount in stock
     * @param description product description
     * @param category product category
     * @param color product color
     * @param imagePath path to product image
     */
    public Product(String name, double price, int stock,
                   String description, Category category,
                   Color color, String imagePath) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.color = color;
        this.imagePath = imagePath;
    }

    /**
     * Returns the product name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the product description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the product category.
     *
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the product color.
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the path to the product image.
     *
     * @return image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Returns the product price.
     *
     * @return price
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets a new price for the product.
     *
     * @param price new price
     * @return true if price is valid and updated
     */
    public boolean setPrice(double price) {
        if (price >= 0) {
            this.price = price;
            return true;
        }
        return false;
    }

    /**
     * Returns the current stock amount.
     *
     * @return stock
     */
    @Override
    public int getStock() {
        return stock;
    }

    /**
     * Increases the stock by the given amount.
     *
     * @param amount amount to add
     * @return true if amount is valid
     */
    @Override
    public boolean increaseStock(int amount) {
        if (amount >= 0) {
            stock += amount;
            return true;
        }
        return false;
    }

    /**
     * Decreases the stock by the given amount.
     *
     * @param amount amount to remove
     * @return true if amount is valid
     */
    @Override
    public boolean decreaseStock(int amount) {
        if (amount >= 0) {
            stock -= amount;
            return true;
        }
        return false;
    }

    /**
     * Returns a short display name for UI usage.
     *
     * @return display name
     */
    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * Returns detailed product information for display.
     *
     * @return detailed description
     */
    @Override
    public String getDisplayDetails() {
        return "Name: " + name +
                "\nPrice: " + price +
                "\nStock: " + stock +
                "\nDescription: " + description +
                "\nCategory: " + category +
                "\nColor: " + color;
    }

    /**
     * Saves the product to a file.
     * Not implemented in this version.
     *
     * @param path file path
     */
    @Override
    public void saveToFile(String path) {
        // optional implementation
    }

    /**
     * Products are equal if name and category are equal.
     *
     * @param o object to compare
     * @return true if products match
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product)) return false;
        Product other = (Product) o;
        return name.equals(other.name) && category == other.category;
    }

    /**
     * Returns a textual representation of the product.
     *
     * @return product string
     */
    @Override
    public String toString() {
        return getDisplayDetails();
    }
}
