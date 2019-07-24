package dao.impl;

import bean.SearchForm;
import dao.ProductDao;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final String GET_BY_COUNTRIES ="select * from Products where productCountry = ?";
    private final String GET_ALL_TYPES = "SELECT DISTINCT productType from Products ORDER BY productType";
    private final String GET_ALL_COUNTRIES = "SELECT DISTINCT productCountry from Products ORDER BY productCountry";
    private final String GET_ALL_PRODUCTS = "select * from Products";

    @Override
    public List<Product> getAllProducts(Connection connection) {
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<String> getAllCountries(Connection connection) {
        ResultSet resultSet = null;
        List<String> countries = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_COUNTRIES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                countries.add(resultSet.getString("productCountry"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductCost(resultSet.getInt("productCost"));
        product.setProductName(resultSet.getString("productName"));
        product.setProductImg(resultSet.getString("productImg"));
        product.setProductInfo(resultSet.getString("productInfo"));
        return product;
    }

    @Override
    public List<String> getAllTypes(Connection connection) {
        ResultSet resultSet = null;
        List<String> types = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TYPES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                types.add(resultSet.getString("productType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    @Override
    public List<Product> getListProductsBySearchForm(Connection connection, SearchForm searchForm) {
        List<Product> products = new ArrayList<>();
        if (searchForm.getCountries() != null && !searchForm.getCountries().isEmpty()){
            addProductsInLiasIfCountriesExist(connection, products, searchForm);
        }
        if (searchForm.getTypes() != null && !searchForm.getTypes().isEmpty()){

        }
        return products;
    }

    private void addProductsInLiasIfCountriesExist(Connection connection, List<Product> list, SearchForm searchForm){
        if (searchForm.getCountries() != null && !searchForm.getCountries().isEmpty()){
            List<String> countries = searchForm.getCountries();
            for (String country: countries) {
                list.addAll(getByCountries(connection, country));
            }
        }
    }

    private List<Product> getByCountries(Connection connection, String country) {
        ResultSet resultSet = null;
        List<Product> countries = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_COUNTRIES)) {
            statement.setString(1, country);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                countries.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
