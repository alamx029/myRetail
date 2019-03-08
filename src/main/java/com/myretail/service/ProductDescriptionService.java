package com.myretail.service;


import java.io.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProductDescriptionService {

    private RestTemplate restTemplate = new RestTemplate();

    public String getProductName(int id) throws IOException {

        String internalUrl = "https://redsky.target.com/v2/pdp/tcin/" + id
            + "?excludes=taxonomy%2Cprice%2Cpromotion%2Cbulk_ship%2Crating_and_review_reviews%2Crating_and_review_statistics%2Cquestion_answer_statistics&fbclid=IwAR0aDmTIZdZYfLKB4vWuUZrmlb6iNJoCaey80r1_6eUEjeaBupHc6CWWvMk";
        String productName = "";

        ResponseEntity<String> response = restTemplate.getForEntity(internalUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode product = root.path("product");
            if (product != null && product.has("item")) {
                JsonNode item = product.path("item");
                if (item != null && item.has("product_description")) {
                    JsonNode product_description = item.path("product_description");
                    if (product_description != null && product_description.has("title")) {
                        productName = product_description.path("title").asText();
                    }
                }
            }
        }

        catch(IOException e){
            throw new IOException(e.getMessage());
        }

        return productName;

    }
}
