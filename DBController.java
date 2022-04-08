package com.mire.studentManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBController {

	public static int studentInsert(StudentModel studentModel) {
		// 1.데이터베이스 접속
		Connection con=DBUtility.getConnection();
		//데이터베이스 명령문 리턴값
		int Value=0;
				
		if(con==null) {
				System.out.println("실패");
				return 0;
		}
		//2.명령문 하달(삽입명령문하달 : 쿼리문으로 : c u r d)insert
		String insertQuery = "insert into studentprojectdb.studentprojecttbl values(?,?,?,?,?,?,?,?,?,?)";
				
		PreparedStatement ps=null;
				
		try {
			//getTotal()=studentModel.getKorean()+studentModel.getMath()+studentModel.getEnglish();
			//insert query 문에 binding
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
			
			//명령문 실행, 리턴값 받기(삽입한 갯수를 리턴한다)
					
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
		//테이블에 잇는 레코드 셋을 가져오기 위한 얼레이 리스트 
		List<StudentModel> list=new ArrayList<StudentModel>();
				
		//데이터베이스접속요청
		Connection con=DBUtility.getConnection();
		//데이터베이스 명령문 리턴값
		if(con==null) {
			System.out.println("실패");
			return null;
		}
		//명령문하달(select 쿼리문 명령문 하달 : select * from phonebookdb.phonebooktbl;)
		String selectQuery = "select * from studentprojectdb.studentprojecttbl";
		
		PreparedStatement ps=null;
		ResultSet Value=null;
		try {
			//select query 문에 binding
			ps=(PreparedStatement) con.prepareStatement(selectQuery);

			//명령문 실행, 리턴값 받기(삽입한 갯수를 리턴한다)
			//레코드 셋(=ResultSet)을 리턴
			//리턴값 주의(executeQuery와 executeUpdate 구분)
			Value=ps.executeQuery();
			//레코드셋을 리스트로 가져온다
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
		//리턴값
		//결과값통보
		return (ArrayList<StudentModel>) list;
	}

	public static List<StudentModel> studentSearch(int searchData, int number) {
		final int ID=1;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		//레코드셋을 저장하기 위한 리스트
		List<StudentModel> list=new ArrayList<StudentModel>();
		//데이터베이스 접속요청
		Connection con=DBUtility.getConnection();
		//데이터베이스 명령문 리턴값
		if(con==null) {
			System.out.println("실패");
			return null;
		}
		
		//명령문하달(검색명령문하달 : 쿼리문으로 명령문하달 :  select * from phonebookdb.phonebooktbl where phoneNumber like '000-0000-0000';)
		String searchQuery=null;
		
		
		switch(number) {
		case ID: searchQuery= "select * from studentprojectdb.studentprojecttbl where id like ?";
			break;
		}
		
		//select query binding
		try {
			ps=(PreparedStatement) con.prepareStatement(searchQuery);
			ps.setInt(1, searchData);
			//명령문 실행
			resultSet =ps.executeQuery();
			//executeUpdate :  숫자(참거짓 등등)/ executeQuery :  결과값(resultSet) *구분*
			
			//리턴값 resultSet을 arraylist로 리턴
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
		//리턴값
		//결과값 통보

		return (ArrayList<StudentModel>) list;
	}

	public static int studentDelete(int deleteData, int number) {
		final int ID=1;
		PreparedStatement ps=null;
		int resultNumber=0;

		//데이터베이스 접속요청
		Connection con=DBUtility.getConnection();
		//데이터베이스 명령문 리턴값
		if(con==null) {
			System.out.println("실패");
			return 0;
		}
		
		//명령문하달(삭제문하달 : 쿼리문으로 명령문하달 :  select * from phonebookdb.phonebooktbl where phoneNumber like '000-0000-0000';)
		String deleteQuery=null;
		
		
		switch(number) {
		case ID: deleteQuery= "delete from studentprojectdb.studentprojecttbl where id like ?";
			break;
		
		}
		
		//select query binding
		try {
			ps=(PreparedStatement) con.prepareStatement(deleteQuery);
			ps.setInt(1, deleteData);
			//명령문 실행
			resultNumber =ps.executeUpdate();
			//executeUpdate :  숫자(참거짓 등등)/ executeQuery :  결과값(resultSet) *구분*
			
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
		//리턴값
		//결과값 통보

		return resultNumber;
	}

	public static int studentUpdate(StudentModel studentModel) {
		final int ID=1;
		PreparedStatement ps=null;
		int resultNumber=0;

		//데이터베이스 접속요청
		Connection con=DBUtility.getConnection();
		//데이터베이스 명령문 리턴값
		if(con==null) {
			System.out.println("실패");
			return 0;
		}
		
		//명령문하달(삭제문하달 : 쿼리문으로 명령문하달 :  select * from phonebookdb.phonebooktbl where phoneNumber like '000-0000-0000';)
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
			//명령문 실행
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
		//테이블에 잇는 레코드 셋을 가져오기 위한 얼레이 리스트 
		List<StudentModel> list=new ArrayList<StudentModel>();
						
		//데이터베이스접속요청
		Connection con=DBUtility.getConnection();
		//데이터베이스 명령문 리턴값
		if(con==null) {
			System.out.println("실패");
			return null;
		}
		//명령문하달(select 쿼리문 명령문 하달 : select * from phonebookdb.phonebooktbl;)
		switch(no) {
		case ASC: sortQuery = "select * from studentprojectdb.studentprojecttbl order by total asc";break;
		case DESC: sortQuery = "select * from studentprojectdb.studentprojecttbl order by total desc";break;
		}
		
				
		PreparedStatement ps=null;
		ResultSet Value=null;
		try {
				//query 문에 binding
				ps=(PreparedStatement) con.prepareStatement(sortQuery);

					//명령문 실행, 리턴값 받기(삽입한 갯수를 리턴한다)
					//레코드 셋(=ResultSet)을 리턴
					//리턴값 주의(executeQuery와 executeUpdate 구분)
					Value=ps.executeQuery();
					//레코드셋을 리스트로 가져온다
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
