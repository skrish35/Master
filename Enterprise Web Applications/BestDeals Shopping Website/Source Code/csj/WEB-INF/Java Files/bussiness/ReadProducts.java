package bussiness;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import persistance.bean.Products;


public class ReadProducts extends DefaultHandler {
	Products product;
	List<Products> productsList;
	String productXmlFileName;
	String elementValueRead;
	

	public ReadProducts(String ProductFile){
		System.out.println("Inside constrctor of ProductDataStore : "+ProductFile);
		this.productXmlFileName = ProductFile;
		productsList = new ArrayList<Products>();
        parseDocument();
        //addProductsToMap();
        //printPretty();
        //printMap();
	}
	
	private void parseDocument() {
		System.out.println("Inside parseDocument() method of ProductDataStore+++++++"+productXmlFileName);
		System.out.println("this : "+this);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(productXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
	
	HashMap<String,Products> productMap=new HashMap<String,Products>();
	
	public HashMap printPretty(){
		System.out.println("Inside addProductsToMap() method of ProductDataStore");
		for(Products product:productsList){
			productMap.put(product.getId(), product);
		}
		return productMap;
	}
	
	private void printMap()
	{
		Set<Map.Entry<String, Products>> entries=productMap.entrySet();
		
		for(Map.Entry<String, Products> prodMap:entries){
			System.out.println();
			Products product=prodMap.getValue();
			System.out.println(prodMap.getKey()+" :  "+product.getName()+"  "+product.getImage()+" "+product.getPrice()+" "+product.getCondition());
		}
	}
	 	
	    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
	 		//System.out.println("Inside startElement() method of ProductDataStore");
	        if (elementName.equals("product")) {
	            product = new Products();
	            product.setRetailer(attributes.getValue("retailer"));
	        }
	    }
	 	
	 	
	    public void endElement(String str1, String str2, String element) throws SAXException {
	 		//System.out.println("Inside endElement() method of ProductDataStore");
	        if (element.equals("product")) {
	        	productsList.add(product);
	        	return;
	        }
	        
	        if (element.equalsIgnoreCase("productid")) {
	        	product.setId(elementValueRead);
	        	return;
	        }
	        if (element.equalsIgnoreCase("name")) {
	        	product.setName(elementValueRead);
	        	return;
	        }
	        if (element.equalsIgnoreCase("condition")) {
	        	product.setCondition(elementValueRead);
	        	return;
	        }
	        if(element.equalsIgnoreCase("price")){
	        	product.setPrice(Integer.parseInt((elementValueRead)));
	        	return;
	        }
	        if(element.equalsIgnoreCase("image")){
	        	product.setImage(elementValueRead);
	        	return;
	        }
	    }
	 	
	 	
	    public void characters(char[] content, int begin, int end) throws SAXException {
	        elementValueRead = new String(content, begin, end);
	    }

}
