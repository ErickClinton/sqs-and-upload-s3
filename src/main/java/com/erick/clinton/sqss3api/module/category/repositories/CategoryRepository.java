package com.erick.clinton.sqss3api.module.category.repositories;

import com.erick.clinton.sqss3api.module.category.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryDocument,String> {
}
