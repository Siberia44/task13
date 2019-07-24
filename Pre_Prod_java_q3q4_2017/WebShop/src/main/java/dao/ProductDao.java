package dao;

import bean.SearchForm;
import entity.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts(Connection connection);

    List<String> getAllCountries(Connection connection);

    List<String> getAllTypes(Connection connection);

    List<Product> getListProductsBySearchForm(Connection connection, SearchForm searchForm);
}
