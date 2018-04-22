package com.takeaway.core.netpay.wxpay.utils;


import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser {

    public static Map<String,String> getMapFromXML(String xmlString){
    	Logger log = Logger.getLogger(XMLParser.class);
        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
    	try {
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		InputStream is =  Util.getStringStream(xmlString);
    		Document document = builder.parse(is);
    		//获取到document里面的全部结点
    		NodeList allNodes = document.getFirstChild().getChildNodes();
    		Node node;
    		Map<String, String> map = new HashMap<String, String>();
    		int i=0;
    		while (i < allNodes.getLength()) {
    			node = allNodes.item(i);
    			if(node instanceof Element){
    				map.put(node.getNodeName(),node.getTextContent());
    			}
    			i++;
    		}
    		return map;
			
		} catch (Exception e) {
			log.error("XML转换MAP过程异常", e);
			return null;
		}
    }


}
