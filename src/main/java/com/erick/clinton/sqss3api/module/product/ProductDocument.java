package com.erick.clinton.sqss3api.module.product;

import com.erick.clinton.sqss3api.module.category.CategoryDocument;
import com.erick.clinton.sqss3api.module.product.dto.ProductDto;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductDocument {

    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

    private Integer price;

    private String categoryId;

    public ProductDocument(){}

    public ProductDocument(ProductDto productDto) {
        this.title = productDto.title();
        this.description = productDto.description();
        this.ownerId = productDto.ownerId();
        this.price = productDto.price();
        this.categoryId = productDto.categoryId();
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",this.title);
        jsonObject.put("description",this.description);
        jsonObject.put("ownerId",this.ownerId);
        jsonObject.put("id",this.id);
        jsonObject.put("categoryId",this.categoryId);
        jsonObject.put("price",this.price);
        jsonObject.put("type","product");

        return jsonObject.toString();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
