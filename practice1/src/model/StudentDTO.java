package model;

public class StudentDTO {
	
	private int id;
	private String name;
	private int kor;
	private int eng;
	private int math;
	
	public StudentDTO() {
		
	}
	
	public StudentDTO(int id, String name, int kor, int eng, int math) {
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getKor() {
		return kor;
	}


	public void setKor(int kor) {
		this.kor = kor;
	}


	public int getEng() {
		return eng;
	}


	public void setEng(int eng) {
		this.eng = eng;
	}


	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
	}
	
	public int getSum() {
		return this.kor + this.eng + this.math;
		
	}
	
	public double getAvg() {
		return (this.kor + this.eng + this.math)/3.0f;
	}
	

}
