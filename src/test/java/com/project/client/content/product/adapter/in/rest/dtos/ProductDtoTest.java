package com.project.client.content.product.adapter.in.rest.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductDtoTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link ProductDto#ProductDto()}
   *   <li>{@link ProductDto#setAvailability(Boolean)}
   *   <li>{@link ProductDto#setId(String)}
   *   <li>{@link ProductDto#setName(String)}
   *   <li>{@link ProductDto#setPrice(Double)}
   *   <li>{@link ProductDto#getAvailability()}
   *   <li>{@link ProductDto#getId()}
   *   <li>{@link ProductDto#getName()}
   *   <li>{@link ProductDto#getPrice()}
   * </ul>
   */
  @Test
  void testConstructor() {
    ProductDto actualProductDto = new ProductDto();
    actualProductDto.setAvailability(true);
    actualProductDto.setId("42");
    actualProductDto.setName("Name");
    actualProductDto.setPrice(10.0d);
    assertTrue(actualProductDto.getAvailability());
    assertEquals("42", actualProductDto.getId());
    assertEquals("Name", actualProductDto.getName());
    assertEquals(10.0d, actualProductDto.getPrice().doubleValue());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link ProductDto#ProductDto(String, String, Double, Boolean)}
   *   <li>{@link ProductDto#setAvailability(Boolean)}
   *   <li>{@link ProductDto#setId(String)}
   *   <li>{@link ProductDto#setName(String)}
   *   <li>{@link ProductDto#setPrice(Double)}
   *   <li>{@link ProductDto#getAvailability()}
   *   <li>{@link ProductDto#getId()}
   *   <li>{@link ProductDto#getName()}
   *   <li>{@link ProductDto#getPrice()}
   * </ul>
   */
  @Test
  void testConstructor2() {
    ProductDto actualProductDto = new ProductDto("42", "Name", 10.0d, true);
    actualProductDto.setAvailability(true);
    actualProductDto.setId("42");
    actualProductDto.setName("Name");
    actualProductDto.setPrice(10.0d);
    assertTrue(actualProductDto.getAvailability());
    assertEquals("42", actualProductDto.getId());
    assertEquals("Name", actualProductDto.getName());
    assertEquals(10.0d, actualProductDto.getPrice().doubleValue());
  }
}
