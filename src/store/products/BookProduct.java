// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import java.awt.Color;

/**
 * Represents a book product.
 */
public class BookProduct extends Product{
    private String author;
    private int pages;

    /**
     * Creates a new book product.
     */
    public BookProduct(String name,double price,int stock,String description,Color color,String imagePath,String author,int pages){
        super(name,price,stock,description,Category.BOOKS,color,imagePath);
        this.author=author;
        this.pages=pages;
    }

    /**
     * Returns detailed book info.
     */
    @Override
    public String getDisplayDetails(){
        return super.getDisplayDetails()+
               "\nAuthor: "+author+
               "\nPages: "+pages;
    }
}
