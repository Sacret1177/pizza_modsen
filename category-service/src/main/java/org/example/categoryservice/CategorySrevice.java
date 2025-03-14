package org.example.categoryservice;

import jakarta.persistence.EntityNotFoundException;
import org.example.categoryservice.communication.CategoryEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySrevice {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryEventProducer categoryEventProducer;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Page<Category> findAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category createCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        if(!categoryRepository.existsById(category.getId())) {
            throw new EntityNotFoundException("Category with id " + category.getId() + " not found");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        categoryRepository.deleteById(id);
        categoryEventProducer.sendCategoryDeleteEvent(id);
    }
}
