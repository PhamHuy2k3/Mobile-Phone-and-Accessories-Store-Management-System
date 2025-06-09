
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class EmployeeManager {
    private final List<Employee> employees;
    private static final AtomicInteger employeeIdCounter = new AtomicInteger(0);

    public EmployeeManager() {
        this.employees = new ArrayList<>();
    }

    public String generateNextEmployeeId() {
        return "EMP" + String.format("%04d", employeeIdCounter.incrementAndGet());
    }

    public void addEmployee(Employee employee) {
        if (employee != null) {
            employees.add(employee);
        }
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public Optional<Employee> findEmployeeById(String employeeId) {
        return employees.stream()
                .filter(e -> e.getEmployeeId().equalsIgnoreCase(employeeId))
                .findFirst();
    }

    public boolean updateEmployee(String employeeId, String newName, String newRole) {
        Optional<Employee> employeeOpt = findEmployeeById(employeeId);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (newName != null && !newName.trim().isEmpty()) {
                employee.setName(newName.trim());
            }
            if (newRole != null && !newRole.trim().isEmpty()) {
                employee.setRole(newRole.trim());
            }
            return true;
        }
        return false;
    }

    public boolean deleteEmployee(String employeeId) {
        return employees.removeIf(e -> e.getEmployeeId().equalsIgnoreCase(employeeId));
    }
}