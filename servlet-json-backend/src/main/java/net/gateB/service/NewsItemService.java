package net.gateB.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

// jsoup Imports
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.gateB.model.NewsItem;


/* 
 * Class responsible for scraping Time.com's urls for article information on the front page
 * Methods:
 * 		getNews(): Called by the servlet, returns a Hashtable of all news articles scraped
 * 		scrapTimeUrl(String url): Given a Time.com front page url, extracts articles' information
 * 			and returns them as List of NewsItem objects
 */

public class NewsItemService {
	
	// Hashtable which maps "newsItems" to all the NewsItem objects
	private Hashtable<String, List<NewsItem>> newsItems = new Hashtable<String, List<NewsItem>>();
	
	// Calls scrapTimeUrl() to scrap three urls from Time.com and append the returned NewsItem objects
	// to the Hashtable before returning it
	public Hashtable<String, List<NewsItem>> getNews() {
		List<NewsItem> allItems = new ArrayList<>();
		allItems.addAll(scrapTimeUrl("https://time.com/section/tech/"));
		allItems.addAll(scrapTimeUrl("https://time.com/section/business/"));
		allItems.addAll(scrapTimeUrl("https://time.com/section/sports/"));
		newsItems.put("newsItems", allItems);
		return newsItems;
	}
	
	// Uses jsoup package to scrap through the given url (assuming the url is for Time.com)
	// Thank you, Chrome DevTools for making this much easier
	private List<NewsItem> scrapTimeUrl(String url) {
		List<NewsItem> curList = new ArrayList<>();
		
		try {
		    // Create a document object and use JSoup to fetch the url
		    Document doc = Jsoup.connect(url).get();

		    // Get the list of articles by the tag "article"
		    Elements articles = doc.getElementsByTag("article");

		    /**
		     * For each article, extract the following information:
		     * 1. Title
		     * 2. Excerpt (short blurb of text under article title)
		     * 3. Image Url
		     * 4. Page Url
		     * 5. Category
		     */
		    
		    // Category is contained within the h1 tag with "lead-headline" class
		    String category = doc.getElementsByClass("lead-headline").text();
		    
		    // Loop through all the elements with the "article" tag
		    for (Element article : articles) {
		      // Extract the title from the h3 tag with "headline" class
		      String title = article.getElementsByClass("headline").text();

		      // Extract the excerpt from a div with with "summary" and "desktop-only" classes
		      String excerpt = article.select("div.summary.desktop-only").text();

		      // Extract the image url from an "inner-container" class' img tag's src attribute
		      String imgUrl = article.getElementsByClass("inner-container").select("img[src]").attr("src");

		      // Get the pageUrl from "a" tag with the href attribute
		      String pageUrl = "https://time.com" + article.select("a[href]").first().attr("href");

		      curList.add(new NewsItem(title, excerpt, imgUrl, pageUrl, category));
		    }

		  // Print if error occurs
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
		
		return curList;
	}
	
}


