package se.telenor.products.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;

import se.telenor.products.dto.StoreAddress;

/**
 * Unit test {@link StoreAddressConverter} class
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StoreAddressConverterTest
{
    private StoreAddressConverter converter;

    @BeforeAll
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        converter = new StoreAddressConverter();
    }

    @Test
    void testConvertToEntityAttribute()
    {
        StoreAddress address = converter.convertToEntityAttribute("Karlsson allén, Karlskrona");

        assertEquals("Karlsson allén", address.getStreet());
        assertEquals("Karlskrona", address.getCity());
    }

    @Test
    void testConvertToDatabaseColumn()
    {
        StoreAddress address = new StoreAddress("Karlsson allén","Karlskrona");

        String addressString = converter.convertToDatabaseColumn(address);

        assertEquals("Karlsson allén, Karlskrona", addressString);
    }
}
