package org.products.service;

import jakarta.ws.rs.NotFoundException;
import org.products.entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import org.products.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductService {

    public ProductEntity create(ProductEntity product) {
        ProductEntity.persist(product);
        return product;
    }

    public ProductEntity findById(UUID productId) {
        return (ProductEntity) ProductEntity.findByIdOptional(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    public List<ProductEntity> findAll(Integer page, Integer pageSize) {
        return ProductEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public ProductEntity update(UUID productId, ProductEntity productEntity) {
        var product = findById(productId);

        product.name = productEntity.name;

        ProductEntity.persist(product);

        return product;
    }

    public void delete(UUID productId) {
        var product = findById(productId);
        ProductEntity.deleteById(product.id);
    }
}
