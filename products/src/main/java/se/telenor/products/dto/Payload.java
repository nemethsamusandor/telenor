package se.telenor.products.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
public class Payload
{
    private String type;
    @JsonProperty("properties")
    private String property;
    private BigDecimal price;
    @JsonProperty("store_address")
    private String storeAddress;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public String getStoreAddress()
    {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress)
    {
        this.storeAddress = storeAddress;
    }
}
