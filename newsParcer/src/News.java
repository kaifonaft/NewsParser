import java.sql.Date;

import sun.util.resources.CalendarData;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;


public class News {
	private Date date;
	private String title, anounce, fulltext;
	private String rubric;
	private int id;
	public News(int id, Date d, String title, String anounce, String fulltext){
		this.id = id;
		this.date = d;
		this.title = title;
		this.anounce = anounce;
		this.fulltext = fulltext;
	}
	public News(){}
	int getId(){
		return id;
	}
	String getTitle(){
		return title;
	}
	String getAnounce(){
		return anounce;
	}
	String getFulltext(){
		return fulltext;
	}
	Date getDate(){
		return date;
	}
	void setId(int id){
		this.id = id;
	}
	void setTitle(String title){
		this.title = title;
	}
	void setAnounce(String anounce){
		this.anounce = anounce;
	}
	void setFulltext(String fulltext){
		this.fulltext = fulltext;
	}
	void setDate(Date date){
		this.date = date;
	}
	
/*	void setId(int id){
		this.id = id;
	}
	void setDate(Date d){
		this.date = d;
	}
	void setTitle(String title){
		this.title = title;
	}
	void setAnounce(String anounce){
		this.anounce = anounce;
	}
	void setFulltext(String fulltext){
		this.fulltext = fulltext;
	}*/
}
