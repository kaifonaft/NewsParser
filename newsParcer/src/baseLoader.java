import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class baseLoader implements ILoadNews{
	private String xmlFileName;
	public baseLoader(String file){
		xmlFileName = file;
	}
	public News[] LoadNews(){
		News[] res = null;
		try{
			File xmlFile = new File(xmlFileName);
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder xmlBulder = dFactory.newDocumentBuilder();
			Document doc;
			doc = xmlBulder.parse(xmlFile);
			NodeList items = doc.getElementsByTagName("item");
			String[] tagName = new String[]{"id","title","anounce","date","fulltext","rubric"};
			res = new News[items.getLength()];
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			for(int i=0; i<items.getLength(); i++){
				Node item = items.item(i);
				News newsOne = new News();
				NodeList newsVars = item.getChildNodes();
				
				for(int j=0; j<newsVars.getLength(); j++){
					Node node = newsVars.item(j);
					String cont = node.getTextContent();
					String nodeName = node.getNodeName();
					if(nodeName.equals("id")){
						newsOne.setId(cont);
					}else if(nodeName.equals("title")){
						newsOne.setTitle(cont);
					}else if(nodeName.equals("anounce")){
						newsOne.setAnounce(cont);
					}else if(nodeName.equals("date")){
						newsOne.setDate(sdf.parse(cont));
					}else if(nodeName.equals("fulltext")){
						newsOne.setFulltext(cont);
					}else if(nodeName.equals("rubric")){
						newsOne.setRubric(cont);
					}
				}
				res[i] = newsOne;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
}
