package se.telenor.products.dto;

import java.util.List;

/**
 * @author  Sandor Nemeth
 * @version 1.00
 * @date    16.09.2020
 */
public class ResponseWrapper
{
    private List<Payload> data;

    public List<Payload> getData()
    {
        return data;
    }

    public void setData(List<Payload> data)
    {
        this.data = data;
    }
}
