public class MobilePhone extends Product {
    private double screenSizeInches;
    private int ramGB;
    private int storageGB;

    public MobilePhone(String id, String name, String brand, double price, int quantity,
            double screenSizeInches, int ramGB, int storageGB) {
        super(id, name, brand, price, quantity);
        this.screenSizeInches = screenSizeInches;
        this.ramGB = ramGB;
        this.storageGB = storageGB;
    }

    public double getScreenSizeInches() {
        return screenSizeInches;
    }

    public void setScreenSizeInches(double screenSizeInches) {
        this.screenSizeInches = screenSizeInches;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    public int getStorageGB() {
        return storageGB;
    }

    public void setStorageGB(int storageGB) {
        this.storageGB = storageGB;
    }

    @Override
    public String getProductType() {
        return "Mobile Phone";
    }

    @Override
    public void displayDetails() {
        System.out.println("------------------------------------");
        System.out.println("ID: " + id + " (Type: " + getProductType() + ")");
        System.out.println("Name: " + name + ", Brand: " + brand);
        System.out.printf("Price: $%.2f, Quantity: %d%n", price, quantity);
        System.out
                .println("Screen: " + screenSizeInches + " inches, RAM: " + ramGB + "GB, Storage: " + storageGB + "GB");
        System.out.println("------------------------------------");
    }

    @Override
    public String toCsvString() {
        return String.join(",",
                getProductType(), // Dùng để phân biệt loại khi đọc file
                super.getBaseCsvString(),
                String.valueOf(screenSizeInches),
                String.valueOf(ramGB),
                String.valueOf(storageGB),
                "" // Placeholder for AccessoryCategory
        );
    }

    public MobilePhone(String[] csvData) {
        super(csvData[1], csvData[2], csvData[3], Double.parseDouble(csvData[4]), Integer.parseInt(csvData[5]));
        this.screenSizeInches = Double.parseDouble(csvData[6]);
        this.ramGB = Integer.parseInt(csvData[7]);
        this.storageGB = Integer.parseInt(csvData[8]);
        // csvData[9] would be the placeholder for AccessoryCategory, which is ignored
        // here.
    }
}