package se.telenor.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.telenor.products.entity.Product;

/**
 * Product Repository
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
public interface ProductRepository extends JpaRepository<Product, Long>
{
    /**
     * Find Product by Type
     * @param type type
     * @return  Type filtered list of products
     */
    List<Product> findByType(String type);
}
