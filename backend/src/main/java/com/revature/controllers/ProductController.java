package com.revature.controllers;

import com.revature.daos.ProductDAO;
import com.revature.models.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.models.dtos.OutgoingProductDTO;
import com.revature.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping(value = "/products")
@RestController
@CrossOrigin
public class ProductController {
    private final ProductDAO productDAO;
    private final ProductService productService;

    public enum PRICE_RANGE {
        MIN_PRICE(0),
        MAX_PRICE(10000);

        private int value;

        private PRICE_RANGE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Autowired
    public ProductController(ProductDAO productDAO, ProductService productService) {

        this.productDAO = productDAO;
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {


            return ResponseEntity.ok().body(productService.getAllProducts());

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct (@PathVariable int productId) {
        Optional<Product> b = productDAO.findById(productId);
        if (b.isEmpty()) {
            return ResponseEntity.status(404).body("No product at ID " + productId + "found");
        }

        Product product = b.get();

        productDAO.deleteById(productId);
        return ResponseEntity.ok().body(product.getName() + " deleted from Products");
    }


    @PostMapping("{productId}/{categoryId}/{categoryDesc}")
    public ResponseEntity<Object> addProduct(@RequestBody Product product, @PathVariable int productId, @PathVariable int categoryId, @PathVariable String categoryDesc) {

        if (productService.addProduct(product, productId, categoryId, categoryDesc)) {

            return ResponseEntity.ok().body(product.getName() + " has been added");

        } else {

            return ResponseEntity.status(404).body(product.getName() + " already exists");
        }
    }

    //Aruna Changes

    @GetMapping(value ="/{pname}")
    public ResponseEntity<?> filterAllProductsByName(@PathVariable String pname) {
        System.out.println("inside filterAllProductsByName");

        List<OutgoingProductDTO> allprdbyname = productService.findAllByProductName(pname.toLowerCase());

        if(allprdbyname.isEmpty()) {
            return ResponseEntity.status(400).body("Product "+ pname + " not found");
        }
        return ResponseEntity.ok().body(allprdbyname);

    }

    @GetMapping(value ="/price/{pvalue}")
    public ResponseEntity<?> filterAllProductsByPrice(@PathVariable double pvalue) {

        System.out.println("inside filterAllProductsByPrice");
        if (pvalue < PRICE_RANGE.MIN_PRICE.getValue() || pvalue > PRICE_RANGE.MAX_PRICE.getValue())
        {
            return ResponseEntity.status(400).body("Price amount out of range : " + PRICE_RANGE.MIN_PRICE.getValue() + " to  " + PRICE_RANGE.MAX_PRICE.getValue() );
        }

        List<OutgoingProductDTO> prd = productService.showAllProductByPrice(pvalue);

        if (prd.isEmpty())
        {
            return ResponseEntity.status(400).body(" No product found with price less than "+pvalue);
        }


        return ResponseEntity.ok().body(productService.showAllProductByPrice(pvalue));

    }


    @GetMapping(value ="/category/{cid}")
    public ResponseEntity<?> filterAllProductsByCategory(@PathVariable int cid) {
        System.out.println("inside filterAllProductsByCategory");

        List<OutgoingProductDTO> allprdbycid = productService.findAllByCategoryCategoryId(cid);

        if(allprdbycid.isEmpty()) {
            return ResponseEntity.status(400).body("Product with category id "+ cid + " not found");
        }


        return ResponseEntity.ok().body(allprdbycid);

    }


}
