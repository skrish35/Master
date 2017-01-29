package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import persistance.bean.Order;
import persistance.util.DbUtil;

public class OrderDao {

    private Connection connection;

    public OrderDao() {
        connection = DbUtil.getConnection();
    }

    public void addOrder(persistance.bean.Order order) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into T_Order(OrderNumber,prodId)"
                    		+ " values (?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, order.getOrderNumber());
            preparedStatement.setString(2, order.getProdId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderNum) {
        try {
        	System.out.println("delete from T_Order where orderId="+orderNum);
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from T_Order where orderNumber=?");
            // Parameters start with 1
            preparedStatement.setInt(1, orderNum);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* public void updateOrder(persistance.bean.Order order) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update T_Order set name=?, value=?, " +
                            "where orderNumber=?");
            // Parameters start with 1
            preparedStatement.setString(1, order.getName());
            preparedStatement.setInt(2, order.getValue());
            preparedStatement.setInt(3, order.getOrderNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from T_Order");
            while (rs.next()) {
                Order order = new Order();
                order.setOrderNumber(rs.getInt("orderNumber"));
                order.setProdId(rs.getString("prodId"));
                order.setOrderId(rs.getInt("orderId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getOrderById(int orderNumber) {
    	System.out.println("UUUUUUUUUUUUUUUUUUUUUU");
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from T_Order where orderNumber=?");
            preparedStatement.setInt(1, orderNumber);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	System.out.println("Count sssssss");
                Order order = new Order();
                order.setOrderNumber(rs.getInt("orderNumber"));
                order.setProdId(rs.getString("prodId"));
                order.setOrderId(rs.getInt("orderId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

	public List<Order> getOrderByOrderName(String name) {
		List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from T_Order where name=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setOrderNumber(rs.getInt("orderNumber"));
                order.setProdId(rs.getString("prodId"));
                order.setOrderId(rs.getInt("orderId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
	
	public int getOrderCount() {
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select count(*) from T_Order");
			ResultSet rs = preparedStatement.executeQuery();
			count = rs.getInt("count(*)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int getMaxOrderNum() {
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select max(orderNumber) from T_Order");
			ResultSet rs = preparedStatement.executeQuery();
			count = rs.getInt("max(orderNumber)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	/*public List<String> getTrendingItems() {
		List<String> proddlist = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select prodName,count(productorder.productId) as count, productstore.productId from productorder inner join ORDERS on productorder.orderId=ORDERS.orderId inner join productStore on productorder.productId=productStore.productId group by productId order by count(productId) desc limit 5;");
			ResultSet rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next() && i<5) {
				proddlist.add(rs.getString("prodId"));
				i++;
            }
			System.out.println(proddlist.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return proddlist;
	}*/
	
	public String getTrendingItems() {
		//List<String> proddlist = new ArrayList<String>();
		String rowHtml="";
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select pname,count(t_order.prodId) as count, product.pid from t_payment inner join t_order on t_payment.orderNumber=t_order.orderNumber inner join product on t_order.prodId=product.pid group by pid order by count(pid) desc limit 5;");
			ResultSet rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next() && i<5) {
				//proddlist.add(rs.getString("prodId"));
				rowHtml=rowHtml+"<tr><td>"+rs.getString("pname")+"</td><td>"+rs.getString("pid")+"</td><td>"+rs.getString("count")+"</td></tr>";
				i++;
            }
			//System.out.println(proddlist.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowHtml;
	}
}
