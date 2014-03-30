import java.io.PrintWriter;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NewsParcer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(System.out);
		ILoadNews LNe1 = new e1Loader();
		ILoadNews LNsev = new severLoader();
		ILoadNews LN = LNe1;
		ISaveNews SN = new Saver("baseNews.xml");
		News[] news = LN.LoadNews();
		if(news!=null){
			for(int i=0; i<news.length; i++){
				out.println("id: "+news[i].getId());
				out.println("title: "+news[i].getTitle());
				out.println("anounce: "+news[i].getAnounce());
				out.println("date: "+news[i].getDate());
				out.println("fullText: "+news[i].getFulltext());
				out.println("rubric: "+news[i].getRubric());
				out.println();
			}
			SN.SaveNews(news);
//			BaseNews BN = new BaseNews();
		}
//		d.setDate(1);
		out.flush();
	}

}
