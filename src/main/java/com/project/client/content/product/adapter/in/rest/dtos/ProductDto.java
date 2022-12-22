package com.project.client.content.product.adapter.in.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  private String id;
  private String name;
  private Double price;
  private Boolean availability;
}
