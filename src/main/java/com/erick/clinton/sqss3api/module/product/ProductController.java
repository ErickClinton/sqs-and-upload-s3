package com.erick.clinton.sqss3api.module.product;

import com.erick.clinton.sqss3api.module.product.dto.ProductDto;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDocument> create(@RequestBody ProductDto productDto) {
        return this.productService.create(productDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDocument>> getAll(){
        return this.productService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDocument> update(@PathVariable("id") String id, @RequestBody ProductDto productDto){
        return this.productService.update(id,productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDocument> delete(@PathVariable("id") String id){
        return this.productService.delete(id);
    }
}
