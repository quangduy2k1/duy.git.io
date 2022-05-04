package com.project4.admin.controller;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Brand;
import com.project4.admin.models.Category;
import com.project4.admin.models.Product;
import com.project4.admin.service.BrandService;
import com.project4.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired private ProductService productService;
    @GetMapping("/brand")
    public String showStyleList(Model model){
        String brandName="";
        return listPage(model,1,brandName);
    }
    @GetMapping("/brand/new")
    public String showForm(Model model){
        model.addAttribute("style",new Brand());
        model.addAttribute("pageTitle","Add new Brand");
        return "admin/new_style";
    }
    @PostMapping("/brand/save")
    public String saveStyle(Brand style, RedirectAttributes ra) throws IOException {
        brandService.save(style);
        ra.addFlashAttribute("message"," The Brand has been saved successfully");
        return "redirect:/brand";
    }
    @GetMapping("/brand/edit/{id}")
    public String showEdit(@PathVariable("id")Integer id, Model model, RedirectAttributes ra ){
        try {
            Brand style=brandService.get(id);
            model.addAttribute("style",style);
            model.addAttribute("pageTitle","Edit Brand(ID :" +id+")");
            return "admin/new_style";
        }catch (ModelException e){
            ra.addFlashAttribute("message"," The Style has been saved successfully");
            return "redirect:/brand";
        }

    }
    @GetMapping("/brand/delete/{id}/{brandName}")
    public String delete(@PathVariable("id")Integer id,RedirectAttributes ra ,@PathVariable("brandName")String brandName,Model model){
        try {
            int cu=1;
            Page<Product> page= productService.findNameB(brandName,1);
            int totalItems= (int) page.getTotalElements();
            model.addAttribute("totalItem",totalItems);
            System.out.println(totalItems);
            if(totalItems!=0){
                ra.addFlashAttribute("r"," The Brand Id "+ id +" cannot be delete");
            }else {
                brandService.delete(id);
                ra.addFlashAttribute("r"," The Brand Id "+ id +" has been delete");
            }
        }catch(Exception e){
            ra.addFlashAttribute("r",e.getMessage());
        }
        return "redirect:/brand";
    }
    @GetMapping("/brand/details/{brandName}/{pageNumber}")
    public String listPage(Model model ,@PathVariable("brandName")String brandName,@PathVariable("pageNumber") int cur){
        Page<Product> page= productService.findNameb(brandName,cur);
        int totalItem= (int) page.getTotalElements();
        int totalPage=page.getTotalPages();
        List<Product> lists=page.getContent();
        model.addAttribute("totalItem",totalItem);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("cur",cur);
        model.addAttribute("brandName",brandName);
        model.addAttribute("lists",lists);
        return "admin/details_brand";
    }
    @GetMapping("/brand/page/{pageNumber}")
    public String listPage(Model model , @PathVariable("pageNumber")int currentPage, @Param("brandName")String brandName){
        Page<Brand> page= brandService.findNameB(currentPage,brandName);
        int totalItem= (int) page.getTotalElements();
        int totalPage=page.getTotalPages();
        List<Brand> lists=page.getContent();
        model.addAttribute("totalItem",totalItem);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("brandName",brandName);
        model.addAttribute("lists",lists);
        return "admin/style";
    }
}
