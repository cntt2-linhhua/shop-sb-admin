package vn.edu.leading.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.leading.shop.services.CategoryService;
import vn.edu.leading.shop.services.ProductService;


@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public HomeController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("products",productService.findAll());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
//        for (GrantedAuthority currentPrincipalName : grantedAuthorities) {
//            if (currentPrincipalName.getAuthority().equals("ROLE_ADMIN")) {
//                return "admin/index";
//            }
//        }
        return "home/index";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("product",productService.findById(id));
        return "home/singleProduct";
    }

    @GetMapping("/cart")
    public String cart() {
        return "home/cart";
    }

}
