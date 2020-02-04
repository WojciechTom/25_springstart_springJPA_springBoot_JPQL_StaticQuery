package pl.javastart;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.javastart.dao.ProductDao;
import pl.javastart.model.Product;


@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {
			
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		ProductDao productDao = ctx.getBean(ProductDao.class);
		
		List<Product> products = new ArrayList<>();
		products.add(new Product("Telewizor", "Samsung", 4500.0));
	    products.add(new Product("Opiekacz", "Opiex", 120.0));
	    products.add(new Product("Laptop", "Samsung", 3599.0));
	    products.add(new Product("Kino domowe", "Yamaha", 2600.0));
	    products.add(new Product("Smartfon", "Sony", 2100.0));
		
		products.forEach(productDao::save);
	    
		System.out.println("All products:");
        List<Product> allProducts = productDao.getAll();
        allProducts.forEach(System.out::println);
        
     
        System.out.println("Product more expensive than 3000");
        List<Product> expensiveProduct = productDao.customGet("SELECT p FROM Product p WHERE p.price > 3000");
        expensiveProduct.forEach(System.out :: println);
		
        System.out.println("All product order by price");
        List<Product> orderByPrice = productDao.customGet("SELECT p FROM Product p ORDER BY p.price ASC");
        orderByPrice.forEach(System.out::println);
	
        productDao.delAll();
        
        ctx.close();
	}

}
