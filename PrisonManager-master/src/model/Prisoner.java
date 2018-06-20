package model;

// Todo: 생성자 오버로딩 필요. 문자열(한글명)을 받아 Type 또는 Crime형으로 멤버 변수에 저장

//죄수 정보 클래스
public class Prisoner {
   
   //멤버 필드
   public enum Type { NORMAL, WATCH, DRUG, EXECUTE };
   public enum Crime { THEFT, DECEIVE, MURDER, RAPE, VIOLENCE, DRUG, EMBEZZLE };

   
   private String name;          // 이름
   //private String prisoner;       // 죄수 
   private Crime crime;             // 죄목
   private Type type;               // 죄수구분 
   private String prinum;            // 죄수 번호 
   private int penalty;          // 형량
   private int score;            // 상벌점  
   private int work;             // 노동량 
   private boolean ill = false;   // 질병 
   private int punish;            // 징계
   
   // 생성자 메서드 
   public Prisoner(String name, String prinum, Crime crime, Type type, int penalty, int score,
         int work, boolean ill, int punish) {
      super();
      this.name = name;
      this.prinum = prinum;
      this.crime = crime;
      this.type = type;
      this.penalty = penalty;
      this.score = score;
      this.work = work;
      this.ill = ill;
      this.punish = punish;
   }
   
   // 생성자 오버로딩(죄목과 죄수구분을 문자열 형태로 받는다.)
   public Prisoner(String name, String prinum, String strCrime, String strType, int penalty, int score,
	         int work, boolean ill, int punish) {
	      super();
	      this.name = name;
	      this.prinum = prinum;
	      this.crime = stringToCrime(strCrime);
	      this.type = stringToType(strType);
	      this.penalty = penalty;
	      this.score = score;
	      this.work = work;
	      this.ill = ill;
	      this.punish = punish;
	   }
   
   // 멤버 메서드
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
//   public String getPrisoner() {
//      return prisoner;
//   }
//   public void setPrisoner(String prisoner) {
//      this.prisoner = prisoner;
//   }
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
   public int getPunish() {
      return punish;
   }
   public void setPunish(int punish) {
      this.punish = punish;
   }

   // Type -> String
   public String typeToString() {
      switch(type) {
      case DRUG:
         return "마약사범";
      case EXECUTE:
         return "사형수";
      case NORMAL:
         return "일반";
      case WATCH:
         return "요시찰";
      default:
         return null;
      }
   }
   
   // Crime -> String
   public String crimeToString() {
      switch(crime) {
      case DECEIVE:
         return "사기";
      case DRUG:
         return "마약";
      case EMBEZZLE:
         return "횡령";
      case MURDER:
         return "살인";
      case RAPE:
         return "강간";
      case THEFT:
         return "절도";
      case VIOLENCE:
         return "폭행";
      default:
         return null;
      }
   }
   
   // String -> Type
   public static Type stringToType(String strType) {
	   switch(strType) {
	   case "마약사범":
		   return Type.DRUG;
	   case "일반":
		   return Type.NORMAL;
	   case "요시찰":
		   return Type.WATCH;
	   case "사형수":
		   return Type.EXECUTE;
	   default:
		   return null;
	   }
   }
   
   // String -> Crime
   public static Crime stringToCrime(String strCrime) {
	   switch(strCrime) {
	   case "절도":
		   return Crime.THEFT;
	   case "폭행":
		   return Crime.VIOLENCE;
	   case "살인":
		   return Crime.MURDER;
	   case "강간":
		   return Crime.RAPE;
	   case "횡령":
		   return Crime.EMBEZZLE;
	   case "사기":
		   return Crime.DECEIVE;
	   case "마약":
		   return Crime.DRUG;
	   default:
		   return null;
	   }
   }

	@Override
	public String toString() {
		String str = "죄수번호 : " + prinum + "\n";
		str += "이름 : " + name + "\n";
		str += "죄목 : " + crimeToString() + "\n";		
		str += "구분 : " + typeToString() + "\n";
		str += "형량 : " + (penalty / 12 == 0 ? "" : penalty / 12 + "년 ") + 
				(penalty % 12 == 0 ? "" : penalty % 12 + "개월") + "\n";
		str += "상벌점 : " + score + "\n";
		if(type != Type.EXECUTE)
			str += "노동량 : " + work + "일\n";
		str += "질병 : " + (ill ? "있음" : "없음") + "\n";
		str += "징계횟수 : " + punish + "\n";
		if(score == 80)
			str += "가석방 심사 대상자\n";
		if(score == -40)
			str += "징계 대상자\n";
		
		return str;
	}
}

