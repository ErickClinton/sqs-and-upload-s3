package com.erick.clinton.sqss3api.module.category;

import com.erick.clinton.sqss3api.module.category.dto.CategoryDto;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDocument> create(@RequestBody CategoryDto createRequestDto) {
        return this.categoryService.create(createRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDocument>> getAll(){
        return this.categoryService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDocument> update(@PathParam("id") String id, @RequestBody CategoryDto categoryDocument){
        return this.categoryService.update(id,categoryDocument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDocument> delete(@PathParam("id") String id){
        return this.categoryService.delete(id);
    }

}
