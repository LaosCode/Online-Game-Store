package alex.com.store.controller;

import alex.com.store.dto.request.OrderRequest;
import alex.com.store.model.User;
import alex.com.store.service.OrderService;
import alex.com.store.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public String postOrder(@Valid OrderRequest orderRequest, Model model) {
        User user = userService.getAuthenticatedUser();
        Long orderId = orderService.createOrder(user,orderRequest);
        model.addAttribute("orderId",orderId);
        return "order-finalize";
    }

    @GetMapping
    public String getOrdering(Model model) {
        model.addAttribute("products", orderService.compileOrder());
        return "order";
    }

//    @GetMapping("/{orderId}")
//    public String showOrder(@PathVariable("orderId") int id, Model model) {
//        model.addAttribute("order", orderService.getOrderById(id));
//        return "order";
//    }

//    @GetMapping("/user/orders")
//    public String getUserOrderList(Model model){
//        model.getAttribute("orders",orderService.getUserOrderList());
//        return "orders";
//    }
}
