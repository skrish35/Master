package persistance.dao;

import java.util.ArrayList;
import java.util.Arrays;

import persistance.bean.UserReview;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

	public class MongoDBDataStoreUtilities {
		static DBCollection Collection;
		public static MongoClient mongo;
		public static void getConnection(){
			//MongoClient mongo;
			mongo=new MongoClient("localhost",27017);
			
			DB db=mongo.getDB("Reviews");
			Collection=db.getCollection("userreviews");
		}
		
		//productID,productCategory,productBrand,productName,manufacturerRebate,productPrice,retailerName,retailerZip,retailerCity,retailerState,productOnSale,userId,userAge,userGender,userOccupation,reviewRating,reviewText
		public static boolean Insert(String ProductModelName ,String ProductCategory,String ProductPrice,String RetailerName, String RetailerZip, String RetailerCity, String RetailerState, String ProductonSale, String ManufacturerName, String ManufacturerRebate, String UserId,String UserAge, String UserGender,String UserOccupation, int ReviewRating, String ReviewDate, String ReviewText){
			System.out.println("Inside MongoDBDataStoreUtilities class insertReview() method");
			Boolean InsertToDBFlag=false;
			
		
			getConnection();
			BasicDBObject dbObj=new BasicDBObject();
			
			dbObj.put("productName",ProductModelName);
			dbObj.put("productCategory",ProductCategory);
			dbObj.put("productPrice",ProductPrice);
			dbObj.put("retailerName",RetailerName);
			dbObj.put("retailerZip",RetailerZip);
			dbObj.put("retailerCity",RetailerCity);
			dbObj.put("retailerState",RetailerState);
			dbObj.put("productOnSale",ProductonSale);
			dbObj.put("manufacturername", ManufacturerName);
			dbObj.put("manufacturerRebate",ManufacturerRebate);
			dbObj.put("userId",UserId);
			dbObj.put("userAge",UserAge);
			dbObj.put("userGender",UserGender);
			dbObj.put("userOccupation",UserOccupation);
			dbObj.put("reviewRating",ReviewRating);
			dbObj.put("reviewDate",ReviewDate);
			dbObj.put("reviewText",ReviewText);
			
			
			
			BasicDBObject reviewDocument = new BasicDBObject();
			Collection.insert(dbObj);
			InsertToDBFlag=true;
			mongo.close();
			return InsertToDBFlag;
		}
		
		public static ArrayList<UserReview> selectReview(){
			System.out.println("Inside MongoDBDataStoreUtilities class selectReview() method");
			getConnection();
			
			/*BasicDBObject dbObj=new BasicDBObject();
			dbObj.put("productId", productId);
			System.out.println("Basic DB Object :"+dbObj);*/
			
			DBCursor cursor=Collection.find();//Query
			int i=0;
			ArrayList<UserReview> reviewList=new ArrayList<UserReview>();
			while(cursor.hasNext()){
				BasicDBObject basicObj=(BasicDBObject)cursor.next();
				//reviews[i]=obj.getString("UserId")+"|"+obj.getInt("ReviewRating")+"|"+obj.getString("ReviewText");
				reviewList.add(new UserReview(basicObj.getString("productName"),basicObj.getString("reviewRating"),basicObj.getString("reviewText"),basicObj.getString("userId")));
				i++;
				System.out.println("Out:"+reviewList.get(0));
			}
			System.out.println("Reviews sent from Mongo DB");
			cursor.close();
			mongo.close();
			return reviewList;
		}
		
		public static String FiveMostLikedProducts(){
			System.out.println("Inside MongoDBDataStoreUtilities class FiveMostLikedProducts() method");
			String tableHtml="";
			getConnection();
			
			 Iterable<DBObject> output = Collection.aggregate(Arrays.asList(
					 (DBObject) new BasicDBObject("$group", new BasicDBObject("_id", "$productName").append("avgRating", new BasicDBObject("$avg","$reviewRating"))),
					 (DBObject) new BasicDBObject("$sort", new BasicDBObject("avgRating", -1)),
					 (DBObject) new BasicDBObject("$project", new BasicDBObject({"avgRating",$gt:new BasicDBObject("$reviewRating", 4)})
					 (DBObject) new BasicDBObject("$limit", 5)
					 )).results();
			
			 for (DBObject dbObject : output)
			 {
				 System.out.println(dbObject);
				 tableHtml=tableHtml+"<tr><td>"+dbObject.get("_id").toString()+"</td><td>"+dbObject.get("avgRating").toString()+"</td></tr>";
			 }
			mongo.close();
			return tableHtml;
		}
		
		public static String TopFiveZipCode(){
			System.out.println("Inside MongoDBDataStoreUtilities class TopFiveZipCode() method");
			String tableHtml="";
			getConnection();

			Iterable<DBObject> Zipoutput1 = Collection.aggregate(Arrays.asList(
					 (DBObject) new BasicDBObject("$match",new BasicDBObject("keywords",new BasicDBObject("$not",new BasicDBObject("$size",0)))),
					 (DBObject) new BasicDBObject("$unwind","$retailerZip"),
					 (DBObject) new BasicDBObject("$group", new BasicDBObject("_id", new BasicDBObject("$toLower","$retailerZip")).append("count", new BasicDBObject("$sum",1))),
					 (DBObject) new BasicDBObject("$match",new BasicDBObject("count",new BasicDBObject("$gte",2))),
					 (DBObject) new BasicDBObject("$sort", new BasicDBObject("count", -1)),
					 (DBObject) new BasicDBObject("$limit", 100)
					 )).results();
			
			 for (DBObject dbObject : Zipoutput1)
			 {
				 tableHtml=tableHtml+"<tr><td>"+dbObject.get("_id").toString()+"</td><td>"+dbObject.get("count").toString()+"</td></tr>";
			 }
			mongo.close();
			return tableHtml;
		}
	}