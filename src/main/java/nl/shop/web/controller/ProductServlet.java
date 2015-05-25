package nl.shop.web.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.shop.domain.Product;
import nl.shop.srv.ProductService;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	ProductService productService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = productService.listProducts();
		request.setAttribute("products", products);
		request.getRequestDispatcher("WEB-INF/views/products.jsp").forward(
				request, response);
	}

}
