package se.telenor.products.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;

import se.telenor.products.dto.ProductProperty;

/**
 * Unit test {@link ProductPropertyConverter} class
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductPropertyConverterTest
{
    private ProductPropertyConverter converter;

    @BeforeAll
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        converter = new ProductPropertyConverter();
    }

    @Test
    void testConvertToEntityAttribute()
    {
        ProductProperty productProperty = converter.convertToEntityAttribute("color:grön");

        assertEquals("color", productProperty.getKey());
        assertEquals("grön", productProperty.getValue());
    }

    @Test
    void testConvertToDatabaseColumn()
    {
        ProductProperty productProperty = new ProductProperty("gb_limit", "10");

        String productPropertyString = converter.convertToDatabaseColumn(productProperty);

        assertEquals("gb_limit:10", productPropertyString);
    }
}
