package ru.mngerasimenko.aliparser.domain;

import java.util.LinkedList;
import java.util.List;

public class ProductStore {

    private LinkedList<Product> productList;

    public ProductStore() {
        this.productList = new LinkedList<>();
    }

    public int addProduct(Product product) {
        productList.add(product);
        return productList.size();
    }

    public List<Product> getProductList() {
        return productList;
    }
}
