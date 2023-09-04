package alex.com.store.controller;

import alex.com.store.dto.request.ProductRequest;
import alex.com.store.dto.response.MessageResponse;
import alex.com.store.model.Product;
import alex.com.store.service.ImageService;
import alex.com.store.service.OrderService;
import alex.com.store.service.ProductService;
import alex.com.store.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final ImageService imageService;
    private final UserService userService;
    private final OrderService orderService;


    @GetMapping("/products")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/products";
    }

    @GetMapping("/product/{id}")
    public String showProductEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(Math.toIntExact(id)));
        return "admin/edit-product";
    }

    @PatchMapping("/product/{id}")
    public String updateProductData(@PathVariable("id") Long id,
                                    @ModelAttribute("product") @Valid ProductRequest productRequest,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/edit-product";
        }
        MessageResponse messageResponse = productService.updateProduct(id, productRequest);
        model.addAttribute(messageResponse.getResponse(), messageResponse.getMessage());
        return "redirect:/admin/products";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/product/add")
    public String showProductAddForm(@ModelAttribute("productRequest") ProductRequest productRequest) {
        return "admin/add-product";
    }

    @PostMapping("/product/add")
    public String postNewProduct(@Valid ProductRequest productRequest,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/add-product";
        }
        MessageResponse messageResponse = productService.addProduct(productRequest);
        model.addAttribute(messageResponse.getResponse(), messageResponse.getMessage());

        return "admin/add-product";
    }

    @DeleteMapping("/image/{id}")
    public String deleteImage(@PathVariable("id") Long id, Model model) {
        productService.deleteImage(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.showAllUsers());
        return "admin/list-of-users";
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        model.addAttribute("orders", orderService.showOrderByUser(userService.getAuthenticatedUser()));

        return "admin/user-details";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/orders")
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.showAllOrders());
        return "admin/list-of-orders";
    }

    @GetMapping("/order/{id}")
    public String showOrder(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderService.showOrderById(id));
        return "admin/order-details";
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable("id") int id) {
        orderService.deleteOrderById(id);
        return "redirect:/admin/orders";
    }

}
