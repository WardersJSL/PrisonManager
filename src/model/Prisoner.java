package model;

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
   public String typeToString(Type type) {
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
   public String crimeToString(Crime crime) {
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
   
}
