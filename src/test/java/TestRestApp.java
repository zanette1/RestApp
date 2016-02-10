import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import web.restapp.configuration.ConfigSpringApp;
import web.restapp.service.ProductsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigSpringApp.class, loader = AnnotationConfigContextLoader.class)
public class TestRestApp {

	@Autowired
	ProductsService productsService;
	
	@Test
	public void testProductService() {
		assertEquals("class web.restapp.service.ProductsServiceImpl", this.productsService.getClass().toString());
	}
	
}
