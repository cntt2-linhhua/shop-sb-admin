package vn.edu.leading.shop.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.CategoryModel;
import vn.edu.leading.shop.repositories.BaseRepository;
import vn.edu.leading.shop.services.BaseService;

import javax.validation.Valid;

@Controller
public class CategoryController extends BaseController<CategoryModel> {

    public CategoryController(BaseRepository<CategoryModel, ?> baseRepository, BaseService<CategoryModel> baseService) {
        super(baseRepository, baseService);
    }

    @GetMapping("/admin/categories")
    public String category(Model model) {
        model.addAttribute("categories", baseService.findAll());
        return "admin/pages/categories";
    }


    @PostMapping("admin/categories")
    public String save(@Valid CategoryModel category, Model model) {
        baseService.save(category);
        model.addAttribute("categories", baseService.findAll());
        return "admin/pages/categories";
    }


    @GetMapping("/admin/categories/{id}/delete")
    @PreAuthorize("hasRole('USER')")
    public String delete(@PathVariable Long id, RedirectAttributes redirect, Model model) {
        if (baseService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted category successfully!");
            model.addAttribute("categories", baseService.findAll());
            return "admin/pages/categories";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            model.addAttribute("categories", baseService.findAll());
            return "admin/pages/categories";
        }
    }
}
