package pl.javastart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.javastart.model.Product;

@Repository
@Transactional
public class ProductDao {

	@PersistenceContext
	private EntityManager manago;


	
	public EntityManager getManago() {
		return manago;
	}

	public void setManago(EntityManager manago) {
		this.manago = manago;
	}


	public Product get(Long id) {
		return manago.find(Product.class, id);
	}
	

	public void save(Product product) {
		manago.persist(product);
	}
	
	
	public List<Product> getAll(){
		TypedQuery<Product> getAllQuery = manago.createNamedQuery("Product.findAll" , Product.class);
		List<Product> resultList = getAllQuery.getResultList();
		return resultList;
	}
	
	
	public void delAll() {
		Query delQuery = manago.createNamedQuery("Product.delAll");
		delQuery.executeUpdate();
	}
	
	
	public List<Product> customGet(String jpqlQuery){
		TypedQuery<Product> result = manago.createQuery(jpqlQuery, Product.class);
		return result.getResultList();
	}
	
}
