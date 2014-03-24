import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class e1Loader implements ILoadNews{
	public News[] LoadNews() {
		byte[] buf = new byte[128*1024];
		News[] Result = new News[5];
		try{
			boolean notLoad = true;
			PrintWriter out = new PrintWriter(System.out);
		if(!notLoad){
			FileInputStream hos = new FileInputStream("headers.txt");
			String header = "GET /news/rdf/full.xml HTTP/1.0\nHost: www.e1.ru\nUser-Agent: Mozilla/5.0 (Windows NT 5.1; rv:27.0) Gecko/20100101 Firefox/27.0\nAccept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\nAccept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3\n\n";
			int port = 80;
			String host = "www.e1.ru";
			Socket s = new Socket(host, port);
			// send http request
			s.getOutputStream().write(header.getBytes());
			// get http answer
			InputStream is = s.getInputStream();
			FileOutputStream fos = new FileOutputStream("out.xml");
			int r = 1;
			String res2write = "";
			boolean firstBuff = true;
			while(r>0){
				r = is.read(buf);
				if(r>0){
					if(firstBuff){
						firstBuff = false;
						res2write = new String(buf);
						int xmlBegin = res2write.indexOf("<?xml version");
						fos.write(buf, xmlBegin, r-xmlBegin);
					}else{
						fos.write(buf, 0, r);
					}
				}
			}
			fos.close();
			s.close();
			is.close();
		}
//			out.println(new String(buf));
// parse xml(e1.ru)
			
			File xmlFile = new File("out.xml");
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder xmlBulder = dFactory.newDocumentBuilder();
			Document e1XML;
			e1XML = xmlBulder.parse(xmlFile);
			NodeList Items = e1XML.getElementsByTagName("item");
			for(int i=0; i<5; i++){
				NodeList NL = Items.item(i).getChildNodes();
				News T = new News();
				for(int j=0; j<NL.getLength(); j++){
					Node N = NL.item(j);
					String type = N.getNodeName();
					if(type=="title"){
						T.setTitle(N.getTextContent());
					}else if(type == ""){
						
					}/*
					out.print("NodeName: ");
					out.println(NL.item(j).getNodeName());
					out.print("NodeValue: ");
					out.println(NL.item(j).getNodeValue());
					out.print("TextContent: ");
					out.println(NL.item(j).getTextContent());
					*/
				}
				Result[i] = T;
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
