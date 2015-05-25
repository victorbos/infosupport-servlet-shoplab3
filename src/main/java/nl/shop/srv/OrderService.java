package nl.shop.srv;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import nl.shop.domain.WebOrder;

@Stateless
public class OrderService {

	@ShopDb
	@Inject
	// @Inject de container (jboss weld) zorgt ervoor dat
	// de entitymanager geinjecteerd wordt. Daarom hoeven we die niet
	// zelf aan te maken.
	EntityManager em;

	/**
	 * Slaat de order op in de database
	 * 
	 * @param webOrder
	 *            Moet een gevulde order zijn
	 * @return het gegenereerde id van de nieuwe weborder
	 */
	public Long saveOrder(WebOrder webOrder) {
		Objects.requireNonNull(webOrder, "De gegeven order mag niet null zijn.");
		em.getTransaction().begin();
		em.persist(webOrder);
		em.getTransaction().commit();
		return webOrder.getId();
	}

}
