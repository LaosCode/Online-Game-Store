package alex.com.store.repository;

import alex.com.store.model.Order;
import alex.com.store.model.Product;
import alex.com.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByUser(User user);

    Order findOrderByIdAndUser(int id,User user);
}
