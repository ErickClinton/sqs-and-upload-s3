package com.erick.clinton.sqss3api.module.product;

import com.erick.clinton.sqss3api.module.aws.AwsSnsService;
import com.erick.clinton.sqss3api.module.aws.dto.MessageDto;
import com.erick.clinton.sqss3api.module.category.CategoryDocument;
import com.erick.clinton.sqss3api.module.category.CategoryService;
import com.erick.clinton.sqss3api.module.category.exceptions.CategoryNotFoundException;
import com.erick.clinton.sqss3api.module.product.dto.ProductDto;
import com.erick.clinton.sqss3api.module.product.exceptions.ProductNotFoundException;
import com.erick.clinton.sqss3api.module.product.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final AwsSnsService awsSnsService;

    public ProductService(CategoryService categoryService, ProductRepository productRepository,AwsSnsService awsSnsService) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.awsSnsService = awsSnsService;
    }

    public ResponseEntity<ProductDocument> create(ProductDto productDto) {
        CategoryDocument categoryDocument = this.categoryService.getById(productDto.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        ProductDocument newProduct = new ProductDocument(productDto);
        newProduct.setCategory(categoryDocument);
        this.productRepository.save(newProduct);

        this.awsSnsService.publish(new MessageDto(newProduct.getOwnerId()));
        return ResponseEntity.ok().body(newProduct);
    }

    public ResponseEntity<List<ProductDocument>> getAll() {
        List<ProductDocument> allCategory = this.productRepository.findAll();
        return ResponseEntity.ok().body(allCategory);
    }

    public ResponseEntity<ProductDocument> update(String id,ProductDto productDto) {
        ProductDocument productDocument = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if(productDto.categoryId() != null){
            this.categoryService.getById(productDto.categoryId())
                    .ifPresent(productDocument::setCategory);
        }
        if(!productDto.title().isEmpty()) productDocument.setTitle(productDto.title());
        if(!productDto.description().isEmpty()) productDocument.setDescription(productDto.description());
        if(!(productDto.price() == null)) productDocument.setPrice(productDto.price());

        this.productRepository.save(productDocument);

        this.awsSnsService.publish(new MessageDto(productDto.ownerId()));
        return ResponseEntity.ok().body(productDocument);
    }

    public ResponseEntity<ProductDocument> delete(String id) {
        ProductDocument productDocument = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(productDocument);
        return ResponseEntity.noContent().build();
    }

}
