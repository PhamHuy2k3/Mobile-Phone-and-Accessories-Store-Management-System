import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private static InventoryManager inventoryManager;
    private static EmployeeManager employeeManager;
    private static OrderManager orderManager;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        inventoryManager = new InventoryManager();
        employeeManager = new EmployeeManager();
        orderManager = new OrderManager(inventoryManager);

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choiceStr = scanner.nextLine();

            switch (choiceStr) {
                case "1" ->
                    handleProductManagement();
                case "2" ->
                    handleOrderManagement();
                case "3" ->
                    handleReporting();
                case "4" ->
                    handleEmployeeManagement();
                case "0" -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Mobile Phone and Accessory Management System");
        System.out.println("1. Product Management");
        System.out.println("2. Order Management");
        System.out.println("3. Reporting & Analytics");
        System.out.println("4. Employee Management");
        System.out.println("0. Exit");
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        while (input == null || input.trim().isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Value cannot be negative. Please enter a valid number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0) {
                    System.out.println("Value cannot be negative. Please enter a valid integer.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static void handleProductManagement() {
        boolean productMenuRunning = true;
        while (productMenuRunning) {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Find Product by ID");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" ->
                    addProduct();
                case "2" ->
                    viewAllProducts();
                case "3" ->
                    findAndDisplayProduct();
                case "4" ->
                    updateProduct();
                case "5" ->
                    deleteProduct();
                case "0" ->
                    productMenuRunning = false;
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleOrderManagement() {
        boolean orderMenuRunning = true;
        while (orderMenuRunning) {
            System.out.println("\n--- Order Management ---");
            System.out.println("1. Create New Order");
            System.out.println("2. View Past Orders");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" ->
                    createOrder();
                case "2" ->
                    viewPastOrders();
                case "0" ->
                    orderMenuRunning = false;
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleEmployeeManagement() {
        boolean employeeMenuRunning = true;
        while (employeeMenuRunning) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add New Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Find Employee by ID");
            System.out.println("4. Update Employee Information");
            System.out.println("5. Delete Employee");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" ->
                    addEmployee();
                case "2" ->
                    viewAllEmployees();
                case "3" ->
                    findAndDisplayEmployee();
                case "4" ->
                    updateEmployee();
                case "5" ->
                    deleteEmployee();
                case "0" ->
                    employeeMenuRunning = false;
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");
        String name = getStringInput("Enter employee name: ");
        String role = getStringInput("Enter employee role (e.g., Sales, Manager): ");
        String employeeId = employeeManager.generateNextEmployeeId();
        Employee newEmployee = new Employee(employeeId, name, role);
        employeeManager.addEmployee(newEmployee);
        System.out.println("Employee added successfully with ID: " + employeeId);
    }

    private static void viewAllEmployees() {
        List<Employee> employees = employeeManager.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("\n--- All Employees ---");
        for (Employee employee : employees) {
            employee.displayDetails();
        }
    }

    private static void findAndDisplayEmployee() {
        String employeeId = getStringInput("Enter employee ID to find: ");
        Optional<Employee> employeeOpt = employeeManager.findEmployeeById(employeeId);
        if (employeeOpt.isPresent()) {
            employeeOpt.get().displayDetails();
        } else {
            System.out.println("Employee with ID '" + employeeId + "' not found.");
        }
    }

    private static void updateEmployee() {
        String employeeId = getStringInput("Enter employee ID to update: ");
        Optional<Employee> employeeOpt = employeeManager.findEmployeeById(employeeId);
        if (!employeeOpt.isPresent()) {
            System.out.println("Employee with ID '" + employeeId + "' not found.");
            return;
        }
        Employee employee = employeeOpt.get();
        System.out.println("Current details: ");
        employee.displayDetails();

        System.out.print("Enter new name (current: " + employee.getName() + ", press Enter to keep): ");
        String newName = scanner.nextLine();
        System.out.print("Enter new role (current: " + employee.getRole() + ", press Enter to keep): ");
        String newRole = scanner.nextLine();
        if (employeeManager.updateEmployee(employeeId, newName, newRole)) {
            System.out.println("Employee information updated successfully.");
            employeeManager.findEmployeeById(employeeId).ifPresent(Employee::displayDetails);
        } else {
            System.out.println("Failed to update employee information.");
        }
    }

    private static void deleteEmployee() {
        String employeeId = getStringInput("Enter employee ID to delete: ");
        if (employeeManager.deleteEmployee(employeeId)) {
            System.out.println("Employee with ID '" + employeeId + "' deleted successfully.");
        } else {
            System.out.println("Employee with ID '" + employeeId + "' not found or could not be deleted.");
        }
    }

    private static void addProduct() {
        System.out.println("Select product type to add:");
        System.out.println("1. Mobile Phone");
        System.out.println("2. Accessory");
        System.out.print("Enter type choice: ");
        String typeChoice = scanner.nextLine();

        String name = getStringInput("Enter product name: ");
        String brand = getStringInput("Enter product brand: ");
        double price = getDoubleInput("Enter product price: ");
        int quantity = getIntInput("Enter product quantity: ");

        if ("1".equals(typeChoice)) {
            double screenSize = getDoubleInput("Enter screen size (inches): ");
            int ram = getIntInput("Enter RAM (GB): ");
            int storage = getIntInput("Enter storage (GB): ");
            String id = inventoryManager.generateNextPhoneId();
            MobilePhone phone = new MobilePhone(id, name, brand, price, quantity, screenSize, ram, storage);
            inventoryManager.addProduct(phone);
            System.out.println("Mobile Phone added successfully with ID: " + id);
        } else if ("2".equals(typeChoice)) {
            String category = getStringInput("Enter accessory category (e.g., Charger, Case): ");
            String id = inventoryManager.generateNextAccessoryId();
            Accessory accessory = new Accessory(id, name, brand, price, quantity, category);
            inventoryManager.addProduct(accessory);
            System.out.println("Accessory added successfully with ID: " + id);
        } else {
            System.out.println("Invalid product type choice.");
        }
    }

    private static void viewAllProducts() {
        List<Product> products = inventoryManager.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products in the inventory.");
            return;
        }
        System.out.println("\n--- All Products ---");
        for (Product product : products) {
            product.displayDetails();
        }
    }

    private static void findAndDisplayProduct() {
        String id = getStringInput("Enter product ID to find: ");
        Optional<Product> productOpt = inventoryManager.findProductById(id);
        if (productOpt.isPresent()) {
            productOpt.get().displayDetails();
        } else {
            System.out.println("Product with ID '" + id + "' not found.");
        }
    }

    private static void updateProduct() {
        String id = getStringInput("Enter product ID to update: ");
        Optional<Product> productOpt = inventoryManager.findProductById(id);

        if (!productOpt.isPresent()) {
            System.out.println("Product with ID '" + id + "' not found.");
            return;
        }

        Product product = productOpt.get();
        System.out.println("Updating product: ");
        product.displayDetails();

        System.out.print("Enter new name (current: " + product.getName() + ", press Enter to keep): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty())
            product.setName(name);

        System.out.print("Enter new brand (current: " + product.getBrand() + ", press Enter to keep): ");
        String brand = scanner.nextLine().trim();
        if (!brand.isEmpty())
            product.setBrand(brand);

        System.out.print("Enter new price (current: " + product.getPrice() + ", press Enter for no change): ");
        String priceStr = scanner.nextLine().trim();
        if (!priceStr.isEmpty()) {
            try {
                product.setPrice(Double.parseDouble(priceStr));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Price not changed.");
            }
        }

        int quantity = getIntInput("Enter new quantity (current: " + product.getQuantity() + "): ");
        product.setQuantity(quantity);

        if (product instanceof MobilePhone phone) {
            double screenSize = getDoubleInput(
                    "Enter new screen size (current: " + phone.getScreenSizeInches() + "): ");
            int ram = getIntInput("Enter new RAM (current: " + phone.getRamGB() + "): ");
            int storage = getIntInput("Enter new storage (GB) (current: " + phone.getStorageGB() + "): ");
            phone.setScreenSizeInches(screenSize);
            phone.setRamGB(ram);
            phone.setStorageGB(storage);
        } else if (product instanceof Accessory accessory) {
            System.out.print(
                    "Enter new accessory category (current: " + accessory.getAccessoryCategory()
                            + ", press Enter to keep): ");
            String category = scanner.nextLine().trim();
            if (!category.isEmpty())
                accessory.setAccessoryCategory(category);
        }
        System.out.println("Product updated successfully.");
        product.displayDetails();
    }

    private static void deleteProduct() {
        String id = getStringInput("Enter product ID to delete: ");
        if (inventoryManager.deleteProduct(id)) {
            System.out.println("Product with ID '" + id + "' deleted successfully.");
        } else {
            System.out.println("Product with ID '" + id + "' not found or could not be deleted.");
        }
    }

    private static void handleReporting() {
        boolean reportingActive = true;
        while (reportingActive) {
            System.out.println("\n--- Reporting & Analytics Menu ---");
            System.out.println("1. Sort by Name (A-Z)");
            System.out.println("2. Sort by Name (Z-A)");
            System.out.println("3. Sort by Price (Low to High)");
            System.out.println("4. Sort by Price (High to Low)");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            List<Product> products = inventoryManager.getAllProducts();

            if (products.isEmpty() && !"0".equals(choice)) {
                System.out.println("No products in the inventory to generate a report.");
                continue;
            }

            switch (choice) {
                case "1" -> {
                    products.sort(Comparator.comparing(Product::getName));
                    displayProductList(products, "Products Sorted by Name (A-Z)");
                }
                case "2" -> {
                    products.sort(Comparator.comparing(Product::getName).reversed());
                    displayProductList(products, "Products Sorted by Name (Z-A)");
                }
                case "3" -> {
                    products.sort(Comparator.comparingDouble(Product::getPrice));
                    displayProductList(products, "Products Sorted by Price (Low to High)");
                }
                case "4" -> {
                    products.sort(Comparator.comparingDouble(Product::getPrice).reversed());
                    displayProductList(products, "Products Sorted by Price (High to Low)");
                }
                case "0" ->
                    reportingActive = false;
                default ->
                    System.out.println("Invalid reporting choice. Please try again.");
            }
        }
    }

    private static void displayProductList(List<Product> productList, String reportTitle) {
        if (productList.isEmpty()) {
            System.out.println("No products to display for this report.");
            return;
        }
        System.out.println("\n--- " + reportTitle + " ---");
        for (Product product : productList) {
            product.displayDetails();
        }
        System.out.println("--- End of Report ---");
    }

    private static void createOrder() {
        System.out.println("\n--- Create New Order ---");
        List<OrderItem> currentOrderItems = new ArrayList<>();
        boolean addingItems = true;

        while (addingItems) {
            String productId = getStringInput("Enter Product ID to add to order (or type 'done' to finish): ");
            if (productId.equalsIgnoreCase("done")) {
                addingItems = false;
                continue;
            }

            Optional<Product> productOpt = inventoryManager.findProductById(productId);
            if (!productOpt.isPresent()) {
                System.out.println("Product with ID '" + productId + "' not found.");
                continue;
            }

            Product product = productOpt.get();
            System.out
                    .println("Selected Product: " + product.getName() + " (Available: " + product.getQuantity() + ")");
            if (product.getQuantity() == 0) {
                System.out.println("This product is out of stock.");
                continue;
            }

            int quantityToOrder = getIntInput("Enter quantity to order: ");
            if (quantityToOrder <= 0) {
                System.out.println("Quantity must be greater than zero.");
                continue;
            }

            if (quantityToOrder > product.getQuantity()) {
                System.out.println("Insufficient stock. Available: " + product.getQuantity());
                continue;
            }

            OrderItem orderItem = new OrderItem(product, quantityToOrder);
            currentOrderItems.add(orderItem);
            System.out.println(product.getName() + " (Qty: " + quantityToOrder + ") added to order.");
        }

        if (currentOrderItems.isEmpty()) {
            System.out.println("Order cancelled as no items were added.");
            return;
        }

        String orderId = orderManager.generateNextOrderId();
        Order newOrder = new Order(orderId);

        for (OrderItem item : currentOrderItems) {
            if (inventoryManager.decreaseProductQuantity(item.getProduct().getId(), item.getQuantitySold())) {
                newOrder.addItem(item);
            } else {
                System.out.println("Error: Could not decrease stock for " + item.getProduct().getName() +
                        ". This item will not be included in the order. Order processing halted for this item.");
            }
        }

        if (newOrder.getItems().isEmpty()) {
            System.out.println(
                    "Order could not be processed as no items could be successfully added due to stock issues.");
            return;
        }

        orderManager.addOrder(newOrder);
        System.out.println("Order created successfully!");
        newOrder.displayOrderDetails();
    }

    private static void viewPastOrders() {
        List<Order> orders = orderManager.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No past orders found.");
            return;
        }
        System.out.println("\n--- Past Orders ---");
        for (Order order : orders) {
            order.displayOrderDetails();
        }
        System.out.println("--- End of Past Orders ---");
    }
}
