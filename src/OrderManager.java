import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderManager {
    private List<Order> orders;
    private static final AtomicInteger orderIdCounter = new AtomicInteger(0);
    // private final String ordersFilePath; // Removed
    // private final String orderItemsFilePath; // Removed

    public OrderManager(InventoryManager inventoryManager) { // Removed ordersFilePath, orderItemsFilePath parameters
        this.orders = new ArrayList<>();
        // this.ordersFilePath = ordersFilePath; // Removed
        // this.orderItemsFilePath = orderItemsFilePath; // Removed
        // loadOrdersFromFile(inventoryManager); // Removed call
    }

    public String generateNextOrderId() {
        return "ORD" + String.format("%04d", orderIdCounter.incrementAndGet());
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> findOrderById(String orderId) {
        return orders.stream()
                .filter(o -> o.getOrderId().equalsIgnoreCase(orderId))
                .findFirst();
    }

    // private void loadOrdersFromFile(InventoryManager inventoryManager) { ... } //
    // Method removed

    // public void saveOrdersToFile() { ... } // Method removed
}