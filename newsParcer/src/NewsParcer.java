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
		News[] NewsE1 = LN.LoadNews();
		if(NewsE1!=null){
			for(int i=0; i<NewsE1.length; i++){
				out.println("id: "+NewsE1[i].getId());
				out.println("title: "+NewsE1[i].getTitle());
				out.println("anounce: "+NewsE1[i].getAnounce());
				out.println("date: "+NewsE1[i].getDate());
				out.println("fullText: "+NewsE1[i].getFulltext());
				out.println("rubric: "+NewsE1[i].getRubric());
				out.println();
			}
			BaseNews BN = new BaseNews();
		}
//		d.setDate(1);
		out.flush();
	}

}
