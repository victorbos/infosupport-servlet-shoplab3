package nl.shop.web.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.shop.domain.Product;
import nl.shop.domain.ProductNotFoundException;
import nl.shop.srv.ProductService;

/**
 * Servlet implementation class ProductDetailsServlet
 */
@WebServlet(urlPatterns = "/product")
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductService productService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// TODO Handel de situatie af wanneer er geen productId parameter
			// meegegeven wordt

			// TODO handel de situatie af wanneer het productId geen getal is
			// (NumberFormatException).

			Long productId = Long.parseLong(request.getParameter("productId"));

			Product product = productService.findProductById(productId);
			request.setAttribute("product", product);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/views/product.jsp");
			dispatcher.forward(request, response);
		} catch (ProductNotFoundException e) {
			response.sendError(404, "Product niet gevonden.");
		} catch (NumberFormatException e){
            response.sendError(400);
        }
	}

}
