package com.project.client.content.product.adapter.in.rest;

import com.project.client.content.product.models.Product;
import com.project.client.content.product.service.ports.in.ProductPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
  @Autowired private ProductController productController;

  @MockBean private ProductPort productPort;

  /** Method under test: {@link ProductController#getBySimilarity(String)} */
  @Test
  void testGetBySimilarity() throws Exception {
    when(productPort.getBySimilarity(any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/product/{productId}/similar", "42");
    MockMvcBuilders.standaloneSetup(productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /** Method under test: {@link ProductController#getBySimilarity(String)} */
  @Test
  void testGetBySimilarity2() throws Exception {
    when(productPort.getBySimilarity(any())).thenReturn(new ArrayList<>());
    when(productPort.getFromRedis(any())).thenReturn(new ArrayList<>());
    doNothing().when(productPort).saveToRedis(any(), any());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/product/{productId}/similar", "42");
    MockMvcBuilders.standaloneSetup(productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /** Method under test: {@link ProductController#getBySimilarity(String)} */
  @Test
  void testGetBySimilarity3() throws Exception {
    ArrayList<Product> productList = new ArrayList<>();
    productList.add(new Product());
    when(productPort.getBySimilarity(any())).thenReturn(new ArrayList<>());
    when(productPort.getFromRedis(any())).thenReturn(productList);
    doNothing().when(productPort).saveToRedis(any(), any());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/product/{productId}/similar", "42");
    MockMvcBuilders.standaloneSetup(productController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string("[{\"id\":null,\"name\":null,\"price\":null,\"availability\":null}]"));
  }
}
