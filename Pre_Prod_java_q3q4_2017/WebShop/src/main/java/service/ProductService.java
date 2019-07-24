package service;

import bean.SearchForm;
import entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<String> getAllCountries();

    List<String> getAllTypes();

    List<Product> getListProductBySearchForm(SearchForm searchForm);
}
