
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class severLoader implements ILoadNews{
	private void loadFullPage(News news, String url){
		try{
			Document doc = Jsoup.connect(url).get();
			Elements items = doc.select("div");
			Element Text = items.get(0);
			news.setFulltext(Text.text());
			news.setRubric("sever");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public News[] LoadNews() {
		PrintWriter out = new PrintWriter(System.out);
		News[] res = new News[10];
		News NewsOne;
		try{
			String url = "http://sever-press.ru/?tmpl=component&print=1";
			Document doc = Jsoup.connect(url).get();
			Elements items = doc.select("table.contentpaneopen");
			Iterator<Element> it = items.iterator();
			Element item;
			Elements trList;
			String ref;
			int i=0;
			while(it.hasNext()){
				NewsOne = new News();
				item = it.next().select("h1").get(0);
				ref = item.select("a").get(0).attr("href");
				String idstr = ref.substring(10);
//				out.println(idstr);
//				out.println(idstr.indexOf("-"));
				String id = "se"+idstr.substring(0, idstr.indexOf("-"));
				NewsOne.setId(id);
//				out.println("id: "+id);
				ref = "http://sever-press.ru"+ref+"?tmpl=component&print=1";
				NewsOne.setTitle(item.text());

				trList = it.next().select("tr");
				String DateString = trList.get(1).text();
				SimpleDateFormat sdm = new SimpleDateFormat("dd.MM.yyyy hh:mm");
				
				NewsOne.setDate(sdm.parse(DateString));
				NewsOne.setAnounce(trList.get(2).text());
//				out.println("title: "+NewsOne.getTitle());
//				out.println("ref: "+ref);
//				out.println("anounce: "+NewsOne.getAnounce());
//				out.println("date: "+NewsOne.getDate());
				loadFullPage(NewsOne, ref);
				res[i] = NewsOne;
				i++;
			}
	//		Element Text = items.get(3);
	//		Element Rubric = doc.select("a[href^=/news/spool/section]").get(0);
	//		news.setFulltext(Text.text());
	//		news.setRubric(Rubric.text());
		}catch(Exception e){
			e.printStackTrace();
		}
		out.flush();
		return res;
	}
}
