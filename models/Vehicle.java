package models;
public class Vehicle {
    private String id;
    private String name; 
    private String color; 
    private double price; 
    private String brand; 
    private String type; 
    private String productYear;
    
    public Vehicle() {

    }

    public Vehicle(String id, String name, String color, double price, String brand, String type, String productYear) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.productYear = productYear;
    }

    public String getName(String name) {
        return name;
    }

    public void setName() {
        return name;
    }
}
