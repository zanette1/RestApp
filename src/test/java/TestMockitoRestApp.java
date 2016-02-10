import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import web.restapp.model.Products;
import web.restapp.service.ProductsServiceImpl;

public class TestMockitoRestApp {

	private static ProductsServiceImpl mockedProduct;
	private static Products product1;
	private static Products product2;
	
	@BeforeClass
	public static void setUp() {
		mockedProduct = mock(ProductsServiceImpl.class);
		
		product1 = new Products(1, "Laptop", null);
		product2 = new Products(2, "IBM", product1);
		
		doReturn(true).when(mockedProduct).isProductExist(product1);
		doReturn(Arrays.asList(product1, product2)).when(mockedProduct).getProducts();
	}
	
	@Test
	public void testAddProduct() {
	
	}
	
	
	@Test
	public void testGetProducts() {
		List<Products> allProducts = mockedProduct.getProducts();
		assertEquals(2, allProducts.size());
		Products product1 = allProducts.get(0);
		Products product2 = allProducts.get(1);
		
		assertEquals(2, product2.getId());
		assertEquals("IBM", product2.getName());
		assertEquals(product1.getId(), product2.getProducts().getId());
	}
	
	@Test
	public void testIsProductExist() {
		Boolean exist;
		exist = mockedProduct.isProductExist(product1);
		assertTrue(exist);
		
	}
	
	public void testFindById() {
		
	}
	
	public void testUpdateProduct() {
		
	}
	
	public void testDeleteProduct() {
		
	}
}
