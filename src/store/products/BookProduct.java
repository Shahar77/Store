// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents a book product sold in the store.
 * Extends Product with author and page count information.
 */
public class BookProduct extends Product {

    private String author;
    private int pages;

    /**
     * Creates a new BookProduct.
     *
     * @param name book title
     * @param price book price
     * @param stock amount in stock
     * @param description book description
     * @param category product category, must be BOOKS
     * @param color display color
     * @param imagePath path to product image
     * @param author book author
     * @param pages number of pages
     */
    public BookProduct(String name, double price, int stock,
                       String description, Category category,
                       Color color, String imagePath,
                       String author, int pages) {
        super(name, price, stock, description, Category.BOOKS, color, imagePath);
        this.author = author;
        this.pages = pages;
    }

    /**
     * Returns a detailed textual representation of the book.
     *
     * @return book details string
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nAuthor: " + author
                + "\nPages: " + pages;
    }
}
