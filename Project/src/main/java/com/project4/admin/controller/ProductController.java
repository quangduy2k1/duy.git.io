package com.project4.admin.controller;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Product;
import com.project4.admin.models.Brand;
import com.project4.admin.service.ProductService;
import com.project4.admin.models.Category;
import com.project4.admin.service.CategoryService;
import com.project4.admin.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService styleService;
    @GetMapping("/product")
    public String showProductList(Model model) {
        String keyword="";
        return listPage(model,1,keyword);
    }

    @GetMapping("/product/new")
    public String showForm(Model model) {
        List<Category> list = categoryService.listAll();
        model.addAttribute("list", list);
        List<Brand> lists = styleService.listAll();
        model.addAttribute("lists", lists);
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Add new Product");
        return "admin/product_new";
    }

    @PostMapping("/product/save")
    public String saveCategory(Product product, RedirectAttributes ra, Model model) {
        productService.save(product);
        ra.addFlashAttribute("message"," The Product has been saved successfully");
        return "redirect:/product";
    }
    @GetMapping("/product/edit/{id}")
    public String showEdit(@PathVariable("id")Integer id, Model model, RedirectAttributes ra ){
        List<Category> list = categoryService.listAll();
        model.addAttribute("list", list);
        List<Brand> lists = styleService.listAll();
        model.addAttribute("lists", lists);
        try {
            Product product=productService.get(id);
            model.addAttribute("product",product);
            model.addAttribute("pageTitle","Edit Product(ID :" +id+")");
            return "admin/product_new";
        }catch ( ModelException e){
            ra.addFlashAttribute("message"," The Product has been saved successfully");
            return "redirect:/product";
        }
    }
    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable("id")Integer id,RedirectAttributes ra ){
        try {
            productService.delete(id);
            ra.addFlashAttribute("r"," The Product Id "+ id +" has been delete");
        }catch(Exception e){
            ra.addFlashAttribute("r",e.getMessage());
        }
        return "redirect:/product";
    }
    @GetMapping("/product/page/{pageNumber}")
    public String listPage(Model model , @PathVariable("pageNumber")int currentPage,@Param("keyword")String keyword){
        Page<Product>page= productService.findNameProduct(currentPage,keyword);
        int totalItem= (int) page.getTotalElements();
        int totalPage=page.getTotalPages();
        List<Product> listsss=page.getContent();
        model.addAttribute("totalItem",totalItem);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("keyword",keyword);
        model.addAttribute("listsss",listsss);
        List<Brand> list = styleService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        return "admin/product";
    }
    @GetMapping("/product/details/{productId}")
    public String listProduct(Model model ,@PathVariable("productId")Integer productId){
        Product products= productService.get(productId);
        model.addAttribute("productId",productId);
        model.addAttribute("products",products);
        return "admin/details_product";
    }

}
