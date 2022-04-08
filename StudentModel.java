package com.mire.studentManagement;

import java.io.Serializable;
import java.util.Objects;




@SuppressWarnings("serial")
public class StudentModel implements Comparable<Object>, Serializable{
	final int SUBJECT=3; //과목수
	private String name, gender, birthday; // 이름, 성별, 생일
	private int id, korean, math, english, total; // 학번, 국어점수, 수학점수, 영어점수, 총점
	private double avr; // 평균
	private String grade; //등급
	
	
	
	public StudentModel(String name, String gender, String birthday, int id, int korean, int math, int english,
			int total, double avr, String grade) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.id = id;
		this.korean = korean;
		this.math = math;
		this.english = english;
		this.total = total;
		this.avr = avr;
		this.grade = grade;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StudentModel)) {
			throw new IllegalArgumentException("비교가 안되는 객체를 보냈습니다.");
		}
		StudentModel data=(StudentModel)obj;
		if(this.id==data.id) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof StudentModel)) {
			throw new IllegalArgumentException("비교가 안되는 객체를 보냈습니다.");
		}
		StudentModel data=(StudentModel)o;
		
		if(this.total>data.total) {
			return -1;
		}else if(this.total<data.total) {
			return 1;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		}else {
			return 0;
		}
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAvr() {
		return avr;
	}
	public void setAvr(double avr) {
		this.avr = avr;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		
		return "\t[\t" + name + "\t" + gender + "\t" + birthday + "\t" + id
				+ "\t" + korean + "\t" + math + "\t" + english + "\t" + total + "\t" + avr
				+ "\t" + grade + "\t]";
	}
	
	
	
	
	
	

}
