import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

//import com.sun.org.apache.xerces.internal.util.HTTPInputSource;



import sun.net.www.http.HttpClient;

import java.net.HttpURLConnection;
import java.net.URL;

public class e1Loader implements ILoadNews{
	public News[] LoadNews() {
		byte[] buf = new byte[128*1024];
		News[] Result = new News[5];
		try{
			boolean notLoad = false;
			PrintWriter out = new PrintWriter(System.out);
		if(!notLoad){
			URL url = new URL("http://www.e1.ru/news/rdf/full.xml");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
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
			out.println("all cool");
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
//			HttpClient HC = new HttpClient();
			for(int i=0; i<5; i++){
				NodeList NL = Items.item(i).getChildNodes();
				News NewsOne = new News();
				String ref;
				for(int j=0; j<NL.getLength(); j++){
					Node Item = NL.item(j);
					String type = Item.getNodeName();
					if(type=="title"){
						NewsOne.setTitle(Item.getTextContent());
					}else if(type == "description"){
						NewsOne.setAnounce(Item.getTextContent());
					}else if(type =="pubDate"){
						NewsOne.setDate(new Date(Item.getTextContent()));
					}else if(type=="link"){
						ref = Item.getTextContent();
					}/*
					out.print("NodeName: ");
					out.println(NL.item(j).getNodeName());
					out.print("NodeValue: ");
					out.println(NL.item(j).getNodeValue());
					out.print("TextContent: ");
					out.println(NL.item(j).getTextContent());
					*/
				}
				
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
