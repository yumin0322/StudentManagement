package com.mire.studentManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBController {

	public static int studentInsert(StudentModel studentModel) {
		// 1.�����ͺ��̽� ����
		Connection con=DBUtility.getConnection();
		//�����ͺ��̽� ��ɹ� ���ϰ�
		int Value=0;
				
		if(con==null) {
				System.out.println("����");
				return 0;
		}
		//2.��ɹ� �ϴ�(���Ը�ɹ��ϴ� : ���������� : c u r d)insert
		String insertQuery = "insert into studentprojectdb.studentprojecttbl values(?,?,?,?,?,?,?,?,?,?)";
				
		PreparedStatement ps=null;
				
		try {
			//getTotal()=studentModel.getKorean()+studentModel.getMath()+studentModel.getEnglish();
			//insert query ���� binding
			ps=(PreparedStatement) con.prepareStatement(insertQuery);
			ps.setString(1, studentModel.getName());
			ps.setString(2, studentModel.getGender());
			ps.setString(3,studentModel.getBirthday());
			ps.setInt(4, studentModel.getId());
			ps.setInt(5, studentModel.getKorean());
			ps.setInt(6, studentModel.getMath());
			ps.setInt(7, studentModel.getEnglish());
			ps.setInt(8, studentModel.getTotal());
			ps.setDouble(9, studentModel.getAvr());
			ps.setString(10, studentModel.getGrade());
			
			//��ɹ� ����, ���ϰ� �ޱ�(������ ������ �����Ѵ�)
					
			Value=ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
					
		}finally {
			try {
				if(ps!=null && ps.isClosed()) {
					ps.close();
						
				}
				if(con!=null && con.isClosed()) {
					con.close();
							
				}
			}catch(SQLException e) {
					e.printStackTrace();
			}
		}
		return Value;
	}

	public static List<StudentModel> studentSelect() {
		//���̺� �մ� ���ڵ� ���� �������� ���� ���� ����Ʈ 
		List<StudentModel> list=new ArrayList<StudentModel>();
				
		//�����ͺ��̽����ӿ�û
		Connection con=DBUtility.getConnection();
		//�����ͺ��̽� ��ɹ� ���ϰ�
		if(con==null) {
			System.out.println("����");
			return null;
		}
		//��ɹ��ϴ�(select ������ ��ɹ� �ϴ� : select * from phonebookdb.phonebooktbl;)
		String selectQuery = "select * from studentprojectdb.studentprojecttbl";
		
		PreparedStatement ps=null;
		ResultSet Value=null;
		try {
			//select query ���� binding
			ps=(PreparedStatement) con.prepareStatement(selectQuery);

			//��ɹ� ����, ���ϰ� �ޱ�(������ ������ �����Ѵ�)
			//���ڵ� ��(=ResultSet)�� ����
			//���ϰ� ����(executeQuery�� executeUpdate ����)
			Value=ps.executeQuery();
			//���ڵ���� ����Ʈ�� �����´�
			while(Value.next()) {
				String name=Value.getString(1);
				String gender=Value.getString(2);
				String birthday=Value.getString(3);
				int id=Value.getInt(4);
				int korean=Value.getInt(5);
				int math=Value.getInt(6);
				int english=Value.getInt(7);
				int total=Value.getInt(8);
				double avr=Value.getDouble(9);
				String grade=Value.getString(10);
				
				StudentModel studentModel=new StudentModel(name,gender,birthday,id,korean,math,english,total,avr,grade);
			    list.add(studentModel);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(ps!=null && ps.isClosed()) {
					ps.close();
					
				}
				if(con!=null && con.isClosed()) {
					con.close();
					
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		//���ϰ�
		//������뺸
		return (ArrayList<StudentModel>) list;
	}

	public static List<StudentModel> studentSearch(int searchData, int number) {
		final int ID=1;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		//���ڵ���� �����ϱ� ���� ����Ʈ
		List<StudentModel> list=new ArrayList<StudentModel>();
		//�����ͺ��̽� ���ӿ�û
		Connection con=DBUtility.getConnection();
		//�����ͺ��̽� ��ɹ� ���ϰ�
		if(con==null) {
			System.out.println("����");
			return null;
		}
		
		//��ɹ��ϴ�(�˻���ɹ��ϴ� : ���������� ��ɹ��ϴ� :  select * from phonebookdb.phonebooktbl where phoneNumber like '000-0000-0000';)
		String searchQuery=null;
		
		
		switch(number) {
		case ID: searchQuery= "select * from studentprojectdb.studentprojecttbl where id like ?";
			break;
		}
		
		//select query binding
		try {
			ps=(PreparedStatement) con.prepareStatement(searchQuery);
			ps.setInt(1, searchData);
			//��ɹ� ����
			resultSet =ps.executeQuery();
			//executeUpdate :  ����(������ ���)/ executeQuery :  �����(resultSet) *����*
			
			//���ϰ� resultSet�� arraylist�� ����
			while(resultSet.next()) {
				
				String name=resultSet.getString(1);
				String gender=resultSet.getString(2);
				String birthday=resultSet.getString(3);
				int id=resultSet.getInt(4);
				int korean=resultSet.getInt(5);
				int math=resultSet.getInt(6);
				int english=resultSet.getInt(7);
				int total=resultSet.getInt(8);
				double avr=resultSet.getDouble(9);
				String grade=resultSet.getString(10);
				
				StudentModel studentModel=new StudentModel(name,gender,birthday,id,korean,math,english,total,avr,grade);
			    list.add(studentModel);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//���ϰ�
		//����� �뺸

		return (ArrayList<StudentModel>) list;
	}

	public static int studentDelete(int deleteData, int number) {
		final int ID=1;
		PreparedStatement ps=null;
		int resultNumber=0;

		//�����ͺ��̽� ���ӿ�û
		Connection con=DBUtility.getConnection();
		//�����ͺ��̽� ��ɹ� ���ϰ�
		if(con==null) {
			System.out.println("����");
			return 0;
		}
		
		//��ɹ��ϴ�(�������ϴ� : ���������� ��ɹ��ϴ� :  select * from phonebookdb.phonebooktbl where phoneNumber like '000-0000-0000';)
		String deleteQuery=null;
		
		
		switch(number) {
		case ID: deleteQuery= "delete from studentprojectdb.studentprojecttbl where id like ?";
			break;
		
		}
		
		//select query binding
		try {
			ps=(PreparedStatement) con.prepareStatement(deleteQuery);
			ps.setInt(1, deleteData);
			//��ɹ� ����
			resultNumber =ps.executeUpdate();
			//executeUpdate :  ����(������ ���)/ executeQuery :  �����(resultSet) *����*
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//���ϰ�
		//����� �뺸

		return resultNumber;
	}

	public static int studentUpdate(StudentModel studentModel) {
		final int ID=1;
		PreparedStatement ps=null;
		int resultNumber=0;

		//�����ͺ��̽� ���ӿ�û
		Connection con=DBUtility.getConnection();
		//�����ͺ��̽� ��ɹ� ���ϰ�
		if(con==null) {
			System.out.println("����");
			return 0;
		}
		
		//��ɹ��ϴ�(�������ϴ� : ���������� ��ɹ��ϴ� :  select * from phonebookdb.phonebooktbl where phoneNumber like '000-0000-0000';)
		String updateQuery=null;
		
		
		switch(ID) {
		case ID: updateQuery= "update studentprojectdb.studentprojecttbl set korean=?, math=?, english=?, total=?, avr=?, grade=? where id=?";
			break;
		}
		
		//select query binding
		try {
			ps=(PreparedStatement) con.prepareStatement(updateQuery);
			ps.setInt(1, studentModel.getKorean());
			ps.setInt(2, studentModel.getEnglish());
			ps.setInt(3, studentModel.getMath());
			ps.setInt(4, studentModel.getTotal());
			ps.setDouble(5, studentModel.getAvr());
			ps.setString(6, studentModel.getGrade());
			ps.setInt(7, studentModel.getId());
			//��ɹ� ����
			resultNumber =ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultNumber;
	}

	public static List<StudentModel> studentSort(int no) {
		final int ASC=1, DESC=2;
		String sortQuery=null;
		//���̺� �մ� ���ڵ� ���� �������� ���� ���� ����Ʈ 
		List<StudentModel> list=new ArrayList<StudentModel>();
						
		//�����ͺ��̽����ӿ�û
		Connection con=DBUtility.getConnection();
		//�����ͺ��̽� ��ɹ� ���ϰ�
		if(con==null) {
			System.out.println("����");
			return null;
		}
		//��ɹ��ϴ�(select ������ ��ɹ� �ϴ� : select * from phonebookdb.phonebooktbl;)
		switch(no) {
		case ASC: sortQuery = "select * from studentprojectdb.studentprojecttbl order by total asc";break;
		case DESC: sortQuery = "select * from studentprojectdb.studentprojecttbl order by total desc";break;
		}
		
				
		PreparedStatement ps=null;
		ResultSet Value=null;
		try {
				//query ���� binding
				ps=(PreparedStatement) con.prepareStatement(sortQuery);

					//��ɹ� ����, ���ϰ� �ޱ�(������ ������ �����Ѵ�)
					//���ڵ� ��(=ResultSet)�� ����
					//���ϰ� ����(executeQuery�� executeUpdate ����)
					Value=ps.executeQuery();
					//���ڵ���� ����Ʈ�� �����´�
					while(Value.next()) {
						String name=Value.getString(1);
						String gender=Value.getString(2);
						String birthday=Value.getString(3);
						int id=Value.getInt(4);
						int korean=Value.getInt(5);
						int math=Value.getInt(6);
						int english=Value.getInt(7);
						int total=Value.getInt(8);
						double avr=Value.getDouble(9);
						String grade=Value.getString(10);
						
						StudentModel phoneBookModel=new StudentModel(name,gender,birthday,id,korean,math,english,total,avr,grade);
					    list.add(phoneBookModel);
					}
			}catch(SQLException e) {
					e.printStackTrace();
					
			}finally {
					try {
						if(ps!=null && ps.isClosed()) {
							ps.close();
							
						}
						if(con!=null && con.isClosed()) {
							con.close();
							
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
			}

				return (ArrayList<StudentModel>) list;
	}

	

}
