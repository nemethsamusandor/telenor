package se.telenor.products.service;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.telenor.products.dto.ProductProperty;

/**
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@Converter
public class ProductPropertyConverter implements AttributeConverter<ProductProperty, String>
{
    private static final Logger LOG = LoggerFactory.getLogger(ProductPropertyConverter.class);

    private static final String SEPARATOR = ":";

    @Override
    public String convertToDatabaseColumn(ProductProperty productProperty)
    {
        return String.format("%s%s%s", productProperty.getKey(), SEPARATOR, productProperty.getValue());
    }

    @Override
    public ProductProperty convertToEntityAttribute(String productProperty)
    {
        String[] productPropertyArray = productProperty.split("\\s*" + SEPARATOR + "\\s*", 2);

        ProductProperty entity = new ProductProperty();

        if (productPropertyArray.length == 2)
        {
            entity.setKey(productPropertyArray[0]);
            entity.setValue(productPropertyArray[1]);
        }
        else
        {
            LOG.warn("Product property: '{}' cannot be parsed", productProperty);
        }

        return entity;
    }
}
