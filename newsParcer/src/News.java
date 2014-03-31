import java.text.SimpleDateFormat;
import java.util.Date;

import sun.util.resources.CalendarData;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;


public class News {
	private Date date;
	private String title, anounce, fulltext;
	private String rubric;
	private String id;
	public News(String id, Date d, String title, String anounce, String fulltext){
		this.id = id;
		this.date = d;
		this.title = title;
		this.anounce = anounce;
		this.fulltext = fulltext;
	}
	public String getParAll(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		return 
			"id: " + this.id + "\n"+
			"date" + sdf.format(this.date) + "\n"+
			"title" + this.title + "\n"+
			"anounce" + this.anounce + "\n"+
			"date" + this.fulltext + "\n"+
			"rubric" + this.rubric + "\n";
	}
	public News(){}
	String getId(){
		return id;
	}
	public String getTitle(){
		return title;
	}
	public String getAnounce(){
		return anounce;
	}
	public String getFulltext(){
		return fulltext;
	}
	public Date getDate(){
		return date;
	}
	public String getRubric(){
		return rubric;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setAnounce(String anounce){
		this.anounce = anounce;
	}
	public void setFulltext(String fulltext){
		this.fulltext = fulltext;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public void setRubric(String rubric){
		this.rubric= rubric;
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
