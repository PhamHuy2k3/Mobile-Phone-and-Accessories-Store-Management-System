import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryManager {
    private List<Product> products;
    private static final AtomicInteger phoneIdCounter = new AtomicInteger(0);
    private static final AtomicInteger accessoryIdCounter = new AtomicInteger(0);
    // private final String productsFilePath; // Removed

    public InventoryManager() { // Removed productsFilePath parameter
        this.products = new ArrayList<>();
        // this.productsFilePath = productsFilePath; // Removed
        // loadProductsFromFile(); // Removed call
    }

    public String generateNextPhoneId() {
        return "PH" + phoneIdCounter.incrementAndGet();
    }

    public String generateNextAccessoryId() {
        return "AC" + accessoryIdCounter.incrementAndGet();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findProductById(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public boolean deleteProduct(String id) {
        return products.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    public boolean decreaseProductQuantity(String productId, int quantityToDecrease) {
        Optional<Product> productOpt = findProductById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (product.getQuantity() >= quantityToDecrease) {
                product.setQuantity(product.getQuantity() - quantityToDecrease);
                return true;
            }
        }
        return false;
    }

    // private void loadProductsFromFile() { ... } // Method removed

    // public void saveProductsToFile() { ... } // Method removed
}