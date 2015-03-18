package com.rest.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rest.model.CatlogItem;
import com.rest.model.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class Database {
    
        public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/meatcart",
                    "raghu", "raghu123");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage() );
            ex.printStackTrace();
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }

    public static ResultSet runSelectQuery(String query) {
        ResultSet rs = null;
            try {
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        return rs;
    }

    public static boolean checkLogin(String uname, String pwd) throws Exception {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = getConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM meatcart_user WHERE username = '" + uname
                    + "' AND password=" + "'" + pwd + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }
    /**
     * Method to insert uname and pwd in DB
     * 
     * @param name
     * @param uname
     * @param pwd
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertUser (String uname, String pwd) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = getConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into meatcart_user( username, password) values('"
                    + uname + "','" + pwd + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }

	public static User getUserDetails(String uname, String pwd) throws SQLException {
		User user;
		 Connection dbConn = null;
	        try {
	            try {
	                dbConn = getConnection();
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            Statement stmt = dbConn.createStatement();
	            String query = "SELECT * FROM meatcart_user WHERE username = '" + uname
	                    + "' AND password=" + "'" + pwd + "'";
	            System.out.println(query);
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	            	System.out.println(rs.getString("username")+rs.getString("password")+rs.getString("email")+""+rs.getInt("user_id"));
	               user = new User(rs.getString("username"),rs.getString("password"),rs.getString("email"),"",rs.getInt("user_id"));
	               return user;
	            }
	        } catch (SQLException sqle) {
	            throw sqle;
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            if (dbConn != null) {
	                dbConn.close();
	            }
	            throw e;
	        } finally {
	            if (dbConn != null) {
	                dbConn.close();
	            }
	        }
		return null;
	}

	public static List<CatlogItem> getCatlogItemDetails() throws SQLException {
		// TODO Auto-generated method stub
		List<CatlogItem> catlogList = new ArrayList<CatlogItem>();
		CatlogItem catlogItem;
		Connection dbConn = null;
        try {
            try {
                dbConn = getConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM Catlog";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	//System.out.println(rs.getString("username")+rs.getString("password")+rs.getString("email")+""+rs.getInt("user_id"));
            	catlogItem = new CatlogItem(rs.getInt("itemId"),rs.getString("itemName"),rs.getString("itemDescription"),rs.getString("image"),rs.getInt("quantityAvailable"),rs.getInt("price"));
               catlogList.add(catlogItem);
               }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
		return catlogList;
	}
}

