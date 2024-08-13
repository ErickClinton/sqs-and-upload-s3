package com.erick.clinton.sqss3api.module.product.repositories;

import com.erick.clinton.sqss3api.module.product.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument,String> {
}
