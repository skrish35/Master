package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import persistance.bean.Payment;
import persistance.util.DbUtil;

public class PaymentDao {

    private Connection connection;

    public PaymentDao() {
        connection = DbUtil.getConnection();
    }

    public void addPayment(persistance.bean.Payment payment) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into T_Payment(OrderNumber, name, value)"
                    		+ " values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, payment.getOrderNumber());
            preparedStatement.setString(2, payment.getName());
            preparedStatement.setInt(3, payment.getValue());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(int orderNum) {
    	System.out.println("INSIDE DELETE");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from T_Payment where orderNumber=?");
            // Parameters start with 1
            preparedStatement.setInt(1, orderNum);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePayment(persistance.bean.Payment payment) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update T_Payment set name=?, value=?, " +
                            "where orderNumber=?");
            // Parameters start with 1
            preparedStatement.setString(1, payment.getName());
            preparedStatement.setInt(2, payment.getValue());
            preparedStatement.setInt(3, payment.getOrderNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<Payment>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from T_Payment");
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setOrderNumber(rs.getInt("orderNumber"));
                payment.setName(rs.getString("name"));
                payment.setValue(rs.getInt("value"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    public Payment getPaymentById(int orderNumber) {
        Payment payment = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from T_Payment where orderNumber=?");
            preparedStatement.setInt(1, orderNumber);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	payment = new Payment();
            	payment.setOrderNumber(rs.getInt("orderNumber"));
            	payment.setName(rs.getString("name"));
            	payment.setValue(rs.getInt("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }

	public List<Payment> getPaymentByPaymentName(String name) {
		List<Payment> payments = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from T_Payment where name=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	Payment payment = new Payment();
            	payment.setOrderNumber(rs.getInt("orderNumber"));
            	payment.setName(rs.getString("name"));
            	payment.setValue(rs.getInt("value"));
            	payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }
	
	public int getPaymentCount() {
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select count(*) as count from T_Payment");
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
				count = rs.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int getMaxOrderNum() {
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select max(orderNumber) as max from T_Payment");
					//prepareStatement("select max(orderId) as max from T_order");
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
				count = rs.getInt("max");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
