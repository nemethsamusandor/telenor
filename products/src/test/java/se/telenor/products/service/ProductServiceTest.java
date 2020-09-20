package se.telenor.products.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import se.telenor.products.dto.ProductProperty;
import se.telenor.products.dto.StoreAddress;
import se.telenor.products.entity.Product;

/**
 * Unit test {@link ProductService} class
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest
{
    private List<Product> products;

    @Mock
    ProductService productService;

    @BeforeAll
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        initProductList();
    }

    @Test
    void testGetProducts()
    {
        Mockito.when(productService.getProducts()).thenReturn(products);

        List<Product> returnList = productService.getProducts();

        assertEquals(products.size(), returnList.size());
    }

    @Test
    void testGetProductsByType()
    {
        Mockito.when(productService.getProductsByType(Mockito.eq("phone"))).thenReturn(
            products.stream()
                .filter(f -> "phone".equals(f.getType()))
                .collect(Collectors.toList()));

        Mockito.when(productService.getProductsByType(Mockito.eq("subscription"))).thenReturn(
            products.stream()
                .filter(f -> "subscription".equals(f.getType()))
                .collect(Collectors.toList()));

        List<Product> returnListPhone = productService.getProductsByType("phone");
        List<Product> returnListSubscription = productService.getProductsByType("subscription");

        assertEquals(1, returnListPhone.size());
        assertEquals(1, returnListSubscription.size());
    }

    private void initProductList()
    {
        this.products = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal(200.00));
        product.setType("phone");

        StoreAddress storeAddress = new StoreAddress("Svensson gatan", "Stockholm");
        product.setAddress(storeAddress);

        ProductProperty productProperty = new ProductProperty("color", "grön");
        product.setProperties(productProperty);

        products.add(product);

        product = new Product();
        product.setId(2L);
        product.setPrice(new BigDecimal(300.00));
        product.setType("subscription");

        storeAddress = new StoreAddress("Gustafsson gärdet", "Malmö");
        product.setAddress(storeAddress);

        productProperty = new ProductProperty("gb_limit", "50");
        product.setProperties(productProperty);

        products.add(product);
    }
}
