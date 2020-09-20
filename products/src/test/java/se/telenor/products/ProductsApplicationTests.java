package se.telenor.products;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import se.telenor.products.api.ProductController;
import se.telenor.products.dto.ResponseWrapper;
import se.telenor.products.repository.ProductRepository;

/**
 * Integration test for Products application
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductsApplicationTests
{
    @LocalServerPort
    private int port;

    @Autowired
    ProductController controller;

    @Autowired
    ProductRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads()
    {
        assertThat(controller).isNotNull();
    }

    @Test
    @Order(1)
    void testType()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?type=phone", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(6, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(2)
    void testCity()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?city=Stockholm", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(3, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(3)
    void testMinPrice()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?min_price=203.00", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(5, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(4)
    void testMaxPrice()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?max_price=370.00", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(7, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(5)
    void testPropertyColorGuld()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?property:color=guld", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
            assertEquals(2, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(6)
    void testPropertyLimitMin()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?property:gb_limit_min=50", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(7)
    void testPropertyLimitMax()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?property:gb_limit_max=10", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(8)
    void testPropertyColor()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?property=color", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(6, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(9)
    void testPropertyLimitMaxTooLow()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port + "/product?property:gb_limit_max=1", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(10)
    void testCombined()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port
                + "/product?type=phone&cityKarlskrona&property:color=grå&price_min=100&price_max=300", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getData().size());
    }

    @Test
    @Order(11)
    void testUTF()
    {
        ResponseEntity<ResponseWrapper> responseEntity =
            restTemplate.getForEntity("http://localhost:" + port
                + "/product?type=phone&cityKarlskrona&property:color=grå&price_min=100&price_max=300", ResponseWrapper.class);

        assertNotNull(responseEntity.getBody());
        assertEquals("color:grå", responseEntity.getBody().getData().get(0).getProperty());
    }

}
