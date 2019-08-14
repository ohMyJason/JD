package com.lanqiao.jd.controller.back.product;


import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.service.ProductService;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    FileUtil fileUtil;

    @PostMapping("/insertProduct")
    public Result insertProduct(@RequestParam(name = "file") MultipartFile file, Product sourceProduct){
        String imgUrl = fileUtil.fileUpload(file, 2);
        Product product = new Product();
        product.setProductImgUrl(imgUrl);
        product.setBusinessId(sourceProduct.getBusinessId());
        product.setProductName(sourceProduct.getProductName());
        product.setProductPrice(sourceProduct.getProductPrice());
        return productService.insertProduct(product);
    }

}
