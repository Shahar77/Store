// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.products;

import store.core.Persistable;
import store.core.StoreEntity;
import java.awt.Color;

/**
 * Abstract base class for all products.
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
     * Constructs a product.
     */
    public Product(String name,double price,int stock,String description,
                   Category category,Color color,String imagePath){
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.description=description;
        this.category=category;
        this.color=color;
        this.imagePath=imagePath;
    }

    public String getName(){return name;}
    public String getDescription(){return description;}
    public Category getCategory(){return category;}
    public Color getColor(){return color;}
    public String getImagePath(){return imagePath;}

    @Override
    public double getPrice(){return price;}

    @Override
    public boolean setPrice(double price){
        if(price>=0){this.price=price;return true;}
        return false;
    }

    @Override
    public int getStock(){return stock;}

    @Override
    public boolean increaseStock(int amount){
        if(amount>=0){stock+=amount;return true;}
        return false;
    }

    @Override
    public boolean decreaseStock(int amount){
        if(amount>=0){stock-=amount;return true;}
        return false;
    }

    @Override
    public String getDisplayName(){
        return name;
    }

    @Override
    public String getDisplayDetails(){
        return toString();
    }

    @Override
    public void saveToFile(String path){}

    @Override
    public String toString(){
        return "Name: "+name+
               "\nPrice: "+price+
               "\nStock: "+stock+
               "\nDescription: "+description+
               "\nCategory: "+category;
    }
}

    public String toString() {
        return getDisplayDetails();
    }
}
