package nl.shop.srv;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.shop.domain.Product;

/**
 * 
 * @author teunh
 * 
 */
@Singleton
// @Singleton zorgt ervoor dat er maar 1 instantie van een DataRepoProducer
// wordt aangemaakt
public class DataRepositoryProducer {

	private static EntityManagerFactory factory;

	/**
	 * Private constructor zodat deze klasse niet aangemaakt kan worden.
	 */
	private DataRepositoryProducer() {

	}

	@Produces
	public EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("pu");

			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			List<Product> products = Arrays.asList(new Product("Bike",
					"bike.jpg", 150.0), new Product("Werklamp", "werklamp.jpg",
					20.1), new Product("New Laptop", "laptop.jpg", 785.0),
					new Product("Nice book", "book.jpg", 15.0), new Product(
							"Boormachine", "boormachine.jpg", 45.0),
					new Product("Keyboard", "keyboard.jpg", 178.0),
					new Product("Smartphone", "smartphone.jpg", 301.0));

			for (Product product : products) {
				em.persist(product);
			}
			em.getTransaction().commit();

		}
		return factory;
	}

	@Produces
	@ShopDb
	@RequestScoped
	// @RequestScoped: voor elk request een nieuwe EM
	public EntityManager produceEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}

}
