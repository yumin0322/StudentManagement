package com.mire.studentManagement;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StudentMain {
	public static final int SUBJECT=3; //�����
	public static Scanner scan=new Scanner(System.in);
	public static final int INSERT=1, SEARCH=2, DELETE=3, REWRITE=4, PRINT=5, SORT=6, EXIT=7;


	public static void main(String[] args) {
		boolean flag=false;
		while(!flag) {
			//�޴�����
			int no=selectMenu();
			switch(no) {
			case INSERT:studentInsert();break; //�Է�
			case SEARCH:studentSearch();break; //�˻�
			case DELETE:studentDelete();break; //����
			case REWRITE:studentRewrite();break; //����
			case PRINT:studentPrint();break; //���
			case SORT:studentSort();break; //����
			case EXIT:flag=true;break; //����
			}
			 
			
		}

	}
	
	//�Է�(�̸�, ����, ����, �й�, ��������, ��������, ��������)
	private static void studentInsert() {

		String name=null;
		String gender=null;
		String birthday=null;
		int id, korean, math, english, total=0;
		double avr=0.0;
		String grade=null;

		//�̸�
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t�̸��� �Է��ϼ���(ȫ�浿) >>");
			name=scan.nextLine();
			if(patternCheck(name, 1)){
				break;
			}else {
				System.out.println("\t�ٽ� �Է����ּ���");
			}
		}
		//����
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t������ �Է��ϼ���(����, ����) >>");
			gender=scan.nextLine();
			if(gender.equals("����")||gender.equals("����")) {
				break;
			}else {
				System.out.println("\t�ٽ� �Է��ϼ���.");
			}
		}
		//����
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t��������� �Է��ϼ���(1995-08-29) >>");
			birthday=scan.nextLine();
			if(patternCheck(birthday,2)) {
				break;
			}else {
				System.out.println("\t�ٽ� �Է����ּ���");
			}
		}
		//�й�
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t�й��� �Է��ϼ���(1000~9999) >>");
			id=scan.nextInt();
			if(id>=1000 && id<=9999) {
				break;
			}else {
				System.out.println("\t(1000~9999)���̷� �ٽ� �Է����ּ���.");
			}
		}
		//����,����,��������
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t���� ������ �Է����ּ���(0~100��) >>");
			korean=scan.nextInt();
			if(korean>=0 && korean<=100) {
				break;
			}else {
				System.out.println("\t(0~100��)���̷� �ٽ� �Է����ּ���.");
			}
		}
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t���� ������ �Է����ּ���(0~100��) >>");
			math=scan.nextInt();
			if(math>=0 && math<=100) {
				break;
			}else {
				System.out.println("\t(0~100��)���̷� �ٽ� �Է����ּ���.");
			}
		}
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t���� ������ �Է����ּ���(0~100��) >>");
			english=scan.nextInt();
			if(english>=0 && english<=100) {
				break;
			}else {
				System.out.println("\t(0~100��)���̷� �ٽ� �Է����ּ���.");
			}
		}
		total=korean+math+english;
		avr=total/(double)SUBJECT;
		grade=checkGrade(avr);
		StudentModel studentModel=new StudentModel(name,gender,birthday,id,korean,math,english,total,avr,grade);

		
		int returnValue=DBController.studentInsert(studentModel);
		if(returnValue!=0) {
			System.out.println("\t______________________________");
			System.out.println("\t"+studentModel.getName()+"�� �Է� �Ϸ��߽��ϴ�");
		}else {
			System.out.println("\t�Է� �����߽��ϴ�.");  
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
	//���ϸ�ġ ó���Լ�(�̸�, �������)
	private static boolean patternCheck(String patternData, int patternType) {
		final int NAME=1, BIRTHDAY=2;
		String filter=null;
		switch(patternType) {
		case NAME : filter="^[��-�R]{2,5}$";break;
		case BIRTHDAY : filter="^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";break;
		}
		Pattern pattern = Pattern.compile(filter);
	    Matcher matcher = pattern.matcher(patternData);
	    
	    return matcher.matches();
		
	}
	//���
	private static void studentPrint() {
		List<StudentModel> list=new ArrayList<StudentModel>();
		list=DBController.studentSelect();
		if(list.size()<=0) {
			return;
		}
		System.out.println("\t[\t�̸�\t����\t   ����   \t�й�\t��������\t��������\t��������\t����\t���\t���\t]");
		System.out.println();
		for(StudentModel data : list) {
			System.out.println(data.toString());
		}
	}
	//�˻�
	private static void studentSearch() {
		final int ID=1, EXIT=2;
		List<StudentModel> list=new ArrayList<StudentModel>();
		//�˻��� ������ ���� ��û(��ȭ��ȣ, �̸�)
		int id=0;
		int searchData=0;
		int no =0;
		int number =0;
		boolean flag=false;
		no = selecSearchtMenu();
		
		switch(no) {
		case ID: 
			while(true) {
				System.out.print("\t�˻��� �й� �Է� >>");
				id=scan.nextInt();
				
				if(id>=1000&&id<=9999) {
					break;
				}else {
					System.out.println("\t�ٽ� �Է����ּ���");
				}
				
			}
			searchData=id;
			number=ID;
			break;
		
		case EXIT: System.out.println("\t�˻����");flag=true;break;
		}
		if(flag) {
			return;
		}

		list=DBController.studentSearch(searchData, number);
		
		if(list.size()<=0) {
			System.out.println("\t"+searchData+"ã�� ������ �����ϴ�.");
			return;
		}
		for(StudentModel data : list) {
			System.out.println(data);
		}
		
	}
	//����(��ȭ��ȣ��/�����̸Ӹ�Ű)
	private static void studentDelete() {
		final int ID=1;
		int id=0;
		int deleteData=0;
		int number=0;
		int resultNumber=0;
		while(true) {
			System.out.print("\t������ �й� �Է� >>");
			id=scan.nextInt();
				
			if(id>=1000&&id<=9999) {
				break;
			}else {
				System.out.println("\t�ٽ� �Է����ּ���");
			}
				
		}
		deleteData=id;
		number=ID;
		
		resultNumber=DBController.studentDelete(deleteData, number);
		
		if(resultNumber!=0) {
			System.out.println("\t�й�."+id+"�л��� ������ �����Ǿ����ϴ�.");
			
		}else {
			System.out.println("\t���� �����߽��ϴ�.");
		}
		return;
			
		
		
	}
	//����
	private static void studentRewrite() {

		final int ID=1;
		List<StudentModel> list=new ArrayList<StudentModel>();
		//�˻��� ������ ���� ��û
		int id, korean, math, english=0;
		int searchData, number=0;
		int resultNumber=0;
		int total=0;
		double avr=0.0;
		String grade=null;

		 
		while(true) {
			System.out.print("\t������ �й� �Է� >>");
			id=scan.nextInt();
				
			if(id>=1000&&id<=9999) {
				break;
			}else {
				System.out.println("\t�ٽ� �Է����ּ���");
				}
				
			}
		searchData=id;
		number=ID;

		list=DBController.studentSearch(searchData, number);
		
		if(list.size()<=0) {
			System.out.println(searchData+"ã�� ������ �����ϴ�.");
			return;
		}
		StudentModel data_buffer=null;
		for(StudentModel data : list) {
			System.out.println(data);
			data_buffer=data;
		}
		System.out.print("\t"+data_buffer.getKorean()+"-> ������ ���� ���� �Է� >> ");
		korean=scan.nextInt();
		System.out.print("\t"+data_buffer.getMath()+"-> ������ ���� ���� �Է� >> ");
		math=scan.nextInt();
		System.out.print("\t"+data_buffer.getEnglish()+"-> ������ ���� ���� �Է� >> ");
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
			System.out.println("\t"+data_buffer.getName()+"���� ���� ������ �Ϸ� �߽��ϴ�.");
		}else {
			System.out.println("\t���� �����߽��ϴ�.");
		}
		return;
	}
	//����
	private static void studentSort() {
		List<StudentModel> list=new ArrayList<StudentModel>();
		int no=0;
		boolean flag=false;
				
		//1.���Ĺ��(����, ����)
		while(!flag) {
			System.out.println("\t<���� �� ����>");
			System.out.print("\t[1. ��������], [2.��������]>> ");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t���) ��Ȯ�� ���� �Է����ּ���.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t���) ��Ȯ�� ���� �Է����ּ���.");
				continue;
			}
			if(no>=1&&no<=7) {
				flag=true;
				
			}else {
				System.out.println("\t��� ) ��Ȯ�� ����(1~2)�� �Է��ϼ���.");
			}
			
		}//end of while

		//2.���� �����ֱ�(��¹��� �����´�.)
		
		list=DBController.studentSort(no);
		if(list.size()<=0) {
			System.out.println("\t������ ������ �����ϴ�.");
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
			System.out.println("\t    <�л� ���� ���� ���α׷�>");
			System.out.println("\t=============================");
			System.out.println("\t1.�Է�\t 2.��ȸ\t3.����\t4.����\n\t5.���\t 6.����\t7.����");
			System.out.println("\t=============================");
			System.out.print("\t>>");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t���) ��Ȯ�� ���� �Է����ּ���.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t���) ��Ȯ�� ���� �Է����ּ���.");
				continue;
			}
			if(no>=1&&no<=7) {
				flag=true;
				
			}else {
				System.out.println("\t��� ) ��Ȯ�� ����(1~2)�� �Է��ϼ���.");
			
			}
		}
		return no;
	}
	private static int selecSearchtMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("\t=================================================");
			System.out.println("\t�˻� ��ȣ�� �����ϼ��� [1.�й� �Է�], [2.�˻����] ");
			System.out.println("\t=================================================");
			System.out.print("\t>>");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t���) ��Ȯ�� ���� �Է����ּ���.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t���) ��Ȯ�� ���� �Է����ּ���.");
				continue;
			}
			if(no>=1&&no<=2) {
				flag=true;
				
			}else {
				System.out.println("\t��� ) ��Ȯ�� ����(1~2)�� �Է��ϼ���.");
			}
		}
		return no;
	}

}
