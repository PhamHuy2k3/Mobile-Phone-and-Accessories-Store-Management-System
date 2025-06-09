public abstract class Product {
    protected String id;
    protected String name;
    protected String brand;
    protected double price;
    protected int quantity;

    public Product(String id, String name, String brand, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract String getProductType();

    public abstract void displayDetails();

    public abstract String toCsvString();

    protected String getBaseCsvString() {
        return String.join(",",
                id,
                name,
                brand,
                String.valueOf(price),
                String.valueOf(quantity));
    }

    // Factory method for creating Product from CSV will be handled by subclasses
    // or a dedicated parser in InventoryManager due to specific fields.
}