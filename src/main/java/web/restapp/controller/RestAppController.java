package web.restapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.restapp.model.Products;
import web.restapp.service.ProductsService;

@RestController
public class RestAppController {
	
	@Autowired
	ProductsService productsService;
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public ResponseEntity<Products> getProduct(@PathVariable("id") int id) {
		Products product = productsService.findById(id);
		if(product == null){
			System.out.println("User with id " + id +" not found");
			return new ResponseEntity<Products>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Products>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/", method=RequestMethod.GET)
	public ResponseEntity<List<Products>> listProducts() {
		List<Products> products = productsService.getProducts();
		if(products.isEmpty()) {
			System.out.println("No items to view");
			return new ResponseEntity<List<Products>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Products>>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/insert-product/", method=RequestMethod.POST)
	public ResponseEntity<Void> insertProduct(@RequestBody Products product) {
		if(productsService.isProductExist(product)) {
			System.out.println("The product already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		productsService.addProduct(product);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delete-product/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeProduct(@PathVariable("id") int id) {
		Products product = productsService.findById(id);
		if(product == null) {
			System.out.println("No item to delete");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		productsService.deleteProduct(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/update-product/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> editProduct(@PathVariable("id") int id, @RequestBody Products product) {
		Products currentProduct = productsService.findById(id);
		if(currentProduct == null) {
			System.out.println("The product doesn't exist");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		currentProduct.setName(product.getName());
		currentProduct.setProducts(product.getProducts());
		productsService.updateProduct(currentProduct);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
}