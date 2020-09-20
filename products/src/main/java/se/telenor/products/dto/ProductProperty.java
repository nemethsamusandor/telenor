package se.telenor.products.dto;

import java.io.Serializable;

import se.telenor.products.entity.Product;

/**
 * Product property data
 * Holds data as a key-value pair
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
public class ProductProperty implements Serializable
{
    private String key;

    private String value;

    public ProductProperty()
    {

    }

    public ProductProperty(String key,String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
