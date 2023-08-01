package domain.models;

import domain.enums.ProductType;


public class Product {
    private long productId;
    private String name;
    private ProductType category;
    private double price;



    public Product() {
    }

    public Product(long productId, String name, ProductType category, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getCategory() {
        return category;
    }

    public void setCategory(ProductType category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

public double getDiscount(){
        return price - (price * 0.10);
    }


    @Override
    public String toString() {
        return "Product -> " +
                "id =" + productId +
                ", name ='" + name + '\'' +
                ", category =" + category +
                ", price = $" + price +
                "||";
    }



}
