package se.telenor.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.telenor.products.entity.Product;
import se.telenor.products.repository.ProductRepository;

/**
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@Service
public class ProductService
{
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }

    public List<Product> getProductsByType(String type)
    {
        return productRepository.findByType(type);
    }
}
