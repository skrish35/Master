function getAJAXObject()
{
	var xmlHttp=false;//XHRObject
	 try{
		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP")
	}
	catch(e){
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP")
	}
	catch(e2){
		xmlHttp=false
	}
	if(!xmlHttp && typeof XMLHttpRequest != 'undefined'){
		xmlHttp=new XMLHttpRequest();
	} 
	xmlHttp=new XMLHttpRequest();
	return xmlHttp;	
}

var ajaxObj=new getAJAXObject();
ajaxObj.overrideMimeType('text/xml');

var autoCompleteTable=document.getElementById("AutoCompleteTable");

function init(){
	console.log("Inside init() function");
	var searchTextField=document.getElementById("searchTextArea");
	var autoRow=document.getElementById("autoRow");
	console.log("searchTextField:"+searchTextField);
}

function doCompletion(){
	console.log("Inside doCompletion() function"+searchAreaText.value);
	var url="searchAutoComplete?action=complete&searchId="+escape(searchAreaText.value);
	ajaxObj.open("get",url,true);
	ajaxObj.send(null);//data is sent/submitted in the url as the request method type is get.So data to be sent is null.
	ajaxObj.onreadystatechange=callBack;
}

function callBack(){
	console.log("Inside callBack() function")
	if(ajaxObj.readyState==4)//Complete response has come to XHRObject
	{
		console.log("Ready State : 4");
		if(ajaxObj.status==200)//response Status is 200:Success
		{
			console.log("Response status : 200"+ajaxObj.responseXML);
			parseMessages(ajaxObj.responseXML);
		}
	}	
}

function parseMessages(xmlData){
	console.log("Inside parseMessages() function"+xmlData);
	autoCompleteTable.innerHTML="";
	
	if(xmlData==null||xmlData==""){
		console.log("Response is null");
		return false;
	}
	else
	{	
		console.log("Response is not null"+xmlData);
		var products=xmlData.getElementsByTagName("Products")[0];
		if(products.childNodes.length>0){
			
			for(loop=0;loop<products.childNodes.length;loop++){
				var product=products.childNodes[loop];
				var productName=product.getElementsByTagName("ProductName")[0].childNodes[0].nodeValue;
				var productId=product.getElementsByTagName("ProductId")[0].childNodes[0].nodeValue;
				console.log("PRODUCT NAME : "+productName+" PRODUCT ID : "+productId);
				appendProduct(productName,productId);
			}
		}
	}
}

function appendProduct(productName,productId){
	console.log("Inside appendProduct() function");
	
	autoCompleteTable.style.display="table";
	
	var tableRow=document.createElement("tr");
	var tableData=document.createElement("td");
	tableRow.appendChild(tableData);
	autoCompleteTable.appendChild(tableRow);
	
	tableData.className="popupCell";
	
	var productLink=document.createElement("a");
	productLink.setAttribute("href", "IndividualProductServlet?productId="+productId);
	productLink.appendChild(document.createTextNode(productName));
	tableData.appendChild(productLink);
}