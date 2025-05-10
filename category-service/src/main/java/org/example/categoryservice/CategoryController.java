package org.example.categoryservice;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    CategorySrevice categorySrevice;
    @Autowired
    CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto) {
        return new ResponseEntity<>(categoryMapper.entityToDto(categorySrevice.createCategory(dto)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categorySrevice.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoryDto>> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Category> categoryPage = categorySrevice.findAllCategories(PageRequest.of(page,size));
        List<CategoryDto> dto = categoryMapper.mapList(categoryPage.getContent());

        Page<CategoryDto> response = new PageImpl<>(dto, categoryPage.getPageable(), categoryPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryDto dto, @PathVariable Long id) {
        return new ResponseEntity<>(categorySrevice.updateCategory(categoryMapper.dtoToEntity(dto, id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        categorySrevice.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
