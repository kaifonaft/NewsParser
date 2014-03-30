import java.io.File;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Saver implements ISaveNews {
	private String xmlFileName;
	public void SaveNews(News[] news){
		try{
			File xmlFile = new File(xmlFileName);
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder xmlBulder = dFactory.newDocumentBuilder();
			Document doc;
			doc = xmlBulder.newDocument();
			Node wrap = doc.createElement("items");
			String[] tagName = new String[]{"id","title","anounce","date","fulltext","rubric"};
			for(int i=0; i<news.length; i++){
				Node item = doc.createElement("item");
				for(int j=0; j<tagName.length; j++){
					Node node = doc.createElement(tagName[j]);
					String cont="";
					if(tagName[j]=="id"){
						cont = news[i].getId();
					}else if(tagName[j]=="title"){
						cont = news[i].getTitle();
					}else if(tagName[j]=="anounce"){
						cont = news[i].getAnounce();
					}else if(tagName[j]=="date"){
						cont = news[i].getDate().toString();
					}else if(tagName[j]=="fulltext"){
						cont = news[i].getFulltext();
					}else if(tagName[j]=="rubric"){
						cont = news[i].getRubric();
					}
					node.setTextContent(cont);
					item.appendChild(node);
				}
				wrap.appendChild(item);
			}
			
			
			doc.appendChild(wrap);

			//save document:
			TransformerFactory TF = TransformerFactory.newInstance();
			Transformer transformer = TF.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Saver(String file){
		xmlFileName = file;
	}
}
