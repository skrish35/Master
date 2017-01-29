package persistance.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import persistance.bean.Products;
import persistance.util.DbUtil;

import com.mysql.jdbc.PreparedStatement;

public class ProductDao {

    private Connection connection;

    public ProductDao() {
        connection = DbUtil.getConnection();
    }

    public boolean addProduct(Products product) {
    	 boolean successflag=false;
    	try {
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("insert into Product(pid, pname, retailer, image, cond, price)"
                    		+ " values (?, ?, ?, ?, ?, ? )");
           
            // Parameters start with 1
            preparedStatement.setString(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getRetailer());
            preparedStatement.setString(4, product.getImage());
            preparedStatement.setString(5, product.getCondition());
            preparedStatement.setInt(6, product.getPrice());
            preparedStatement.executeUpdate();
            successflag=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return successflag;
    }
    
    public void updateProduct(Products product) {
        try {
        	String updateSQL="update Product set pname=?, price=?, cond=? where pid=?";
        	System.out.println("SQL: "+updateSQL);
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement(updateSQL);
            
            // Parameters start with 1
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setString(3, product.getCondition());
           /* preparedStatement.setString(4, product.getRetailer());
            preparedStatement.setString(5, product.getImage());*/
            preparedStatement.setString(4, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteProduct(String productId) {
    	boolean successflag=false;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("delete from Product where pid=?");
            // Parameters start with 1
            preparedStatement.setString(1, productId);
            preparedStatement.executeUpdate();
            successflag=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return successflag;
    }
    
    public Products getProductById(String id) {
    	System.out.println("INSIDE PRODUCTBYID-----------");
        Products product = null;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.
                    prepareStatement("select * from Product where pid=?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	product = new Products();
            	product.setId(rs.getString("pid"));
                product.setName(rs.getString("pname"));
                product.setRetailer(rs.getString("retailer"));
                product.setCondition(rs.getString("cond"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
    
    
    
    public HashMap<String,Products> getAllProducts() {
       HashMap<String,Products> pmap=new HashMap<String,Products>();
        String pid;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from PRODUCT");
            while (rs.next()) {
                Products product = new Products();
                pid=rs.getString("pid");
                product.setId(pid);
                product.setName(rs.getString("pname"));
                product.setRetailer(rs.getString("retailer"));
                product.setCondition(rs.getString("cond"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));
                pmap.put(pid, product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pmap;
    }

   
}