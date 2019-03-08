package com.myretail.controller;

import com.myretail.model.CurrentPrice;
import com.myretail.repository.PriceRepository;
import com.myretail.service.ProductDescriptionService;
import com.myretail.model.ProductDescription;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductDescriptionController {

    @Autowired
    private PriceRepository priceRepository;
    ProductDescriptionService productDescriptionService = new ProductDescriptionService();

    //GET
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody ProductDescription getProductDescription(@PathVariable("id") int id) throws IOException {

        String name = productDescriptionService.getProductName(id); //retrieve product name from external API

        CurrentPrice price = priceRepository.findById(id); //retrieve product price from mongoDB

        ProductDescription product = new ProductDescription(id, name, price);

        return product;
    }

    //PUT
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody String updatePrice(@PathVariable("id") int id, @RequestBody CurrentPrice currentPrice) {

        JSONObject obj = new JSONObject();

        // check if id exists in database
        CurrentPrice price = priceRepository.findById(id);
        if (price == null) {
            obj.put("404", "Not Found");
        }
        else {
            currentPrice.setId(id);
            priceRepository.save(currentPrice);
            obj.put("200", "OK");
        }

        return obj.toString();
    }
}
