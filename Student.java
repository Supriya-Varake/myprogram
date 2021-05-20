package com.studentsystem;

public class Student {
	private int srno;
	private String sname;
	private  String Saddress;
	public Student(int srno,String sname,String saddress)
	{
		super();
		this.srno = srno;
		this.sname = sname;
		Saddress = saddress;
		
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddress() {
		return Saddress;
	}
	public void setSaddress(String saddress) {
		Saddress = saddress;
	}

}
