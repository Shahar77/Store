// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import store.core.Persistable;
import store.core.StoreEntity;
import java.awt.Color;

/**
 * Abstract base class for all products in the store.
 * Contains common fields such as name,price,stock and category.
 */
public abstract class Product implements PricedItem,StockManageable,Persistable,StoreEntity{

    private String name;
    private double price;
    private int stock;
    private String description;
    private Category category;
    private Color color;
    private String imagePath;

    /**
     * Creates a new product.
     * @param name product name
     * @param price product price
     * @param stock initial stock
     * @param description product description
     * @param category product category
     * @param color display color
     * @param imagePath path to product image
     */
    public Product(String name,double price,int stock,String description,Category category,Color color,String imagePath){
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.description=description;
        this.category=category;
        this.color=color;
        this.imagePath=imagePath;
    }

    /**
     * @return product name
     */
    public String getName(){
        return name;
    }

    /**
     * @return product description
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return product category
     */
    public Category getCategory(){
        return category;
    }

    /**
     * @return product color
     */
    public Color getColor(){
        return color;
    }

    /**
     * @return product image path
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * @return product price
     */
    @Override
    public double getPrice(){
        return price;
    }

    /**
     * Sets a new price if valid.
     * @param price new price
     * @return true if updated successfully
     */
    @Override
    public boolean setPrice(double price){
        if(price>=0){
            this.price=price;
            return true;
        }
        return false;
    }

    /**
     * @return current stock amount
     */
    @Override
    public int getStock(){
        return stock;
    }

    /**
     * Increases product stock.
     * @param amount amount to add
     * @return true if valid
     */
    @Override
    public boolean increaseStock(int amount){
        if(amount>=0){
            stock+=amount;
            return true;
        }
        return false;
    }

    /**
     * Decreases product stock.
     * @param amount amount to remove
     * @return true if valid
     */
    @Override
    public boolean decreaseStock(int amount){
        if(amount>=0&&stock>=amount){
            stock-=amount;
            return true;
        }
        return false;
    }

    /**
     * @return display name for GUI
     */
    @Override
    public String getDisplayName(){
        return name;
    }

    /**
     * @return detailed display information
     */
    @Override
    public String getDisplayDetails(){
        return "Price: "+price+
               "\nStock: "+stock+
               "\nDescription: "+description+
               "\nCategory: "+category;
    }

    /**
     * Saves the product to a file.
     * Not implemented in this stage.
     * @param path file path
     */
    @Override
    public void saveToFile(String path){
    }

    /**
     * Products are equal if name and category match.
     * @param o other object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Product)){
            return false;
        }
        Product p=(Product)o;
        return name.equals(p.name)&&category==p.category;
    }

    /**
     * @return textual representation of product
     */
    @Override
    public String toString(){
        return "Name: "+name+
               "\nPrice: "+price+
               "\nStock: "+stock+
               "\nDescription: "+description+
               "\nCategory: "+category;
    }
}
