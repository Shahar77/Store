package store.products;

/**
 * Represents a book product.
 */
public class BookProduct extends Product{

    private String author;

    /**
     * Creates a new book product.
     * @param name book name
     * @param price book price
     * @param stock available stock
     * @param author book author
     * @param imagePath image path
     */
    public BookProduct(String name,double price,int stock,String author,String imagePath){
        super(
                name,
                price,
                stock,
                Category.BOOKS,
                "Book written by "+author,
                imagePath
        );
        this.author=author;
    }

    /**
     * @return book author
     */
    public String getAuthor(){
        return author;
    }
}
