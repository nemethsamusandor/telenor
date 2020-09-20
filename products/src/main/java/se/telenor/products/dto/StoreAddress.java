package se.telenor.products.dto;

import java.io.Serializable;

/**
 * Store address data
 * Street and City
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
public class StoreAddress implements Serializable
{
    private String street;

    private String city;

    public StoreAddress()
    {

    }

    public StoreAddress(String street, String city)
    {
        this.street = street;
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
}
