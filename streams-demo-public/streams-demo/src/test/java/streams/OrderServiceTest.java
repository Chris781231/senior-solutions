package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService ordersService = new OrderService() ;

    @BeforeEach
    public void init(){
        Product p1 = new Product("Tv","IT",2000);
        Product p2 = new Product("Laptop","IT",2400);
        Product p3 = new Product("Phone","IT",400);
        Product p4 = new Product("Lord of The Rings","Book",20);
        Product p5 = new Product("Harry Potter Collection","Book",120);

        Order o1 = new Order("pending", LocalDate.of(2021,6,7));
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p5);

        Order o2 = new Order("on delivery", LocalDate.of(2021,6,1));
        o2.addProduct(p3);
        o2.addProduct(p1);
        o2.addProduct(p2);

        ordersService.saveOrder(o1);
        ordersService.saveOrder(o2);
    }

    @Test
    void testCountOrdersByStatus() {
        assertEquals(1, ordersService.countOrdersByStatus("pending"));
    }

    @Test
    void testCollectOrdersWithProductCategory() {
        assertEquals(2, ordersService.collectOrdersWithProductCategory("IT").size());
    }

    @Test
    void testProductsOverAmountPrice() {
        assertEquals(4, ordersService.productsOverAmountPrice(1999).size());
    }

    @Test
    void testGetTotalAmountByDates() {
        assertEquals(4800,
                ordersService.getTotalAmountByDates(
                        LocalDate.of(2021, 5,30),
                        LocalDate.of(2021, 6, 4)));
    }

    @Test
    void findProductByName() {
        Product p = new Product("Tv","IT",2000);
        assertEquals(p.getCategory(), ordersService.findProductByName("Tv").get().getCategory());
    }

    @Test
    void testFindMostExpensiveOrder() {
        assertEquals(4800, ordersService.findMostExpensiveOrder().get().getProducts().stream().mapToDouble(Product::getPrice).sum());
    }
}