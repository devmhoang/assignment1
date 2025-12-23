package demo.backend;

import demo.backend.model.Order;
import demo.backend.model.User;
import demo.backend.repository.OrderRepo;
import demo.backend.service.OrderService;
import demo.backend.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderService orderService;

    private Order testOrder;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setUsername("testuser");

        testOrder = new Order();
        testOrder.setId(UUID.randomUUID());
        testOrder.setPaymentTotal(50000L);
    }

    @Test
    void create_WithUserId_ShouldSetNewOrder() {
        when(userService.findByUUID(any(UUID.class))).thenReturn(testUser);
        when(orderRepo.save(any(Order.class))).thenReturn(testOrder);

        Order result = orderService.create(testOrder, testUser.getId());

        assertNotNull(result);
        verify(orderRepo, times(1)).save(testOrder);
    }

    @Test
    void findAll_ShouldReturnAllOrders() {
        when(orderRepo.findAll()).thenReturn(Arrays.asList(testOrder));
        List<Order> result = orderService.findAll();
        assertEquals(1, result.size());
        verify(orderRepo, times(1)).findAll();
    }

    @Test
    void findOrdersByCustomerId_ShouldReturnCustomerOrders() {
        when(orderRepo.findByCustomerId(any(UUID.class))).thenReturn(Arrays.asList(testOrder));
        List<Order> result = orderService.findOrdersByCustomerId(testUser.getId());
        assertEquals(1, result.size());
        verify(orderRepo, times(1)).findByCustomerId(testUser.getId());
    }
}
