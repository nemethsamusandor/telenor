package se.telenor.products.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import se.telenor.products.dto.Payload;
import se.telenor.products.dto.ProductProperty;
import se.telenor.products.dto.ResponseWrapper;
import se.telenor.products.entity.Product;
import se.telenor.products.service.ProductService;

/**
 * Rest API Mappings
 *
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
@RestController
public class ProductController
{
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping(path = "/product", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResponseWrapper> getProduct(
        @RequestParam(required = false) String type,
        @RequestParam(required = false, name = "min_price") BigDecimal minPrice,
        @RequestParam(required = false, name = "max_price") BigDecimal maxPrice,
        @RequestParam(required = false) String city,
        @RequestParam(required = false, name = "property") String property,
        @RequestParam(required = false, name = "property:color") String propertyColor,
        @RequestParam(required = false, name = "property:gb_limit_min") Integer propertyMinLimit,
        @RequestParam(required = false, name = "property:gb_limit_max") Integer propertyMaxLimit)
    {
        try
        {
            ResponseWrapper responseWrapper = new ResponseWrapper();

            /**
             * Filter products by type if exist or get all products,
             * Since, the performance was not in the requirements, this is the fastest way to get the required data.
             * If performance would be crucial, than the {@link se.telenor.products.repository.ProductRepository}
             * should be extended with more custom Query or even NativeQuery would be necessary
             */
            List<Product> products = (type != null && !type.isEmpty()) ? productService.getProductsByType(type) :
                productService.getProducts();

            // Effective filtering of data based on the requirements
            List<Payload> payloadList = products.stream()
                .filter(filter -> city == null || filter.getAddress().getCity().equals(city))
                .filter(filter -> minPrice == null || filter.getPrice().compareTo(minPrice) >= 0)
                .filter(filter -> maxPrice == null || filter.getPrice().compareTo(maxPrice) <= 0)
                .filter(filter -> propertyMinLimit == null
                    || nullSafeIntegerValueCompare(filter.getProperties(), propertyMinLimit, true) >= 0)
                .filter(filter -> propertyMaxLimit == null
                    || nullSafeIntegerValueCompare(filter.getProperties(), propertyMaxLimit, false) <= 0)
                .filter(filter -> propertyColor == null || nullSafeColorCheck(filter.getProperties(), propertyColor))
                .filter(filter -> property == null || nullSafePropertyCheck(filter.getProperties(), property))
                .map(entity -> {
                    Payload data = new Payload();
                    data.setType(entity.getType());
                    data.setPrice(entity.getPrice());
                    data.setProperty(entity.getProperties().getKey() + ":" + entity.getProperties().getValue());
                    data.setStoreAddress(entity.getAddress().getStreet() + ", " + entity.getAddress().getCity());
                    return data;
                })
                .collect(Collectors.toList());

            responseWrapper.setData(payloadList);

            return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private int nullSafeIntegerValueCompare(ProductProperty property, Integer requestedValue, boolean minLimit)
    {
        if (property == null || property.getValue() == null || property.getKey() == null
            || !"gb_limit".equals(property.getKey()))
        {
            return minLimit?-1:1;
        }

        return  Integer.valueOf(property.getValue()).compareTo(requestedValue);
    }

    private boolean nullSafeColorCheck(ProductProperty property, String requestedValue)
    {
        if (property == null || property.getValue() == null || property.getKey() == null
            || !"color".equals(property.getKey()))
        {
            return false;
        }

        return  property.getValue().equals(requestedValue);
    }

    private boolean nullSafePropertyCheck(ProductProperty property, String requestedValue)
    {
        if (property == null || property.getKey() == null)
        {
            return false;
        }

        return property.getKey().equals(requestedValue);
    }

}
