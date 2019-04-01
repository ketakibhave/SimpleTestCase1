package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.ReturnsData;
import com.training.dao.ELearningDAO;
import com.training.dao.ReturnDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		return result;
	}
	
	@DataProvider(name = "db-inputs1")
	public Object [][] getDBDataProductDetails() {

		List<ReturnsData> list = new ReturnDAO().getDetails(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(ReturnsData temp : list){
			Object[]  obj = new Object[8]; 
			obj[0] = temp.getOrderID(); 
			obj[1] = temp.getCustomer(); 
			obj[2]=temp.getFirstName();
			obj[3]=temp.getLastName();
			obj[4]=temp.getEmail();
			obj[5]=temp.getPhone();
			obj[6]=temp.getProduct();
			obj[7]=temp.getModel();
			result[count ++] = obj; 
		}
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:\\Automation training\\Project\\Testing1.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName, 0); 
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}
