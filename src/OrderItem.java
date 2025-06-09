public class OrderItem {
    private Product product;
    private int quantitySold;
    private double priceAtSale;

    public OrderItem(Product product, int quantitySold) {
        this.product = product;
        this.quantitySold = quantitySold;
        this.priceAtSale = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getPriceAtSale() {
        return priceAtSale;
    }

    public double getSubtotal() {
        return quantitySold * priceAtSale;
    }

    public void displayItemDetails() {
        System.out.printf("  - Item: %s (ID: %s), Quantity: %d, Unit Price: $%.2f, Subtotal: $%.2f%n",
                product.getName(), product.getId(), quantitySold, priceAtSale, getSubtotal());
    }

    // orderId is passed to link this item to its order in the CSV
    public String toCsvString(String orderId) {
        return String.join(",", orderId, product.getId(), String.valueOf(quantitySold), String.valueOf(priceAtSale));
    }

    // Product needs to be fetched from InventoryManager using productId
    public OrderItem(Product product, String[] csvData) {
        this.product = product; // Product is resolved by OrderManager during loading
        this.quantitySold = Integer.parseInt(csvData[2]);
        this.priceAtSale = Double.parseDouble(csvData[3]);
    }
}