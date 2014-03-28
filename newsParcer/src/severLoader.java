
import java.io.PrintWriter;
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
			Element Text = items.get(3);
			Element Rubric = doc.select("a[href^=/news/spool/section]").get(0);
			news.setFulltext(Text.text());
			news.setRubric(Rubric.text());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public News[] LoadNews() {
		PrintWriter out = new PrintWriter(System.out);
		try{
			String url = "http://sever-press.ru/?tmpl=component&print=1";
			Document doc = Jsoup.connect(url).get();
			Elements items = doc.select("table.contentpaneopen h1");
			Iterator<Element> it = items.iterator();
			Element item;
			while(it.hasNext()){
				out.println(it.next().text());
			}
	//		Element Text = items.get(3);
	//		Element Rubric = doc.select("a[href^=/news/spool/section]").get(0);
	//		news.setFulltext(Text.text());
	//		news.setRubric(Rubric.text());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
