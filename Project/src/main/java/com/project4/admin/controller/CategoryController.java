package com.project4.admin.controller;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Category;
import com.project4.admin.models.Product;
import com.project4.admin.service.AdminService;
import com.project4.admin.service.CategoryService;
import com.project4.admin.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public record CategoryController(CategoryService categoryService,
                                 ProductService productService, AdminService adminService) {
    @GetMapping("/category")
    public String showCategoryList(Model model) {
        return listPage(model, 1, "hi");

    }

    @PostMapping("/category/new")
    public String showForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add new Category");
        return "admin/category_new";
    }

    @PostMapping("/category/save")
    public String saveCategory(Category category, RedirectAttributes ra) {
        categoryService.save(category);
        ra.addFlashAttribute("message", " The Category has been saved successfully");
        return "redirect:/category";
    }

    @GetMapping("/category/edit/{id}")
    public String showEdit(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Category category = categoryService.get(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Edit Category(ID :" + id + ")");
            return "admin/category_new";
        } catch (ModelException e) {
            ra.addFlashAttribute("message", " The Category has been saved successfully");
            return "redirect:/category";
        }
    }

    @DeleteMapping("/category/delete/{id}/{categoryName}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra, @PathVariable("categoryName") String categoryName, Model model) {
        int cu = 1;
        Page<Product> page = productService.findNamep(categoryName, 1);
        int totalItems = (int) page.getTotalElements();
        model.addAttribute("totalItem", totalItems);
        System.out.println(totalItems);
        if (totalItems != 0) {
            ra.addFlashAttribute("r", " The Category Id " + id + " cannot be delete");
        } else {
            categoryService.delete(id);
            ra.addFlashAttribute("r", " The Category Id " + id + " has been delete");
        }
        return "redirect:/category";
    }

    @GetMapping("/category/page/{pageNumber}")
    public String listPage(Model model, @PathVariable("pageNumber") int currentPage, @Param("categoryName") String categoryName) {
        Page<Category> page = categoryService.findName(currentPage, categoryName);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Category> lists = page.getContent();
        List<Product> productList = productService.listAll();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("lists", lists);
        model.addAttribute("productList", productList);
        return "admin/category";
    }

    @GetMapping("/category/details/{categoryName}/{pageNumber}")
    public String listPage(Model model, @PathVariable("categoryName") String categoryName, @PathVariable("pageNumber") int currentPage) {
        Page<Product> page = productService.findNamep(categoryName, currentPage);
        int totalItems = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> lists = page.getContent();
        model.addAttribute("totalItem", totalItems);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("lists", lists);
        return "admin/details_category";
    }
}
