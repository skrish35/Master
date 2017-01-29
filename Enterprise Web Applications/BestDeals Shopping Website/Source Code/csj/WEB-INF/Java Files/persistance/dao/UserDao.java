package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import persistance.bean.User;
import persistance.util.DbUtil;

public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(persistance.bean.User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into T_User(firstName,lastName,emailId,phoneNum,userName,password,userType)"
                    		+ " values (?, ?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmailId());
            preparedStatement.setString(4, user.getPhoneNum());
            preparedStatement.setString(5, user.getUserName());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getUserType());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from T_User where userid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(persistance.bean.User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update T_User set firstname=?, lastname=?, email=?" +
                            "where userid=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmailId());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from T_User");
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmailId(rs.getString("emailId"));
                user.setPhoneNum(rs.getString("emailId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("userType"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from T_User where userid=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	user.setUserId(rs.getInt("userId"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmailId(rs.getString("emailId"));
                user.setPhoneNum(rs.getString("emailId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("userType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

	public User getUserByUserName(String name) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from T_User where userName=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	user.setUserId(rs.getInt("userId"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmailId(rs.getString("emailId"));
                user.setPhoneNum(rs.getString("emailId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("userType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

	public int getUserCountByUserName(String name) {
		User user = new User();
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select count(*) from T_User where userName=?");
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			count = rs.getInt("count(*)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
