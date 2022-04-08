package com.mire.studentManagement;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class DBUtility {
	//�ɹ��Լ� �����ͺ��̽����ӿ�û
	//Ŀ�ؼ��� �����ͺ��̽��� ������ ���� �ڵ鰪
	public static Connection getConnection() {
		Connection con=null;
		
		FileReader fr=null;
		
		try {
			fr=new FileReader("src\\com\\mire\\studentManagement\\db.properties");
			Properties properties=new Properties();
			properties.load(fr);
			
			String Driver=properties.getProperty("Driver");
			String URL=properties.getProperty("URL");
			String userID=properties.getProperty("userID");
			String userPW=properties.getProperty("userPW");
			//����̹� ����
			Class.forName(Driver);
			//�����ͺ��̽�����
			con=(Connection) DriverManager.getConnection(URL, userID, userPW);
		} catch (ClassNotFoundException e) {
			System.out.println("mysql database connection fail");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("mysql database connection fail");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return con;
		
	}
}

