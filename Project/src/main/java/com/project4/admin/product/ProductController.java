package com.project4.admin.product;

import com.project4.admin.brand.BrandService;
import com.project4.admin.category.CategoryService;
import com.project4.common.exception.ModelException;
import com.project4.common.entites.Brand;
import com.project4.common.entites.Category;
import com.project4.common.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
public record ProductController(ProductService productService, CategoryService categoryService,
                                BrandService styleService) {
    private static final String GET_LIST_PRODUCT = "/product";
    private static final String VIEW_PRODUCT_CREATE = "/product/new";
    private static final String CREATE_PRODUCT_ENDPOINT = "/product/save";
    private static final String EDIT_PRODUCT_ENDPOINT = "/product/edit/{id}";
    private static final String DELETE_PRODUCT_ENDPOINT = "/product/delete/{id}";
    private static final String GET_LIST_PRODUCT_HOME = "/product/page/{pageNumber}";
    private static final String GET_DETAILS_PRODUCT = "/product/details/{productId}";

    @GetMapping(GET_LIST_PRODUCT)
    public String showProductList(Model model) {
        String keyword = "";
        return listPage(model, 1, keyword);
    }

    @GetMapping(VIEW_PRODUCT_CREATE)
    public String showForm(Model model) {
        List<Category> list = categoryService.listAll();
        model.addAttribute("list", list);
        List<Brand> lists = styleService.listAll();
        model.addAttribute("lists", lists);
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Add new Product");
        return "admin/product_new";
    }

    @PostMapping(CREATE_PRODUCT_ENDPOINT)
    public String saveCategory(Product product, RedirectAttributes ra, Model model) {
        productService.save(product);
        ra.addFlashAttribute("message", " The Product has been saved successfully");
        return "redirect:/product";
    }

    @PutMapping(EDIT_PRODUCT_ENDPOINT)
    public String showEdit(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        List<Category> list = categoryService.listAll();
        model.addAttribute("list", list);
        List<Brand> lists = styleService.listAll();
        model.addAttribute("lists", lists);
        try {
            Product product = productService.get(id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit Product(ID :" + id + ")");
            return "admin/product_new";
        } catch (ModelException e) {
            ra.addFlashAttribute("message", " The Product has been saved successfully");
            return "redirect:/product";
        }
    }

    @GetMapping(DELETE_PRODUCT_ENDPOINT)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            productService.delete(id);
            ra.addFlashAttribute("r", " The Product Id " + id + " has been delete");
        } catch (Exception e) {
            ra.addFlashAttribute("r", e.getMessage());
        }
        return "redirect:/product";
    }

    @GetMapping(GET_LIST_PRODUCT_HOME)
    public String listPage(Model model, @PathVariable("pageNumber") int currentPage,
                           @Param("keyword") String keyword) {
        Page<Product> page = productService.findNameProduct(currentPage, keyword);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> listsss = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listsss", listsss);
        List<Brand> list = styleService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        return "admin/product";
    }

    @GetMapping(GET_DETAILS_PRODUCT)
    public String listProduct(Model model, @PathVariable("productId") Integer productId) {
        Product products = productService.get(productId);
        model.addAttribute("productId", productId);
        model.addAttribute("products", products);
        return "admin/details_product";
    }

}
