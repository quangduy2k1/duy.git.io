package com.project4.admin.controller;

import com.project4.admin.models.Category;
import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Product;
import com.project4.admin.service.AdminService;
import com.project4.admin.service.CategoryService;
import com.project4.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired private CategoryService categoryService;
    @Autowired private ProductService productService;
    @Autowired
    private AdminService adminService;
    @GetMapping("/category")
    public String showCategoryList(Model model){
        String categoryname="";
        return listPage(model,1,categoryname);

    }
    @GetMapping("/category/new")
    public String showForm(Model model){
        model.addAttribute("category",new Category());
        model.addAttribute("pageTitle","Add new Category");
        return "admin/category_new";
    }
    @PostMapping("/category/save")
    public String saveCategory(Category category,RedirectAttributes ra) throws IOException {
        categoryService.save(category);
        ra.addFlashAttribute("message"," The Category has been saved successfully");
        return "redirect:/category";
    }
    @GetMapping("/category/edit/{id}")
    public String showEdit(@PathVariable("id")Integer id,Model model,RedirectAttributes ra ){
        try {
            Category category=categoryService.get(id);
            model.addAttribute("category",category);
            model.addAttribute("pageTitle","Edit Category(ID :" +id+")");
            return "admin/category_new";
        }catch (ModelException e){
            ra.addFlashAttribute("message"," The Category has been saved successfully");
            return "redirect:/category";
        }
    }
    @GetMapping("/category/delete/{id}/{categoryname}")
    public String delete(@PathVariable("id")Integer id,RedirectAttributes ra ,@PathVariable("categoryname")String categoryname,Model model){
            int cu=1;
            Page<Product> page= productService.findNamep(categoryname,1);
            int totalItems= (int) page.getTotalElements();
            model.addAttribute("totalItem",totalItems);
            System.out.println(totalItems);
            if(totalItems!=0){
                ra.addFlashAttribute("r"," The Category Id "+ id +" cannot be delete");
            }else {
                categoryService.delete(id);
                ra.addFlashAttribute("r"," The Category Id "+ id +" has been delete");
            }
        return "redirect:/category";
    }
   @GetMapping("/category/page/{pageNumber}")
    public String listPage(Model model , @PathVariable("pageNumber")int currentPage, @Param("categoryname")String categoryname){
       Page<Category>page= categoryService.findName(currentPage,categoryname);
       int totalItem= (int) page.getTotalElements();
       int totalPage=page.getTotalPages();
       List<Category> lists=page.getContent();
       List<Product> productList=productService.listAll();
       model.addAttribute("totalItem",totalItem);
       model.addAttribute("totalPage",totalPage);
       model.addAttribute("currentPage",currentPage);
       model.addAttribute("categoryname",categoryname);
       model.addAttribute("lists",lists);
       model.addAttribute("productList",productList);
       return "admin/category";
   }
    @GetMapping("/category/details/{categoryname}/{pageNumber}")
    public String listPage(Model model , @PathVariable("categoryname")String categoryname, @PathVariable("pageNumber")int currentPage){
        Page<Product> page= productService.findNamep(categoryname,currentPage);
        int totalItems= (int) page.getTotalElements();
        int totalPage=page.getTotalPages();
        List<Product> lists=page.getContent();
        model.addAttribute("totalItem",totalItems);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("categoryname",categoryname);
        model.addAttribute("lists",lists);
        return "admin/details_category";
    }
}
