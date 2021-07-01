package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();

    public void saveOrder(Order order){
        orders.add(order);
    }

    public Long countOrdersByStatus(String status) {
        return orders.stream()
                .filter(o -> o.getStatus().equalsIgnoreCase(status))
                .count();
    }

    public List<Order> collectOrdersWithProductCategory(String category) {
        return orders.stream()
                .filter(o -> o.getProducts()
                        .stream()
                        .anyMatch(p -> p.getCategory().equalsIgnoreCase(category)))
                .collect(Collectors.toList());
    }

    public List<Product> productsOverAmountPrice(int amount) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getPrice() > amount)
                .collect(Collectors.toList());
    }

    public double getTotalAmountByDates(LocalDate beginDate, LocalDate endDate) {
        return orders.stream()
                .filter(o -> o.getOrderDate().isAfter(beginDate) && o.getOrderDate().isBefore(endDate))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Optional<Product> findProductByName(String name) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Optional<Order> findMostExpensiveOrder() {
        return orders.stream()
                .max(Comparator.comparingDouble(o -> o.getProducts()
                        .stream().mapToDouble(Product::getPrice).sum()));
    }
}
