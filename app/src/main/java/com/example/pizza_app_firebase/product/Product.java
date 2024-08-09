package com.example.pizza_app_firebase.product;

import java.util.HashMap;
import java.util.Map;

public class Product{
    public Long                 id;
    public String               name;
    public String               image;
    public String               description;
    public Long                 price;
    public Map<String, Long>    sizes;

    public Product() {
        this.id = 0L;
        this.name = "";
        this.image = "";
        this.description = "";
        this.price = 0L;
        this.sizes = new HashMap<String, Long>();
    }

    public Product(Long id, String name, String image, String description, Long price, Map<String, Long> sizes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.sizes.putAll(sizes);
    }
}
