package persistance.bean;


import java.util.ArrayList;
import java.util.List;


public class Products {
    String retailer;
    String name;
    String id;
    String image;
    String condition;
    int price;
    List<String> accessories;
    public Products(){
        accessories=new ArrayList<String>();
    }

    public Products(String id,String name,String retailer,  String image,
			String condition, int price) {
		super();
		this.retailer = retailer;
		this.name = name;
		this.id = id;
		this.image = image;
		this.condition = condition;
		this.price = price;
	}
    
    
public void setId(String id) {
	this.id = id;
}

public void setRetailer(String retailer) {
	this.retailer = retailer;
}


public void setImage(String image) {
	this.image = image;
}

public void setCondition(String condition) {
	this.condition = condition;
}

public void setPrice(int price) {
	this.price = price;
}

public List getAccessories() {
	return accessories;
}


public void setName(String name) {
	this.name = name;
}

public String getImage() {
	return image;
}

public String getRetailer() {
	return retailer;
}

public String getName() {
	return name;
}

public String getId() {
	return id;
}

public String getCondition() {
	return condition;
}

public int getPrice() {
	return price;
}

public void setAccessories(List<String> accessories) {
	this.accessories = accessories;
}





}
