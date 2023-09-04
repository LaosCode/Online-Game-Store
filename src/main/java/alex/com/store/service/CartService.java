package alex.com.store.service;

import alex.com.store.model.Product;
import alex.com.store.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CartService {
    private final UserService userService;
    private final ProductService productService;

    public List<Product> showProductsFromCart() {
        User user = userService.getAuthenticatedUser();
        return user.getProducts();
    }

    @Transactional
    public void addItemToCart(Long perfumeId) {
        User user = userService.getAuthenticatedUser();
        Product product = productService.getProductById(Math.toIntExact(perfumeId));
        user.getProducts().add(product);
    }

    @Transactional
    public void removeItemFromCart(Long perfumeId) {
        User user = userService.getAuthenticatedUser();
        Product product = productService.getProductById(Math.toIntExact(perfumeId));
        user.getProducts().remove(product);
    }
}
