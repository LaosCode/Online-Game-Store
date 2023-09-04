package alex.com.store.controller;


import alex.com.store.dto.request.EditUserRequest;
import alex.com.store.model.User;
import alex.com.store.service.OrderService;
import alex.com.store.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.sql.results.graph.collection.internal.BagInitializer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/account")
    public String showAccount(Model model) {
        model.addAttribute("account",userService.getAuthenticatedUser());
        return "user/account";
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        model.addAttribute("user",userService.getAuthenticatedUser());
        return "user/profile";
    }

    @PostMapping("/profile")
    public String editUserData(@Valid EditUserRequest request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "user/profile";
        }
        userService.editUserData(request);
        return "redirect:/user/account";
    }

    @GetMapping("/orders")
    public String showAllOrders(Model model) {
        User user = userService.getAuthenticatedUser();
        model.addAttribute("orders", orderService.showOrderByUser(user));
        return "user/list-of-orders";
    }

    @GetMapping("/order/{id}")
    public String showOrder(@PathVariable("id") int id,Model model) {
        User user = userService.getAuthenticatedUser();
        model.addAttribute("order",orderService.showOrderByIdAndUser(id,user));
        return "user/order-details";
    }

}
