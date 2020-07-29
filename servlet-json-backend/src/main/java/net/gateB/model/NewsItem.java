package net.gateB.model;

/* 
 * Class for each news item
 * Attributes: title, excerpt, imgUrl, pageUrl, category
 * Has a constructor and a getter/setter for each attribute
 * 
 */

public class NewsItem {
	private String title;
	private String excerpt;
	private String imgUrl;
	private String pageUrl;
	private String category;
	
	public NewsItem(String title, String excerpt, String imgUrl, String pageUrl, String category) {
		super();
		this.title = title;
		this.excerpt = excerpt;
		this.imgUrl = imgUrl;
		this.pageUrl = pageUrl;
		this.category = category;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
