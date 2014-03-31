import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class NewsParcer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(System.out);
		Scanner in = new Scanner(System.in);
		ILoadNews LNe1 = new e1Loader();
		ILoadNews LNsev = new severLoader();
		ILoadNews LNbase = new baseLoader("baseNews.xml");
		ILoadNews LN = LNbase;
		ISaveNews SN = new Saver("baseNews.xml");
		News[] news = LNbase.LoadNews();
		ArrayList<News> base = new ArrayList<News>(Arrays.asList(news));
		String query = "first query";
		String type, par;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		out.println("input command. for example: help");
		while(true){
			out.flush();
			query = in.nextLine();
			if(query.equals("")){
				continue;
			}
			int deli = query.indexOf(" ");
			
			if(deli != -1 && query.length() > deli){
				type = query.substring(0, deli);
				par = query.substring(deli+1);
			}else{
				type = query;
				par = "";
			}
			if(type.equals("load")){
				if(par.equals("e")){
					news = LNe1.LoadNews();
				}else if(par.equals("s")){
					news = LNsev.LoadNews();
				}else{
					out.println("incorrect load par. only s or e");
					continue;
				}
				for(int i=0; i<news.length; i++){
					boolean addNews = true;
					for(int j = 0; j<base.size(); j++){
						if(news[i].getId().equals(base.get(j).getId())){
							addNews = false;
							break;
						}
					}
					if(addNews){
						base.add(news[i]);
					}
				}
				out.println("loaded");
			}else if(type.equals("rubric")){
				boolean newsNotExists = true;
				for(int i=0; i<base.size(); i++){
					if(base.get(i).getRubric().equals(par)){
						out.println(base.get(i).getParAll());
						newsNotExists = false;
					}
				}
				if(newsNotExists)out.println("news not exists");
			}else if(type.equals("text")){
				boolean newsNotExists = true;
				for(int i=0; i<base.size(); i++){
					if(	base.get(i).getTitle().indexOf(par) != -1 ||
						base.get(i).getAnounce().indexOf(par) != -1 ||
						base.get(i).getFulltext().indexOf(par) != -1)
					{
						out.println(base.get(i).getParAll());
						newsNotExists = false;
					}
				}
				if(newsNotExists)out.println("news not exists");
			}else if(type.equals("date")){
				try{
					sdf.parse(par);
				}catch(Exception e){
					out.println("incorrect date format. must be dd.MM.yyyy");
					continue;
				}
				boolean newsNotExists = true; 
				for(int i=0; i<base.size(); i++){
					if(	par.equals(sdf.format(base.get(i).getDate()))){
						out.println(base.get(i).getParAll());
						newsNotExists = false;
					}
				}
				if(newsNotExists)out.println("news not exists");
			}else if(type.equals("?") || type.equals("help")){
				out.println("exit, quit - exit close program. all load news will save");
				out.println("help, ? - show help");
				out.println("load e - load news from e1.ru");
				out.println("load s - load news from sever-press.ru");
				out.println("save - save base");
				out.println("clear - clear base");
				out.println("date dd.MM.yyyy - show news from date");
				out.println("rubric rub - show news from rubric rub");
				out.println("text str - show news witch contains str");
			}else if(type.equals("exit") || type.equals("quit")){
				SN.SaveNews(base.toArray(new News[base.size()]));
				break;
			}else if(type.equals("save")){
				SN.SaveNews(base.toArray(new News[base.size()]));
				out.println("saved");
			}else if(type.equals("clear")){
				base.clear();
				out.println("cleared");
			}else{
				out.println("incorrect command. input help");
			}
			out.flush();
		}
//		d.setDate(1);
		out.flush();
	}

}
