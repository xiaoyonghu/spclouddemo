package net.gaotao.productservice.service;

import net.gaotao.productservice.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProduct();
    Product findById(int id);
}
