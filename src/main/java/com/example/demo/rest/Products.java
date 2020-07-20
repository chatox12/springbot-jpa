package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entitys.Product;

import com.example.demo.dao.ProductsDAO;

@RestController
@RequestMapping("products")
public class Products {

	@Autowired
	private ProductsDAO productsDAO;

	// retorna todos los productos que existen
	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productsDAO.findAll();
		return ResponseEntity.ok(products);
	}

	@RequestMapping(value = "{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
		Optional<Product> product = productsDAO.findById(productId);

		if (product.isPresent()) {
			return ResponseEntity.ok(product.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product newProduct = productsDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}

	@DeleteMapping(value = "{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
		productsDAO.deleteById(productId);
		return ResponseEntity.ok(null);
	}

	@PatchMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Optional<Product> producto = productsDAO.findById(product.getId());
		if (producto.isPresent()) {
			Product updateProduct = producto.get();
			updateProduct.setName(product.getName());
			updateProduct = productsDAO.save(product);
			return ResponseEntity.ok(updateProduct);

		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
