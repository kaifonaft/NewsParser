import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


import java.net.HttpURLConnection;
import java.net.URL;

public class e1Loader implements ILoadNews{
	private void loadFullPage(News news, String url){
		try{
			org.jsoup.nodes.Document doc = org.jsoup.Jsoup.connect(url).get();
			org.jsoup.select.Elements items = doc.select("div");
			org.jsoup.nodes.Element Text = items.get(3);
			org.jsoup.nodes.Element Rubric = doc.select("a[href^=/news/spool/section]").get(0);
			news.setFulltext(Text.text());
			news.setRubric(Rubric.text());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public News[] LoadNews() {
		byte[] buf = new byte[128*1024];
		News[] Result;
		HttpURLConnection con;
		URL url;
		int maxi = 10;
		Result = new News[maxi];
		try{
			boolean notLoad = false;
			PrintWriter out = new PrintWriter(System.out);
		if(!notLoad){
			url = new URL("http://www.e1.ru/news/rdf/full.xml");
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Host", "www.e1.ru");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:27.0) Gecko/20100101 Firefox/27.0");
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
			
			InputStream in = con.getInputStream();
			FileOutputStream hout = new FileOutputStream("httpout.xml");
			int rb = 1;
			while(rb>0){
				rb = in.read(buf);
				if(rb>0){
					hout.write(buf, 0, rb);
				}
			}
//			out.println(new String(buf));
//			out.println("all cool");
			out.flush();
			in.close();
			hout.close();
//*/
		}
//			out.println(new String(buf));
// parse xml(e1.ru)
			
			File xmlFile = new File("httpout.xml");

			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder xmlBulder = dFactory.newDocumentBuilder();
			Document Doc;
			Doc = xmlBulder.parse(xmlFile);
			NodeList Items = Doc.getElementsByTagName("item");
			for(int i=0; i<maxi; i++){
				NodeList NL = Items.item(i).getChildNodes();
				News NewsOne = new News();
				String ref="";
				for(int j=0; j<NL.getLength(); j++){
					Node Item = NL.item(j);
					String type = Item.getNodeName();
					if(type.equals("title")){
						NewsOne.setTitle(Item.getTextContent());
					}else if(type.equals("description")){
						NewsOne.setAnounce(Item.getTextContent());
					}else if(type.equals("pubDate")){
						NewsOne.setDate(new Date(Item.getTextContent()));
					}else if(type.equals("link")){
						ref = Item.getTextContent();
					}
				}
				String id = "e1" + ref.substring(36);
				NewsOne.setId(id);
				String refClear = ref.replaceFirst("spool", "print")+".html";
				loadFullPage(NewsOne, refClear);
				Result[i] = NewsOne;
			}
			out.flush();
//			NL.item(0).;
//*/		
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return Result;
	}
}
