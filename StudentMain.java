package com.mire.studentManagement;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StudentMain {
	public static final int SUBJECT=3; //과목수
	public static Scanner scan=new Scanner(System.in);
	public static final int INSERT=1, SEARCH=2, DELETE=3, REWRITE=4, PRINT=5, SORT=6, EXIT=7;


	public static void main(String[] args) {
		boolean flag=false;
		while(!flag) {
			//메뉴선택
			int no=selectMenu();
			switch(no) {
			case INSERT:studentInsert();break; //입력
			case SEARCH:studentSearch();break; //검색
			case DELETE:studentDelete();break; //삭제
			case REWRITE:studentRewrite();break; //수정
			case PRINT:studentPrint();break; //출력
			case SORT:studentSort();break; //정렬
			case EXIT:flag=true;break; //종료
			}
			 
			
		}

	}
	
	//입력(이름, 성별, 생일, 학번, 국어점수, 수학점수, 영어점수)
	private static void studentInsert() {

		String name=null;
		String gender=null;
		String birthday=null;
		int id, korean, math, english, total=0;
		double avr=0.0;
		String grade=null;

		//이름
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t이름을 입력하세요(홍길동) >>");
			name=scan.nextLine();
			if(patternCheck(name, 1)){
				break;
			}else {
				System.out.println("\t다시 입력해주세요");
			}
		}
		//성별
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t성별을 입력하세요(남자, 여자) >>");
			gender=scan.nextLine();
			if(gender.equals("남자")||gender.equals("여자")) {
				break;
			}else {
				System.out.println("\t다시 입력하세요.");
			}
		}
		//생일
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t생년월일을 입력하세요(1995-08-29) >>");
			birthday=scan.nextLine();
			if(patternCheck(birthday,2)) {
				break;
			}else {
				System.out.println("\t다시 입력해주세요");
			}
		}
		//학번
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t학번을 입력하세요(1000~9999) >>");
			id=scan.nextInt();
			if(id>=1000 && id<=9999) {
				break;
			}else {
				System.out.println("\t(1000~9999)사이로 다시 입력해주세요.");
			}
		}
		//국어,수학,영어점수
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t국어 점수를 입력해주세요(0~100점) >>");
			korean=scan.nextInt();
			if(korean>=0 && korean<=100) {
				break;
			}else {
				System.out.println("\t(0~100점)사이로 다시 입력해주세요.");
			}
		}
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t수학 점수를 입력해주세요(0~100점) >>");
			math=scan.nextInt();
			if(math>=0 && math<=100) {
				break;
			}else {
				System.out.println("\t(0~100점)사이로 다시 입력해주세요.");
			}
		}
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t영어 점수를 입력해주세요(0~100점) >>");
			english=scan.nextInt();
			if(english>=0 && english<=100) {
				break;
			}else {
				System.out.println("\t(0~100점)사이로 다시 입력해주세요.");
			}
		}
		total=korean+math+english;
		avr=total/(double)SUBJECT;
		grade=checkGrade(avr);
		StudentModel studentModel=new StudentModel(name,gender,birthday,id,korean,math,english,total,avr,grade);

		
		int returnValue=DBController.studentInsert(studentModel);
		if(returnValue!=0) {
			System.out.println("\t______________________________");
			System.out.println("\t"+studentModel.getName()+"님 입력 완료했습니다");
		}else {
			System.out.println("\t입력 실패했습니다.");  
		}
	}
	public static String checkGrade(double avr) {
		String grade=null;
		if(avr>=90.0) {
			grade="A";
		}else if(avr>=80.0) {
			grade="B";
		}else if(avr>=70.0) {
			grade="C";
		}else if(avr>=60.0) {
			grade="D";
		}else {
			grade="F";
		}
		return grade;
	}
	//패턴매치 처리함수(이름, 생년월일)
	private static boolean patternCheck(String patternData, int patternType) {
		final int NAME=1, BIRTHDAY=2;
		String filter=null;
		switch(patternType) {
		case NAME : filter="^[가-힣]{2,5}$";break;
		case BIRTHDAY : filter="^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";break;
		}
		Pattern pattern = Pattern.compile(filter);
	    Matcher matcher = pattern.matcher(patternData);
	    
	    return matcher.matches();
		
	}
	//출력
	private static void studentPrint() {
		List<StudentModel> list=new ArrayList<StudentModel>();
		list=DBController.studentSelect();
		if(list.size()<=0) {
			return;
		}
		System.out.println("\t[\t이름\t성별\t   생일   \t학번\t국어점수\t수학점수\t영어점수\t총점\t평균\t등급\t]");
		System.out.println();
		for(StudentModel data : list) {
			System.out.println(data.toString());
		}
	}
	//검색
	private static void studentSearch() {
		final int ID=1, EXIT=2;
		List<StudentModel> list=new ArrayList<StudentModel>();
		//검색할 내용을 선택 요청(전화번호, 이름)
		int id=0;
		int searchData=0;
		int no =0;
		int number =0;
		boolean flag=false;
		no = selecSearchtMenu();
		
		switch(no) {
		case ID: 
			while(true) {
				System.out.print("\t검색할 학번 입력 >>");
				id=scan.nextInt();
				
				if(id>=1000&&id<=9999) {
					break;
				}else {
					System.out.println("\t다시 입력해주세요");
				}
				
			}
			searchData=id;
			number=ID;
			break;
		
		case EXIT: System.out.println("\t검색취소");flag=true;break;
		}
		if(flag) {
			return;
		}

		list=DBController.studentSearch(searchData, number);
		
		if(list.size()<=0) {
			System.out.println("\t"+searchData+"찾는 정보가 없습니다.");
			return;
		}
		for(StudentModel data : list) {
			System.out.println(data);
		}
		
	}
	//삭제(전화번호로/프라이머리키)
	private static void studentDelete() {
		final int ID=1;
		int id=0;
		int deleteData=0;
		int number=0;
		int resultNumber=0;
		while(true) {
			System.out.print("\t삭제할 학번 입력 >>");
			id=scan.nextInt();
				
			if(id>=1000&&id<=9999) {
				break;
			}else {
				System.out.println("\t다시 입력해주세요");
			}
				
		}
		deleteData=id;
		number=ID;
		
		resultNumber=DBController.studentDelete(deleteData, number);
		
		if(resultNumber!=0) {
			System.out.println("\t학번."+id+"학생의 정보가 삭제되었습니다.");
			
		}else {
			System.out.println("\t삭제 실패했습니다.");
		}
		return;
			
		
		
	}
	//수정
	private static void studentRewrite() {

		final int ID=1;
		List<StudentModel> list=new ArrayList<StudentModel>();
		//검색할 내용을 선택 요청
		int id, korean, math, english=0;
		int searchData, number=0;
		int resultNumber=0;
		int total=0;
		double avr=0.0;
		String grade=null;

		 
		while(true) {
			System.out.print("\t수정할 학번 입력 >>");
			id=scan.nextInt();
				
			if(id>=1000&&id<=9999) {
				break;
			}else {
				System.out.println("\t다시 입력해주세요");
				}
				
			}
		searchData=id;
		number=ID;

		list=DBController.studentSearch(searchData, number);
		
		if(list.size()<=0) {
			System.out.println(searchData+"찾는 정보가 없습니다.");
			return;
		}
		StudentModel data_buffer=null;
		for(StudentModel data : list) {
			System.out.println(data);
			data_buffer=data;
		}
		System.out.print("\t"+data_buffer.getKorean()+"-> 수정할 국어 점수 입력 >> ");
		korean=scan.nextInt();
		System.out.print("\t"+data_buffer.getMath()+"-> 수정할 수학 점수 입력 >> ");
		math=scan.nextInt();
		System.out.print("\t"+data_buffer.getEnglish()+"-> 수정할 영어 점수 입력 >> ");
		english=scan.nextInt();
		
		total=korean+math+english;
		avr=total/(double)SUBJECT;
		grade=checkGrade(avr);
		
		data_buffer.setKorean(korean);
		data_buffer.setMath(math);
		data_buffer.setEnglish(english);
		data_buffer.setTotal(total);
		data_buffer.setAvr(avr);
		data_buffer.setGrade(grade);
		
		resultNumber=DBController.studentUpdate(data_buffer);
		
		
		if(resultNumber!=0) {
			System.out.println("\t"+data_buffer.getName()+"님의 성적 수정을 완료 했습니다.");
		}else {
			System.out.println("\t수정 실패했습니다.");
		}
		return;
	}
	//정렬
	private static void studentSort() {
		List<StudentModel> list=new ArrayList<StudentModel>();
		int no=0;
		boolean flag=false;
				
		//1.정렬방식(오름, 내림)
		while(!flag) {
			System.out.println("\t<성적 순 졍렬>");
			System.out.print("\t[1. 오름차순], [2.내림차순]>> ");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t경고) 정확한 값을 입력해주세요.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t경고) 정확한 값을 입력해주세요.");
				continue;
			}
			if(no>=1&&no<=7) {
				flag=true;
				
			}else {
				System.out.println("\t경고 ) 정확한 숫자(1~2)을 입력하세요.");
			}
			
		}//end of while

		//2.정렬 보여주기(출력물을 가져온다.)
		
		list=DBController.studentSort(no);
		if(list.size()<=0) {
			System.out.println("\t정렬할 내용이 없습니다.");
			return;
		}
		for(StudentModel data : list) {
			System.out.println(data.toString());
		}
		return;
		
	}

	private static int selectMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println();
			System.out.println("\t    <학생 성적 관리 프로그램>");
			System.out.println("\t=============================");
			System.out.println("\t1.입력\t 2.조회\t3.삭제\t4.수정\n\t5.출력\t 6.정렬\t7.종료");
			System.out.println("\t=============================");
			System.out.print("\t>>");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t경고) 정확한 값을 입력해주세요.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t경고) 정확한 값을 입력해주세요.");
				continue;
			}
			if(no>=1&&no<=7) {
				flag=true;
				
			}else {
				System.out.println("\t경고 ) 정확한 숫자(1~2)을 입력하세요.");
			
			}
		}
		return no;
	}
	private static int selecSearchtMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("\t=================================================");
			System.out.println("\t검색 번호를 선택하세요 [1.학번 입력], [2.검색취소] ");
			System.out.println("\t=================================================");
			System.out.print("\t>>");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t경고) 정확한 값을 입력해주세요.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t경고) 정확한 값을 입력해주세요.");
				continue;
			}
			if(no>=1&&no<=2) {
				flag=true;
				
			}else {
				System.out.println("\t경고 ) 정확한 숫자(1~2)을 입력하세요.");
			}
		}
		return no;
	}

}
