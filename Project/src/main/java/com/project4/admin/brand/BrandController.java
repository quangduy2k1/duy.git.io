package com.project4.admin.brand;

import com.project4.common.exception.ModelException;
import com.project4.common.entites.Brand;
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
public record BrandController(BrandService brandService, ProductService productService) {
    private static final String GET_LIST_BRAND = "/brand";
    private static final String GET_CREATE_BRAND_STYLE = "/brand/new";
    private static final String POST_SAVE_BRAND = "/brand/save";
    private static final String EDIT_BRAND = "/brand/edit/{id}";
    private static final String DELETE_BRAND = "/brand/delete/{id}/{brandName}";
    private static final String GET_DETAILS_BRAND_BY_BRAND_NAME = "/brand/details/{brandName}/{pageNumber}";
    private static final String GET_LIST_BRAND_HOME = "/brand/page/{pageNumber}";

    @GetMapping(GET_LIST_BRAND)
    public String showStyleList(Model model) {
        String brandName = "";
        return listPage(model, 1, brandName);
    }

    @GetMapping(GET_CREATE_BRAND_STYLE)
    public String showForm(Model model) {
        model.addAttribute("style", new Brand());
        model.addAttribute("pageTitle", "Add new Brand");
        return "admin/new_style";
    }

    @PostMapping(POST_SAVE_BRAND)
    public String saveStyle(Brand style,
                            RedirectAttributes ra) {
        brandService.save(style);
        ra.addFlashAttribute("message", " The Brand has been saved successfully");
        return "redirect:/brand";
    }

    @GetMapping(EDIT_BRAND)
    public String showEdit(@PathVariable("id") Integer id,
                           Model model,
                           RedirectAttributes ra) {
        try {
            Brand style = brandService.get(id);
            model.addAttribute("style", style);
            model.addAttribute("pageTitle", "Edit Brand(ID :" + id + ")");
            return "admin/new_style";
        } catch (ModelException e) {
            ra.addFlashAttribute("message", " The Style has been saved successfully");
            return "redirect:/brand";
        }

    }

    @DeleteMapping(DELETE_BRAND)
    public String delete(@PathVariable("id") Integer id,
                         RedirectAttributes ra,
                         @PathVariable("brandName") String brandName,
                         Model model) {
        try {
            Page<Product> page = productService.findNameB(brandName, 1);
            int totalItems = (int) page.getTotalElements();
            model.addAttribute("totalItem", totalItems);
            System.out.println(totalItems);
            if (totalItems != 0) {
                ra.addFlashAttribute("r", " The Brand Id " + id + " cannot be delete");
            } else {
                brandService.delete(id);
                ra.addFlashAttribute("r", " The Brand Id " + id + " has been delete");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("r", e.getMessage());
        }
        return "redirect:/brand";
    }

    @GetMapping(GET_DETAILS_BRAND_BY_BRAND_NAME)
    public String listPage(Model model,
                           @PathVariable("brandName") String brandName,
                           @PathVariable("pageNumber") int cur) {
        Page<Product> page = productService.findNameb(brandName, cur);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> lists = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("cur", cur);
        model.addAttribute("brandName", brandName);
        model.addAttribute("lists", lists);
        return "admin/details_brand";
    }

    @GetMapping(GET_LIST_BRAND_HOME)
    public String listPage(Model model,
                           @PathVariable("pageNumber") int currentPage,
                           @Param("brandName") String brandName) {
        Page<Brand> page = brandService.findNameB(currentPage, brandName);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Brand> lists = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("brandName", brandName);
        model.addAttribute("lists", lists);
        return "admin/style";
    }
}
