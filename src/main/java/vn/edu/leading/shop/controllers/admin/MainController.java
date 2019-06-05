package vn.edu.leading.shop.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/admin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/register")
    public String register() {
        return "admin/register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "admin/forgot-password";
    }
}
