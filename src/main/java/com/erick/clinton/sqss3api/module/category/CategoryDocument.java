package com.erick.clinton.sqss3api.module.category;

import com.erick.clinton.sqss3api.module.category.dto.CategoryDto;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoryDocument {

    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

    public CategoryDocument(){}

    public CategoryDocument(CategoryDto categoryDto) {
        this.title = categoryDto.title();
        this.description = categoryDto.description();
        this.ownerId = categoryDto.ownerId();
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",this.title);
        jsonObject.put("description",this.description);
        jsonObject.put("ownerId",this.ownerId);
        jsonObject.put("id",this.id);
        jsonObject.put("type","category");

        return jsonObject.toString();
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
