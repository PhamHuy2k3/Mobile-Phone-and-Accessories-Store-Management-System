public class Accessory extends Product {
    private String accessoryCategory;

    public Accessory(String id, String name, String brand, double price, int quantity, String accessoryCategory) {
        super(id, name, brand, price, quantity);
        this.accessoryCategory = accessoryCategory;
    }

    public String getAccessoryCategory() {
        return accessoryCategory;
    }

    public void setAccessoryCategory(String accessoryCategory) {
        this.accessoryCategory = accessoryCategory;
    }

    @Override
    public String getProductType() {
        return "Accessory";
    }

    @Override
    public void displayDetails() {
        System.out.println("------------------------------------");
        System.out.println("ID: " + id + " (Type: " + getProductType() + ")");
        System.out.println("Name: " + name + ", Brand: " + brand);
        System.out.println("Category: " + accessoryCategory);
        System.out.printf("Price: $%.2f, Quantity: %d%n", price, quantity);
        System.out.println("------------------------------------");
    }

    @Override
    public String toCsvString() {
        return String.join(",",
                getProductType(),
                super.getBaseCsvString(),
                "",
                "",
                "",
                accessoryCategory);
    }

    public Accessory(String[] csvData) {
        super(csvData[1], csvData[2], csvData[3], Double.parseDouble(csvData[4]), Integer.parseInt(csvData[5]));
        this.accessoryCategory = csvData[9];
    }
}