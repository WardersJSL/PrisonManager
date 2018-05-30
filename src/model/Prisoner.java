package model;

//죄수 정보 클래스
public class Prisoner {
	
	//멤버 필드
	public enum Type { NORMAL, WATCH, DRUG, EXECUTE };
	public enum Crime { THEFT, DECEIVE, MURDER, RAPE, VIOLENCE, DRUG, EMBEZZLE };

	
	private String name;		 //이름
	private String prisoner; 	// 죄수 
	private Crime crime; 	    // 죄목
	private Type type;		   // 죄수구분 
	private String prinum; 	  //죄수 번호 
	private int penalty;	 // 형량
	private int score;		// 상벌점  
	private int work; 		// 노동량 
	boolean ill = false;	//질병 

	
	// 생성자 메서드 
	public Prisoner(String name, String prisoner, Crime crime, Type type, String prinum, int penalty, int score,
			int work, boolean ill) {
		super();
		this.name = name;
		this.prisoner = prisoner;
		this.crime = crime;
		this.type = type;
		this.prinum = prinum;
		this.penalty = penalty;
		this.score = score;
		this.work = work;
		this.ill = ill;
	}
	
	// 멤버 메서드
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrisoner() {
		return prisoner;
	}
	public void setPrisoner(String prisoner) {
		this.prisoner = prisoner;
	}
	public Crime getCrime() {
		return crime;
	}
	public void setCrime(Crime crime) {
		this.crime = crime;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getPrinum() {
		return prinum;
	}
	public void setPrinum(String prinum) {
		this.prinum = prinum;
	}
	public int getPenalty() {
		return penalty;
	}
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getWork() {
		return work;
	}
	public void setWork(int work) {
		this.work = work;
	}
	public boolean isIll() {
		return ill;
	}
	public void setIll(boolean ill) {
		this.ill = ill;
	}
	
	
	
}
