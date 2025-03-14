package org.example.productservice;

import jakarta.persistence.EntityNotFoundException;
import org.example.productservice.communication.ProductEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductEventProducer productEventProducer;

    public List<Product> getAllProducts() { return productRepository.findAll(); }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product createProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategoryId(dto.getCategoryId());
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        if(!productRepository.existsById(product.getId())) {
            throw new EntityNotFoundException("Product not found");
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)){
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
        productEventProducer.sendProductDeletedEvent(id);
    }
}
