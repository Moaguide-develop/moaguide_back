package com.moaguide.domain.elasticsearch.product;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductㄴRepository extends ElasticsearchRepository<ProductsEntity, String> {
    // Custom query methods can be added here
}
