package alex.com.store.controller;

import alex.com.store.service.CartService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartContoller {

    private final CartService cartService;

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("products",cartService.showProductsFromCart());
        return "cart";
    }

    @PostMapping("/add")
    public String addItemToCart(@RequestParam("productId") Long productId) {
        cartService.addItemToCart(productId);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItemFromCart(@RequestParam("productId") Long productId) {
        cartService.removeItemFromCart(productId);
        return "redirect:/cart";
    }

}
