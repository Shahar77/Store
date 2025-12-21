package store.products;

import store.core.StoreEntity;

/**
 * Abstract base class for all products.
 */
public abstract class Product implements StoreEntity,StockManageable{

    private String name;
    private double price;
    private int stock;
    private Category category;
    private String description;
    private String imagePath;

    public Product(String name,double price,int stock,Category category,String description,String imagePath){
        this.name=name;
        this.price=price;
        this.stock=stock;
        this.category=category;
        this.description=description;
        this.imagePath=imagePath;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public int getStock(){
        return stock;
    }

    public Category getCategory(){
        return category;
    }

    public String getDescription(){
        return description;
    }

    public String getImagePath(){
        return imagePath;
    }

    /**
     * Decreases stock if possible.
     */
    @Override
    public boolean decreaseStock(int amount){
        if(amount<=0||stock<amount){
            return false;
        }
        stock-=amount;
        return true;
    }

    @Override
    public String getDisplayName(){
        return name;
    }

    @Override
    public String getDisplayDetails(){
        return description+" | ₪"+price;
    }
}
