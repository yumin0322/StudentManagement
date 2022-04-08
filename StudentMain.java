package com.mire.studentManagement;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StudentMain {
	public static final int SUBJECT=3; //°ú¸ñ¼ö
	public static Scanner scan=new Scanner(System.in);
	public static final int INSERT=1, SEARCH=2, DELETE=3, REWRITE=4, PRINT=5, SORT=6, EXIT=7;


	public static void main(String[] args) {
		boolean flag=false;
		while(!flag) {
			//¸Þ´º¼±ÅÃ
			int no=selectMenu();
			switch(no) {
			case INSERT:studentInsert();break; //ÀÔ·Â
			case SEARCH:studentSearch();break; //°Ë»ö
			case DELETE:studentDelete();break; //»èÁ¦
			case REWRITE:studentRewrite();break; //¼öÁ¤
			case PRINT:studentPrint();break; //Ãâ·Â
			case SORT:studentSort();break; //Á¤·Ä
			case EXIT:flag=true;break; //Á¾·á
			}
			 
			
		}

	}
	
	//ÀÔ·Â(ÀÌ¸§, ¼ºº°, »ýÀÏ, ÇÐ¹ø, ±¹¾îÁ¡¼ö, ¼öÇÐÁ¡¼ö, ¿µ¾îÁ¡¼ö)
	private static void studentInsert() {

		String name=null;
		String gender=null;
		String birthday=null;
		int id, korean, math, english, total=0;
		double avr=0.0;
		String grade=null;

		//ÀÌ¸§
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\tÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä(È«±æµ¿) >>");
			name=scan.nextLine();
			if(patternCheck(name, 1)){
				break;
			}else {
				System.out.println("\t´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä");
			}
		}
		//¼ºº°
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t¼ºº°À» ÀÔ·ÂÇÏ¼¼¿ä(³²ÀÚ, ¿©ÀÚ) >>");
			gender=scan.nextLine();
			if(gender.equals("³²ÀÚ")||gender.equals("¿©ÀÚ")) {
				break;
			}else {
				System.out.println("\t´Ù½Ã ÀÔ·ÂÇÏ¼¼¿ä.");
			}
		}
		//»ýÀÏ
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t»ý³â¿ùÀÏÀ» ÀÔ·ÂÇÏ¼¼¿ä(1995-08-29) >>");
			birthday=scan.nextLine();
			if(patternCheck(birthday,2)) {
				break;
			}else {
				System.out.println("\t´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä");
			}
		}
		//ÇÐ¹ø
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\tÇÐ¹øÀ» ÀÔ·ÂÇÏ¼¼¿ä(1000~9999) >>");
			id=scan.nextInt();
			if(id>=1000 && id<=9999) {
				break;
			}else {
				System.out.println("\t(1000~9999)»çÀÌ·Î ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
		}
		//±¹¾î,¼öÇÐ,¿µ¾îÁ¡¼ö
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t±¹¾î Á¡¼ö¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä(0~100Á¡) >>");
			korean=scan.nextInt();
			if(korean>=0 && korean<=100) {
				break;
			}else {
				System.out.println("\t(0~100Á¡)»çÀÌ·Î ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
		}
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t¼öÇÐ Á¡¼ö¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä(0~100Á¡) >>");
			math=scan.nextInt();
			if(math>=0 && math<=100) {
				break;
			}else {
				System.out.println("\t(0~100Á¡)»çÀÌ·Î ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
		}
		while(true) {
			System.out.println("\t______________________________");
			System.out.print("\t¿µ¾î Á¡¼ö¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä(0~100Á¡) >>");
			english=scan.nextInt();
			if(english>=0 && english<=100) {
				break;
			}else {
				System.out.println("\t(0~100Á¡)»çÀÌ·Î ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
		}
		total=korean+math+english;
		avr=total/(double)SUBJECT;
		grade=checkGrade(avr);
		StudentModel studentModel=new StudentModel(name,gender,birthday,id,korean,math,english,total,avr,grade);

		
		int returnValue=DBController.studentInsert(studentModel);
		if(returnValue!=0) {
			System.out.println("\t______________________________");
			System.out.println("\t"+studentModel.getName()+"´Ô ÀÔ·Â ¿Ï·áÇß½À´Ï´Ù");
		}else {
			System.out.println("\tÀÔ·Â ½ÇÆÐÇß½À´Ï´Ù.");  
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
	//ÆÐÅÏ¸ÅÄ¡ Ã³¸®ÇÔ¼ö(ÀÌ¸§, »ý³â¿ùÀÏ)
	private static boolean patternCheck(String patternData, int patternType) {
		final int NAME=1, BIRTHDAY=2;
		String filter=null;
		switch(patternType) {
		case NAME : filter="^[°¡-ÆR]{2,5}$";break;
		case BIRTHDAY : filter="^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";break;
		}
		Pattern pattern = Pattern.compile(filter);
	    Matcher matcher = pattern.matcher(patternData);
	    
	    return matcher.matches();
		
	}
	//Ãâ·Â
	private static void studentPrint() {
		List<StudentModel> list=new ArrayList<StudentModel>();
		list=DBController.studentSelect();
		if(list.size()<=0) {
			return;
		}
		System.out.println("\t[\tÀÌ¸§\t¼ºº°\t   »ýÀÏ   \tÇÐ¹ø\t±¹¾îÁ¡¼ö\t¼öÇÐÁ¡¼ö\t¿µ¾îÁ¡¼ö\tÃÑÁ¡\tÆò±Õ\tµî±Þ\t]");
		System.out.println();
		for(StudentModel data : list) {
			System.out.println(data.toString());
		}
	}
	//°Ë»ö
	private static void studentSearch() {
		final int ID=1, EXIT=2;
		List<StudentModel> list=new ArrayList<StudentModel>();
		//°Ë»öÇÒ ³»¿ëÀ» ¼±ÅÃ ¿äÃ»(ÀüÈ­¹øÈ£, ÀÌ¸§)
		int id=0;
		int searchData=0;
		int no =0;
		int number =0;
		boolean flag=false;
		no = selecSearchtMenu();
		
		switch(no) {
		case ID: 
			while(true) {
				System.out.print("\t°Ë»öÇÒ ÇÐ¹ø ÀÔ·Â >>");
				id=scan.nextInt();
				
				if(id>=1000&&id<=9999) {
					break;
				}else {
					System.out.println("\t´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä");
				}
				
			}
			searchData=id;
			number=ID;
			break;
		
		case EXIT: System.out.println("\t°Ë»öÃë¼Ò");flag=true;break;
		}
		if(flag) {
			return;
		}

		list=DBController.studentSearch(searchData, number);
		
		if(list.size()<=0) {
			System.out.println("\t"+searchData+"Ã£´Â Á¤º¸°¡ ¾ø½À´Ï´Ù.");
			return;
		}
		for(StudentModel data : list) {
			System.out.println(data);
		}
		
	}
	//»èÁ¦(ÀüÈ­¹øÈ£·Î/ÇÁ¶óÀÌ¸Ó¸®Å°)
	private static void studentDelete() {
		final int ID=1;
		int id=0;
		int deleteData=0;
		int number=0;
		int resultNumber=0;
		while(true) {
			System.out.print("\t»èÁ¦ÇÒ ÇÐ¹ø ÀÔ·Â >>");
			id=scan.nextInt();
				
			if(id>=1000&&id<=9999) {
				break;
			}else {
				System.out.println("\t´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä");
			}
				
		}
		deleteData=id;
		number=ID;
		
		resultNumber=DBController.studentDelete(deleteData, number);
		
		if(resultNumber!=0) {
			System.out.println("\tÇÐ¹ø."+id+"ÇÐ»ýÀÇ Á¤º¸°¡ »èÁ¦µÇ¾ú½À´Ï´Ù.");
			
		}else {
			System.out.println("\t»èÁ¦ ½ÇÆÐÇß½À´Ï´Ù.");
		}
		return;
			
		
		
	}
	//¼öÁ¤
	private static void studentRewrite() {

		final int ID=1;
		List<StudentModel> list=new ArrayList<StudentModel>();
		//°Ë»öÇÒ ³»¿ëÀ» ¼±ÅÃ ¿äÃ»
		int id, korean, math, english=0;
		int searchData, number=0;
		int resultNumber=0;
		int total=0;
		double avr=0.0;
		String grade=null;

		 
		while(true) {
			System.out.print("\t¼öÁ¤ÇÒ ÇÐ¹ø ÀÔ·Â >>");
			id=scan.nextInt();
				
			if(id>=1000&&id<=9999) {
				break;
			}else {
				System.out.println("\t´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä");
				}
				
			}
		searchData=id;
		number=ID;

		list=DBController.studentSearch(searchData, number);
		
		if(list.size()<=0) {
			System.out.println(searchData+"Ã£´Â Á¤º¸°¡ ¾ø½À´Ï´Ù.");
			return;
		}
		StudentModel data_buffer=null;
		for(StudentModel data : list) {
			System.out.println(data);
			data_buffer=data;
		}
		System.out.print("\t"+data_buffer.getKorean()+"-> ¼öÁ¤ÇÒ ±¹¾î Á¡¼ö ÀÔ·Â >> ");
		korean=scan.nextInt();
		System.out.print("\t"+data_buffer.getMath()+"-> ¼öÁ¤ÇÒ ¼öÇÐ Á¡¼ö ÀÔ·Â >> ");
		math=scan.nextInt();
		System.out.print("\t"+data_buffer.getEnglish()+"-> ¼öÁ¤ÇÒ ¿µ¾î Á¡¼ö ÀÔ·Â >> ");
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
			System.out.println("\t"+data_buffer.getName()+"´ÔÀÇ ¼ºÀû ¼öÁ¤À» ¿Ï·á Çß½À´Ï´Ù.");
		}else {
			System.out.println("\t¼öÁ¤ ½ÇÆÐÇß½À´Ï´Ù.");
		}
		return;
	}
	//Á¤·Ä
	private static void studentSort() {
		List<StudentModel> list=new ArrayList<StudentModel>();
		int no=0;
		boolean flag=false;
				
		//1.Á¤·Ä¹æ½Ä(¿À¸§, ³»¸²)
		while(!flag) {
			System.out.println("\t<¼ºÀû ¼ø Á´·Ä>");
			System.out.print("\t[1. ¿À¸§Â÷¼ø], [2.³»¸²Â÷¼ø]>> ");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t°æ°í) Á¤È®ÇÑ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t°æ°í) Á¤È®ÇÑ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			if(no>=1&&no<=7) {
				flag=true;
				
			}else {
				System.out.println("\t°æ°í ) Á¤È®ÇÑ ¼ýÀÚ(1~2)À» ÀÔ·ÂÇÏ¼¼¿ä.");
			}
			
		}//end of while

		//2.Á¤·Ä º¸¿©ÁÖ±â(Ãâ·Â¹°À» °¡Á®¿Â´Ù.)
		
		list=DBController.studentSort(no);
		if(list.size()<=0) {
			System.out.println("\tÁ¤·ÄÇÒ ³»¿ëÀÌ ¾ø½À´Ï´Ù.");
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
			System.out.println("\t    <ÇÐ»ý ¼ºÀû °ü¸® ÇÁ·Î±×·¥>");
			System.out.println("\t=============================");
			System.out.println("\t1.ÀÔ·Â\t 2.Á¶È¸\t3.»èÁ¦\t4.¼öÁ¤\n\t5.Ãâ·Â\t 6.Á¤·Ä\t7.Á¾·á");
			System.out.println("\t=============================");
			System.out.print("\t>>");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t°æ°í) Á¤È®ÇÑ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t°æ°í) Á¤È®ÇÑ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			if(no>=1&&no<=7) {
				flag=true;
				
			}else {
				System.out.println("\t°æ°í ) Á¤È®ÇÑ ¼ýÀÚ(1~2)À» ÀÔ·ÂÇÏ¼¼¿ä.");
			
			}
		}
		return no;
	}
	private static int selecSearchtMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("\t=================================================");
			System.out.println("\t°Ë»ö ¹øÈ£¸¦ ¼±ÅÃÇÏ¼¼¿ä [1.ÇÐ¹ø ÀÔ·Â], [2.°Ë»öÃë¼Ò] ");
			System.out.println("\t=================================================");
			System.out.print("\t>>");
			
			try {
				no=Integer.parseInt(scan.nextLine());
				
			}catch(InputMismatchException e) {
				System.out.println("\t°æ°í) Á¤È®ÇÑ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			catch(Exception e) {
				System.out.println("\t°æ°í) Á¤È®ÇÑ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			if(no>=1&&no<=2) {
				flag=true;
				
			}else {
				System.out.println("\t°æ°í ) Á¤È®ÇÑ ¼ýÀÚ(1~2)À» ÀÔ·ÂÇÏ¼¼¿ä.");
			}
		}
		return no;
	}

}
