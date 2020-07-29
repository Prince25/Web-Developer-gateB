package net.gateB.controller;

import java.io.*;
import java.util.Hashtable;
import java.util.List;

// servlet imports
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Gson import
import com.google.gson.Gson;

// Import other classes
import net.gateB.model.NewsItem;
import net.gateB.service.NewsItemService;


/*
 * Java Servlet triggered by a GET method to /news-items which calls getNews() from NewsItemService class and 
 * responds with a JSON of all the news articles
 */
@WebServlet(urlPatterns = {"/news-items"}, name = "News Items Servlet", description = "JSON Servlet for News Items from time.com")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsItemService newsService = new NewsItemService();
	private Hashtable<String, List<NewsItem>> newsItems = new Hashtable<String, List<NewsItem>>();

	// Calls the getNews() to save the scraped information in memory
	// Triggers when the server starts or servlet is first requested
	public void init() throws ServletException {
		newsItems = newsService.getNews();
	}

	// Responds with JSON when GET request is sent
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String newsItemsJson = gson.toJson(newsItems);	// Convert the newsItems object to JSON string
		   
		// Set up the content type of the webpage
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		   
		// Write response to the web page
		PrintWriter out = response.getWriter();
		out.write(newsItemsJson);
		out.close();
	}

	// Gets called at the end of servlet life cycle
	// Empty because Java's garbage collector takes care of everything we have
	public void destroy() {

	}
}
