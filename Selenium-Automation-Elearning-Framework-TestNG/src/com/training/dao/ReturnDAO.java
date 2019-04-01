package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import com.training.bean.LoginBean;
import com.training.bean.ReturnsData;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

public class ReturnDAO {
Properties properties; 
	
	public ReturnDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ReturnsData> getDetails(){
		String sql = properties.getProperty("get.details"); 
		
		GetConnection gc  = new GetConnection(); 
		List<ReturnsData> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<ReturnsData>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				ReturnsData temp = new ReturnsData(); 
				temp.setOrderID(gc.rs1.getString(1));
				temp.setCustomer(gc.rs1.getString(2));
				temp.setFirstName(gc.rs1.getString(3));
				temp.setLastName(gc.rs1.getString(4));
				temp.setEmail(gc.rs1.getString(5));
				temp.setPhone(gc.rs1.getString(6));
				temp.setProduct(gc.rs1.getString(7));
				temp.setModel(gc.rs1.getString(8));

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public static void main(String[] args) {
		new ReturnDAO().getDetails().forEach(System.out :: println);
	}
}
