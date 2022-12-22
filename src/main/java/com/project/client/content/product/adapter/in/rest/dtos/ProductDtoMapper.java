package com.project.client.content.product.adapter.in.rest.dtos;

import com.project.client.content.product.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDtoMapper {
  ProductDtoMapper INSTANCE = Mappers.getMapper(ProductDtoMapper.class);

  ProductDto toDto(Product product);

  Product toDomainModel(ProductDto productDto);
}
