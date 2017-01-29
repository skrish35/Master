package persistance.bean;

public class UserReview {

		String pname;
	    String rating;
	    String rtext;
	    String uid;
	    
	    public UserReview(String pname,String rating,String rtext,String uid){
	    	this.pname=pname;
	    	this.rating=rating;
	    	this.rtext=rtext;
	    	this.uid=uid;
	    }
	    
		public String getPname() {
			return pname;
		}
		public void setPname(String pname) {
			this.pname = pname;
		}
		public String getRating() {
			return rating;
		}
		public void setRating(String rating) {
			this.rating = rating;
		}
		public String getRtext() {
			return rtext;
		}
		public void setRtext(String rtext) {
			this.rtext = rtext;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}

}
