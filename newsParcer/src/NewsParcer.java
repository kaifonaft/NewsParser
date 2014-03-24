import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NewsParcer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(System.out);
		ILoadNews e1Load = new e1Loader();
		News[] NewsE1 = e1Load.LoadNews();
		if(NewsE1!=null){
			for(int i=0; i<NewsE1.length; i++){
				out.println(NewsE1[i].getTitle());
			}
			BaseNews BN = new BaseNews();
		}
//		d.setDate(1);
		out.flush();
	}

}
