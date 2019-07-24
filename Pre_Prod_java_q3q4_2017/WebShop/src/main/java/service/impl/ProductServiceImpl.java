package service.impl;

import bean.SearchForm;
import dao.ProductDao;
import dao.transaction.TransactionManager;
import entity.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private TransactionManager transactionManager;

    public ProductServiceImpl(ProductDao productDao, TransactionManager transactionManager) {
        this.productDao = productDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Product> getAllProducts() {
        return transactionManager.doInTransaction(connection -> productDao.getAllProducts(connection));
    }

    @Override
    public List<String> getAllCountries() {
        return transactionManager.doInTransaction(connection -> productDao.getAllCountries(connection));
    }

    @Override
    public List<String> getAllTypes() {
        return transactionManager.doInTransaction(connection -> productDao.getAllTypes(connection));
    }

    @Override
    public List<Product> getListProductBySearchForm(SearchForm searchForm) {
        return transactionManager.doInTransaction(connection -> productDao.getListProductsBySearchForm(connection, searchForm));
    }
}
