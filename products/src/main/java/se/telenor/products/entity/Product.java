package se.telenor.products.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import se.telenor.products.dto.ProductProperty;
import se.telenor.products.dto.StoreAddress;
import se.telenor.products.service.ProductPropertyConverter;
import se.telenor.products.service.StoreAddressConverter;

/**
 * Product entity
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    private Long id;

    private BigDecimal price;

    private String type;

    @Column(name = "PROPERTY")
    @Convert(converter = ProductPropertyConverter.class)
    private ProductProperty properties;

    @Column(name = "STOREADDRESS")
    @Convert(converter = StoreAddressConverter.class)
    private StoreAddress address;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public ProductProperty getProperties()
    {
        return properties;
    }

    public void setProperties(ProductProperty properties)
    {
        this.properties = properties;
    }

    public StoreAddress getAddress()
    {
        return address;
    }

    public void setAddress(StoreAddress address)
    {
        this.address = address;
    }
}
