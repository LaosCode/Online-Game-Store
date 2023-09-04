package alex.com.store.controller;

import alex.com.store.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final ProductService productService;

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("best_sellers", productService.getBestSellingProducts());
        model.addAttribute("new_arrivals", productService.getNewArrivals());
        model.addAttribute("on_sales", productService.getOnSalesProducts());
        return "home";
    }


    @GetMapping("all")
    public String showAllProducts(Model model) {
        model.addAttribute("games", productService.findAll());
        return "games-by-category";
    }

    @GetMapping("best-sellers")
    public String showAllBestSeller(Model model) {
        model.addAttribute("games", productService.getBestSellingProducts());
        return "games-by-category";
    }

    @GetMapping("new-arrivals")
    public String showAllNewArrivals(Model model) {
        model.addAttribute("games", productService.getNewArrivals());
        return "games-by-category";
    }

    @GetMapping("on-sales")
    public String showAllOnSales(Model model) {
        model.addAttribute("games", productService.getOnSalesProducts());
        return "games-by-category";
    }
}
