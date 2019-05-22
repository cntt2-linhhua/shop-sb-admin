package vn.edu.leading.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.OrderDetailModel;
import vn.edu.leading.shop.services.CustomerService;
import vn.edu.leading.shop.services.OrderDetailService;
import vn.edu.leading.shop.services.OrderService;
import vn.edu.leading.shop.services.ProductService;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrderService orderService;
    private final ProductService productService;

    public OrderDetailController(OrderDetailService orderDetailService, OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderDetailService = orderDetailService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/admin/orderDetails")
    public String orderDetail(Model model) {
        model.addAttribute("orderDetails", orderDetailService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.findAll());
        return "admin/pages/orderDetails";
    }

    @PostMapping("/admin/orderDetails")
    public String save(@Valid OrderDetailModel orderDetail, BindingResult result, RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderDetailModel", orderDetailService.findAll());
            model.addAttribute("OrderModel", orderService.findAll());
            model.addAttribute("ProductModel", productService.findAll());
            return "admin/pages/orderDetails/";
        }
        orderDetailService.save(orderDetail);
        model.addAttribute("orderDetailModel", orderDetailService.findAll());
        model.addAttribute("OrderModel", orderService.findAll());
        model.addAttribute("ProductModel", productService.findAll());
        redirect.addFlashAttribute("successMessage", "Saved orderDetails successfully!");
        return "admin/pages/orderDetails";
    }

    @GetMapping("/admin/orderDetails/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect, Model model) {
        if (orderDetailService.delete(id)) {
            model.addAttribute("orderDetailModel", orderDetailService.findAll());
            model.addAttribute("OrderModel", orderService.findAll());
            model.addAttribute("ProductModel", productService.findAll());
            redirect.addFlashAttribute("successMessage", "Deleted orderDetails successfully!");
            return "admin/pages/orderDetails";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            model.addAttribute("orderDetailModel", orderDetailService.findAll());
            model.addAttribute("OrderModel", orderService.findAll());
            model.addAttribute("ProductModel", productService.findAll());
            return "admin/pages/orderDetails";
        }
    }
}
