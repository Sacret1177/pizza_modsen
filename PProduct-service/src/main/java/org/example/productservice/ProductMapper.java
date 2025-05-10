package org.example.productservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    /*public ProductDto map(Product product){
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategory_id(product.getCategoryId());
        return dto;
    }*/

    public Product dtoToEntity(ProductDto dto, Long id){
        Product product = new Product();
        product.setId(id);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategoryId(dto.getCategoryId());
        return product;
    }

    public List<ProductDto> mapList(List<Product> products){
        List<ProductDto> dtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto dto = new ProductDto();
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setCategoryId(product.getCategoryId());
            dtos.add(dto);
        }
        return dtos;
    }
}
