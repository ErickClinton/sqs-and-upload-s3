package com.erick.clinton.sqss3api.module.category;

import com.erick.clinton.sqss3api.module.category.dto.CategoryDto;
import com.erick.clinton.sqss3api.module.category.exceptions.CategoryNotFoundException;
import com.erick.clinton.sqss3api.module.category.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<CategoryDocument> create(CategoryDto categoryDto) {
        CategoryDocument newCategory = new CategoryDocument(categoryDto);
        this.categoryRepository.save(newCategory);

        return ResponseEntity.ok().body(newCategory);
    }

    public ResponseEntity<List<CategoryDocument>> getAll() {
        List<CategoryDocument> allCategory = this.categoryRepository.findAll();
        return ResponseEntity.ok().body(allCategory);
    }

    public Optional<CategoryDocument> getById(String id) {
        return this.categoryRepository.findById(id);
    }

    public ResponseEntity<CategoryDocument> update(String id,CategoryDto categoryDto) {
        CategoryDocument categoryDocument = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if(!categoryDto.title().isEmpty()) categoryDocument.setTitle(categoryDto.title());
        if(!categoryDto.description().isEmpty()) categoryDocument.setDescription(categoryDto.description());

        this.categoryRepository.save(categoryDocument);

        return ResponseEntity.ok().body(categoryDocument);
    }

    public ResponseEntity<CategoryDocument> delete(String id) {
        CategoryDocument categoryDocument = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.categoryRepository.delete(categoryDocument);
        return ResponseEntity.noContent().build();
    }
}
