import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private List<OrderItem> items;
    private LocalDateTime orderDateTime;
    private double totalAmount;

    public Order(String orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.orderDateTime = LocalDateTime.now();
        this.totalAmount = 0.0;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
        recalculateTotalAmount();
    }

    private void recalculateTotalAmount() {
        this.totalAmount = 0;
        for (OrderItem item : items) {
            this.totalAmount += item.getSubtotal();
        }
    }

    public void displayOrderDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\n--- Order Invoice ---");
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDateTime.format(formatter));
        System.out.println("Items Purchased:");
        for (OrderItem item : items) {
            item.displayItemDetails();
        }
        System.out.printf("Total Amount: $%.2f%n", totalAmount);
        System.out.println("--- End of Invoice ---");
    }

    public String toCsvString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return String.join(",", orderId, orderDateTime.format(formatter), String.valueOf(totalAmount));
    }

    public Order(String[] csvData) {
        this.orderId = csvData[0];
        this.orderDateTime = LocalDateTime.parse(csvData[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.totalAmount = Double.parseDouble(csvData[2]);
        this.items = new ArrayList<>(); // Items will be added by OrderManager
    }
}