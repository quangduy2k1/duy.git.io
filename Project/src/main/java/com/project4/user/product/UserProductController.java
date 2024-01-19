package com.project4.user.product;

import com.project4.admin.product.ProductService;
import com.project4.common.entites.Brand;
import com.project4.common.entites.Category;
import com.project4.common.entites.Product;
import com.project4.common.entites.User;
import com.project4.user.authen.UserAuthenService;
import com.project4.user.brach.UserBranchService;
import com.project4.user.category.UserCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/user")
public record UserProductController(ProductService productService, UserBranchService userBranchService,
                                    UserCategoryService userCategoryService, UserAuthenService userAuthenService) {
    private static final String GET_LIST_PRODUCT_BY_CATEGORY = "/homepage/details_category/{categoryname}/{page}";
    private static final String GET_LIST_PRODUCT_BY_BRANCH = "/homepage/details_brand/{brandName}/{page}";
    private static final String GET_DETAILS_PRODUCT = "/homepage/infor/{productId}/{brandName}/{page}";

    private static final String GET_LIST_PRODUCT = "/homepage/page/{pageNumber}";
    private static final String GET_HOME_PRODUCT = "/homepage";

    @GetMapping(GET_HOME_PRODUCT)
    public String showStyleList(Model model) {
        String keyword = "";
        return search(model, 1, keyword);
    }

    @GetMapping(GET_LIST_PRODUCT_BY_CATEGORY)
    public String listPage(Model model, @PathVariable("categoryname") String categoryname, @PathVariable("page") int cur) {
        Page<Product> page = productService.findNameP(categoryname, cur);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> lists = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("cur", cur);
        model.addAttribute("categoryname", categoryname);
        model.addAttribute("lists", lists);
        List<Brand> list = userBranchService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = userCategoryService.listAll();
        model.addAttribute("listt", listt);
        List<User> userList = userAuthenService.listAll();
        model.addAttribute("userList", userList);
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "Homepage/details_category_home";
    }

    @GetMapping(GET_LIST_PRODUCT_BY_BRANCH)
    public String listPagee(Model model, @PathVariable("brandName") String brandName, @PathVariable("page") int cur) {
        Page<Product> page = productService.findNameB(brandName, cur);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> lists = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("cur", cur);
        model.addAttribute("brandName", brandName);
        model.addAttribute("lists", lists);
        List<Brand> list = userBranchService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = userCategoryService.listAll();
        model.addAttribute("listt", listt);
        List<User> userList = userAuthenService.listAll();
        model.addAttribute("userList", userList);
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "Homepage/details_brand_home";
    }

    @GetMapping(GET_DETAILS_PRODUCT)
    public String listProduct(Model model, @PathVariable("productId") Integer productId, @PathVariable("brandName") String brandName, @PathVariable("page") int cur) {
        Product products = productService.get(productId);
        model.addAttribute("productId", productId);
        model.addAttribute("products", products);
        Page<Product> page = productService.findNameB(brandName, cur);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> lists = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("cur", cur);
        model.addAttribute("brandName", brandName);
        model.addAttribute("lists", lists);
        List<Brand> list = userBranchService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = userCategoryService.listAll();
        model.addAttribute("listt", listt);
        List<User> userList = userAuthenService.listAll();
        model.addAttribute("userList", userList);
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "Homepage/infor";
    }

    @GetMapping(GET_LIST_PRODUCT)
    public String search(Model model, @PathVariable("pageNumber") int currentPage,
                         @Param("keyword") String keyword) {
        Page<Product> page = productService.findNameProducts(currentPage, keyword);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> listsss = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listsss", listsss);
        List<Brand> list = userBranchService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = userCategoryService.listAll();
        model.addAttribute("listt", listt);
        List<User> userList = userAuthenService.listAll();
        model.addAttribute("userList", userList);
        System.out.println(totalItem);
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "Homepage/homePage";
    }
}
