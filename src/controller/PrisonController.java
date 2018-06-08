package controller;

import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import model.Prisoner;
import view.ConsoleViewer;

//교도소 관리 클래스(back-end)
//유효성검사
public class PrisonController {
   private ArrayList<Prisoner> prisoners = new ArrayList<Prisoner>();      // 죄수들
   
   // 생성자
   public PrisonController(){
      
   }
   
   // 죄수번호 유효성 검사
   public boolean isValidPrisonerNo(String prisonerNo) {
      boolean x = false;
      if(prisonerNo.charAt(0)=='0') {
         if(prisonerNo.charAt(1)=='1'||prisonerNo.charAt(1)=='2'){
            if(prisonerNo.charAt(2)=='가'||prisonerNo.charAt(2)=='나'||prisonerNo.charAt(2)=='다'){
               if(prisonerNo.charAt(3)>='1'&&prisonerNo.charAt(3)<='5'){
                  for(int i = 4; i < 8; i++) {
                     if(prisonerNo.charAt(i)>='0'&&prisonerNo.charAt(i)<='9'){
                        x=true;
                     }
                  }
                  
               }
            }
         }
      }
      return x;
   }
   
   // 죄수 정보 유효성 검사
   public boolean isValidPrisoner(Prisoner prisoner) {
      boolean x=true;
      if(!isValidPrisonerNo(prisoner.getPrinum())){
         x=false;
      }
      
      if(prisoner.getName().length()>=2&&prisoner.getName().length()<=20) {
         
      }else {
         x=false;
      }
      
      switch(prisoner.getCrime()) {
      case THEFT :
      case DECEIVE :
      case MURDER :
      case RAPE :
      case VIOLENCE :
      case DRUG :
      case EMBEZZLE :break;
      default : x = false;
      }
      switch(prisoner.getType()) {
      case NORMAL :
      case WATCH : 
      case DRUG :
      case EXECUTE :break;
      default : x = false;
      }
      if(prisoner.getPenalty()>=1&&prisoner.getPenalty()<=9999){
      
      }else {
         x=false;
      }

      if(prisoner.getScore()>=-40&&prisoner.getScore()<=80) {
      
      }else {
         x=false;
      }
      if(prisoner.getWork()>=0&&prisoner.getWork()<=99999) {
         
      }else {
         x=false;
      }
      if(prisoner.isIll()==true||prisoner.isIll()==false) {
         
      }else {
         x=false;
      }
      if(prisoner.getPunish()>=0&&prisoner.getPunish()<=999) {
         
      }
      return x;
   }
   
   // 죄수번호 중복 검사
   //   주어진 죄수번호와 이 클래스의 InsiPrisoners 리스트에 저장된 죄수들의 죄수번호 중 일치하는 값이 있는지 검사
   public boolean isExistPrisonerNo(String prisonerNo) {
      boolean x = false;
      for(int i = 0; i < prisoners.size(); i++) {
         if(prisonerNo.equals(prisoners.get(i).getPrinum())) {
            x = true;
            break;
            
         }
      }
      return x;
   }
   
   public boolean AddPrisoner(Prisoner prisoner) {         //죄수등록
      boolean x = false;
      if(isValidPrisoner(prisoner)&&!isExistPrisonerNo(prisoner.getPrinum())){
         x = prisoners.add(prisoner);
      }
      return x;
   }
   
   public boolean DeletePrisoner(String prinum) {      //죄수삭제
      boolean check = false;
      
      for(int i = 0; i < prisoners.size();i++) {
         if(prinum.equals(prisoners.get(i).getPrinum())) {
            check =   prisoners.remove(prisoners.get(i));
            break;
         }
      }
   
      return check;
   }
   
   // 이름으로 검색하고, 이름이 일치하는 죄수 정보를 전부 추가해서 리스트로 반환
   public ArrayList<Prisoner> FindPrisoner(String name) {      //죄수검색
      ArrayList<Prisoner> foundPrisoners = new ArrayList<Prisoner>();
      
      for(int i = 0; i < prisoners.size(); i++) {
       if(prisoners.get(i).getName().equals(name)) {
                    
          foundPrisoners.add(prisoners.get(i));
       }   
      }
      
      return foundPrisoners;
   }
   
   public ArrayList<Prisoner> ShowAll() {            //죄수전체출력
      return prisoners;
   }
   
   /**
    * 상벌점 부여 처리 내부동작
    * @param prisonerNo 죄수번호
    * @param scoreToAdd 더할 상벌점(음수가 되면 벌점 부여)
    * @return 성공여부
    */
   public boolean updateScore(String prisonerNo, int scoreToAdd) {
      // 상벌점을 부여할 경우 범위를 벗어나는지 먼저 검사   
      boolean x = false;
      for(int i = 0; i < prisoners.size(); i++) {
         if(prisonerNo.equals(prisoners.get(i).getPrinum())) {
            if(prisoners.get(i).getScore()+scoreToAdd<= 80 &&
               prisoners.get(i).getScore()+scoreToAdd>=-40){
            	prisoners.get(i).setScore(prisoners.get(i).getScore()+scoreToAdd);
               x = true;
            }
         }
      }
      return x;
   }
   
   /**
    * 징계 처리 내부동작
    * @param prisonerNo 죄수번호
    * @return 성공여부
    */
   public boolean initScore(String prisonerNo) {
      // 벌점이 -40점인 죄수만 징계 가능
      // 징계를 받은 죄수의 벌점은 0으로 초기화
      boolean x = false;
      
      for(int i = 0; i < prisoners.size(); i++) {
         if(prisonerNo.equals(prisoners.get(i).getPrinum())) {
            if(prisoners.get(i).getScore()<=-40) {
               prisoners.get(i).setScore(0);
               prisoners.get(i).setPunish(prisoners.get(i).getPunish()+1);
               x=true;
            }
         }
      }
      
      return x;
   }
}
