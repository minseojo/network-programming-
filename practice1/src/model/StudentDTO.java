package model;

public class StudentDTO {
	
	private int id;
	private String name;
	private int kor;
	private int eng;
	private int math;

	/** 해당 생성자는 사용하지 않음. 따라서 기본 생성자는 지우거나 private으로 무차별한 생성자 사용 방지하기**/
	public StudentDTO() {
		
	}

	/** 생성자 파라미터가 4개 이상이라면 builder 패턴도 고려하면 좋음
	 일반적으로 파라미터가 4~5개 이상이라면 builder 이용 - 스프링부트의 lombok에서 이를 지원해줌. 자바는 커스텀 구현
	 */

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

	/** Setter는 사용하지 않는게 기본 컨벤션
	 setter로 객체의 값을 어느 위치에서든 변경할 수 있음. (개발자가 많은 경우 실수 방지)
	 즉, 객체 생성은 생성자에서만 생성하고, 생성된 객체를 이용하기

	 세터를 안쓰고 이렇게 점수를 업데이트 해야하는데, 세터를 어떻게 안쓰냐? setName 이라는 메서드 대신 updateName 메서드로 메서드 명 변경하기
	 */
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
