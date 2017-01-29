package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import persistance.bean.Products;
import persistance.dao.ProductDao;

public class AjaxUtility {

	public static HashMap<Integer,Products> getData(){
		HashMap<Integer, Products> productListMap=new HashMap<Integer, Products>();
		
		return productListMap;
	}

	public StringBuffer readData(String searchAreaText){
		StringBuffer sb=new StringBuffer();
		//HashMap<Integer, Products> data=getData();
		ProductDao p=new ProductDao();
		HashMap<String, Products> data=p.getAllProducts();
		
		Set<Map.Entry<String, Products>> entries=data.entrySet();
		for(Map.Entry<String, Products> prodMap:entries){
			Products product=prodMap.getValue();
			if(product.getName().toLowerCase().startsWith(searchAreaText.toLowerCase())){
				sb.append("<Product>");
				sb.append("<ProductId>"+product.getId()+"</ProductId>");
				sb.append("<ProductName>"+product.getName()+"</ProductName>");
				sb.append("</Product>");
			}
		}
		return sb;
	}
}
