package com.erick.clinton.sqss3api.module.category;

import com.erick.clinton.sqss3api.module.aws.AwsSnsService;
import com.erick.clinton.sqss3api.module.aws.dto.MessageDto;
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
    private final AwsSnsService awsSnsService;

    public CategoryService(CategoryRepository categoryRepository, AwsSnsService awsSnsService) {
        this.categoryRepository = categoryRepository;
        this.awsSnsService = awsSnsService;
    }

    public ResponseEntity<CategoryDocument> create(CategoryDto categoryDto) {
        CategoryDocument newCategory = new CategoryDocument(categoryDto);
        this.categoryRepository.save(newCategory);
        this.awsSnsService.publish(new MessageDto(newCategory.toString()));

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
        this.awsSnsService.publish(new MessageDto(categoryDocument.toString()));

        return ResponseEntity.ok().body(categoryDocument);
    }

    public ResponseEntity<CategoryDocument> delete(String id) {
        CategoryDocument categoryDocument = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.categoryRepository.delete(categoryDocument);
        return ResponseEntity.noContent().build();
    }
}
