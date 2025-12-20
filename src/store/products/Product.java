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
     * Returns product name.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns product description.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Returns product category.
     */
    public Category getCategory(){
        return category;
    }

    /**
     * Returns product color.
     */
    public Color getColor(){
        return color;
    }

    /**
     * Returns image path.
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * Returns product price.
     */
    public double getPrice(){
        return price;
    }

    /**
     * Updates product price.
     */
    public boolean setPrice(double price){
        if(price<0)return false;
        this.price=price;
        return true;
    }

    /**
     * Returns current stock.
     */
    public int getStock(){
        return stock;
    }

    /**
     * Increases stock.
     */
    public boolean increaseStock(int amount){
        if(amount<0)return false;
        stock+=amount;
        return true;
    }

    /**
     * Decreases stock.
     */
    public boolean decreaseStock(int amount){
        if(amount<0||amount>stock)return false;
        stock-=amount;
        return true;
    }

    /**
     * Returns display name for UI.
     */
    public String getDisplayName(){
        return name;
    }

    /**
     * Returns detailed display string.
     */
    public String getDisplayDetails(){
        return "Price: "+price+
               "\nStock: "+stock+
               "\nCategory: "+category+
               "\nDescription: "+description;
    }

    /**
     * Saves product to file.
     */
    public void saveToFile(String path){
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Product))return false;
        Product p=(Product)o;
        return name.equals(p.name)&&category==p.category;
    }

    @Override
    public String toString(){
        return getDisplayDetails();
    }
}
