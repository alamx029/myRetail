package com.myretail.controller;

import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductDescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProductDescriptionTest() throws Exception {

        this.mockMvc.perform(get("/products/13860428")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getProductDescriptionFailTest() throws Exception {

        this.mockMvc.perform(get("/products/failurl")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void updatePriceTest() throws Exception {

        JSONObject requestBody = new JSONObject();
        requestBody.put("value", 13.49);
        requestBody.put("currencyCode", "USD");

        this.mockMvc.perform(put("/products/13860428").contentType(MediaType.APPLICATION_JSON).content(requestBody.toString())).andDo(print()).andExpect(status().isOk());
    }
}