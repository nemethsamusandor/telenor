package se.telenor.products.service;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.telenor.products.dto.StoreAddress;

/**
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@Converter
public class StoreAddressConverter implements AttributeConverter<StoreAddress, String>
{
    private static final Logger LOG = LoggerFactory.getLogger(StoreAddressConverter.class);

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(StoreAddress storeAddress)
    {
        return String.format("%s%s %s", storeAddress.getStreet(), SEPARATOR, storeAddress.getCity());
    }

    @Override
    public StoreAddress convertToEntityAttribute(String storeAddress)
    {
        String[] storeAddressArray = storeAddress.split("\\s*" + SEPARATOR + "\\s*", 2);

        StoreAddress entity = new StoreAddress();

        if (storeAddressArray.length == 2)
        {
            entity.setStreet(storeAddressArray[0]);
            entity.setCity(storeAddressArray[1]);
        }
        else
        {
            LOG.warn("Store address: '{}' cannot be parsed", storeAddress);
        }

        return entity;
    }
}
