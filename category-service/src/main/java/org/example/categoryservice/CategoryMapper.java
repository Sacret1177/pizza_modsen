package org.example.categoryservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public CategoryDto entityToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setName(category.getName());
        return dto;
    }

    public Category dtoToEntity(CategoryDto dto, Long id){
        Category category = new Category();
        category.setId(id);
        category.setName(dto.getName());
        return category;
    }

    public List<CategoryDto> mapList(List<Category> categoryList) {
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category category : categoryList) {
            dtos.add(entityToDto(category));
        }
        return dtos;
    }
}
