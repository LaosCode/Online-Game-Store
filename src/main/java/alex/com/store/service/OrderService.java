package alex.com.store.service;

import alex.com.store.constants.ErrorMessage;
import alex.com.store.dto.request.OrderRequest;
import alex.com.store.model.Order;
import alex.com.store.model.Product;
import alex.com.store.model.User;
import alex.com.store.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    private final UserService userService;

    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    public List<Order> getUserOrderList() {
        return null;
    }

    @Transactional
    public Long createOrder(User user, OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setUser(user);
        order.getProducts().addAll(user.getProducts());
        order = orderRepository.save(order);
        user.getProducts().clear();
        return order.getId();
    }

    public List<Product> compileOrder() {
        User user = userService.getAuthenticatedUser();
        return user.getProducts();
    }

    public List<Order> showAllOrders() {
        return orderRepository.findAll();
    }
    public List<Order> showOrderByUser(User user) {
        return orderRepository.findOrdersByUser(user);
    }

    public Order showOrderById(int id) {
        return orderRepository.getReferenceById(id);
    }

    public Order showOrderByIdAndUser(int id, User user) {
        return orderRepository.findOrderByIdAndUser(id, user);
    }
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }
}
