package nl.shop.web.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.shop.domain.Product;
import nl.shop.domain.ProductNotFoundException;
import nl.shop.srv.ProductService;
import nl.shop.web.ShoppingBasket;

/**
 * Servlet implementation class ShoppingBasketServlet
 */
@WebServlet("/basket")
public class ShoppingBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	ProductService productService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingBasketServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/shoppingbasket.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String productIdValue = request.getParameter("productId");
		try {
			Long productId = Long.parseLong(productIdValue);
			Product product = productService.findProductById(productId);

			HttpSession session = request.getSession();
			Object basketValue = session.getAttribute("basket");
			ShoppingBasket basket;
			if (basketValue instanceof ShoppingBasket) {
				basket = (ShoppingBasket) basketValue;
			} else {
				basket = new ShoppingBasket();
				session.setAttribute("basket", basket);
			}
			basket.addProduct(product);
			response.sendRedirect("basket");
		} catch (NumberFormatException e) {
			response.sendError(400);
		} catch (ProductNotFoundException e) {
			response.sendError(404);
		}
	}

}
