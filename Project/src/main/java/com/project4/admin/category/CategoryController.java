package com.project4.admin.category;

import com.project4.admin.admin.AdminService;
import com.project4.common.exception.ModelException;
import com.project4.common.entites.Category;
import com.project4.common.entites.Product;
import com.project4.admin.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
public record CategoryController(CategoryService categoryService,
                                 ProductService productService, AdminService adminService) {
    private static final String GET_CATEGORY_HOME = "/category";
    private static final String GET_CATEGORY_HOME_NEW = "/category/new";
    private static final String GET_CATEGORY_HOME_CREATE = "/category/save";
    private static final String GET_CATEGORY_HOME_EDIT = "/category/edit/{id}";
    private static final String DELETE_CATEGORY_HOME = "/category/delete/{id}/{categoryName}";
    private static final String GET_CATEGORY_HOME_LIST = "/category/page/{pageNumber}";
    private static final String GET_CATEGORY_HOME_LIST_BY_CATEGORY_NAME = "/category/details/{categoryName}/{pageNumber}";

    @GetMapping(GET_CATEGORY_HOME)
    public String showCategoryList(Model model) {
        return listPage(model, 1, "hi");

    }

    @GetMapping(GET_CATEGORY_HOME_NEW)
    public String showForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add new Category");
        return "admin/category_new";
    }

    @PostMapping(GET_CATEGORY_HOME_CREATE)
    public String saveCategory(Category category, RedirectAttributes ra) {
        categoryService.save(category);
        ra.addFlashAttribute("message", " The Category has been saved successfully");
        return "redirect:/category";
    }

    @GetMapping(GET_CATEGORY_HOME_EDIT)
    public String showEdit(@PathVariable("id") Integer id,
                           Model model,
                           RedirectAttributes ra) {
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

    @DeleteMapping(DELETE_CATEGORY_HOME)
    public String delete(@PathVariable("id") Integer id,
                         RedirectAttributes ra,
                         @PathVariable("categoryName") String categoryName,
                         Model model) {
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

    @GetMapping(GET_CATEGORY_HOME_LIST)
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

    @GetMapping(GET_CATEGORY_HOME_LIST_BY_CATEGORY_NAME)
    public String listPage(Model model,
                           @PathVariable("categoryName") String categoryName,
                           @PathVariable("pageNumber") int currentPage) {
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
