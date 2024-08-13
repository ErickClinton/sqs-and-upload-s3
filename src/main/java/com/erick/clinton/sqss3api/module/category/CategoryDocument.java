package com.erick.clinton.sqss3api.module.category;

import com.erick.clinton.sqss3api.module.category.dto.CategoryDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoryDocument {

    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

    public CategoryDocument(CategoryDto createRequestDto) {
        this.title = createRequestDto.title();
        this.description = createRequestDto.description();
        this.ownerId = createRequestDto.ownerId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
