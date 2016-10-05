package com.qac.oc.repositories.offline;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.qac.oc.entities.mongo.Product;
import com.qac.oc.repositories.ProductRepository;
import com.qac.oc.util.TestData;

@Stateless
@Default
public class ProductRepositoryOffline implements ProductRepository {
	@Inject
	private TestData testData;
	
	@Override
	public List<Product> findAll() {
		return testData.getProducts();
	}

	@Override
	public Product findById(long productId) {
		try {
			logger.log(Level.FINE, "ProductRepositoryOffline.findById: Searching for product" + productId);
			return testData.getProducts().get(testData.getProducts().indexOf(new Product(productId, null, null, null)));
		} catch (ArrayIndexOutOfBoundsException exception) {
			logger.log(Level.INFO, "ProductRepositoryOffline.findById: Searching for unknown product" + productId);
			return null;
		}
	}

	@Override
	public List<Product> findByNameLike(String name) {
		List<Product> products = new ArrayList<>();
		for(Product product : testData.getProducts())
			if (product.getName().toLowerCase().contains(name.toLowerCase()))
				products.add(product);
		return products;
	}

	@Override
	@Deprecated
	public List<Product> findByDescriptionLike(String description) {
		return testData.getProducts();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> findRange(int[] range) {
		// TODO Auto-generated method stub
		return null;
	}
}