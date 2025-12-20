// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents a book product.
 * Includes author name and number of pages.
 */
public class BookProduct extends Product{

    private String author;
    private int pages;

    /**
     * Creates a new book product.
     * @param name book title
     * @param price book price
     * @param stock amount in stock
     * @param description book description
     * @param color display color
     * @param imagePath path to image
     * @param author book author
     * @param pages number of pages
     */
    public BookProduct(String name,double price,int stock,String description,Color color,String imagePath,String author,int pages){
        super(name,price,stock,description,Category.BOOKS,color,imagePath);
        this.author=author;
        this.pages=pages;
    }

    /**
     * @return book author
     */
    public String getAuthor(){
        return author;
    }

    /**
     * @return number of pages
     */
    public int getPages(){
        return pages;
    }

    /**
     * @return detailed book description
     */
    @Override
    public String toString(){
        return super.toString()+
               "\nAuthor: "+author+
               "\nPages: "+pages;
    }
}
