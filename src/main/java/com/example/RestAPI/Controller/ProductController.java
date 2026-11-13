package com.example.RestAPI.Controller;

import com.example.RestAPI.Model.Product;
import com.example.RestAPI.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; 
import java.util.Optional;

@RestController 
@RequestMapping("/products") 
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping
	public List<Product> getAll() { 
		return service.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id) { 
		Optional<Product> product = service.getProductById(id);
		return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) { 
		return service.saveProduct(product);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product productDetails) {
			Product updatedProduct = service.updateProduct(id, productDetails); 
			return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) { 
		service.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

}