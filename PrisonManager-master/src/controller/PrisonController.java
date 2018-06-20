package controller;

import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import model.Prisoner;
import view.ConsoleViewer;

//교도소 관리 클래스(back-end)
//유효성검사
public class PrisonController {
   
   // 죄수번호 유효성 검사
   public static boolean isValidPrisonerNo(String prisonerNo) {
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
   public static boolean isValidPrisoner(Prisoner prisoner) {
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
      if(prisoner.getPenalty()>=0&&prisoner.getPenalty()<=9999){
      
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
}
