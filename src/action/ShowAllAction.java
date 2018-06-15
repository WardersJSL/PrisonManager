package action;

import java.util.ArrayList;
import java.util.Scanner;

import model.Prisoner;
import model.PrisonerDAO;

public class ShowAllAction implements Action {
	
	@Override
	public void execute(Scanner sc) {
		PrisonerDAO dao = new PrisonerDAO();
		
		ArrayList<Prisoner> prisoners = dao.selectAllPrisoners();
		
		
		
		if(prisoners.size() < 1) {	// 죄수가 없으면 죄수 없음 메시지 출력
			System.out.println();
			System.out.println("죄수가 없습니다.");
			return;
		}
			System.out.println();
			
			
			
			System.out.println("===============================================================================");
			System.out.println(" 죄수번호\t\t이름\t죄목\t구분\t형량\t상벌점\t노동량\t질병유무\t징벌횟수");
			System.out.println("-------------------------------------------------------------------------------");
				for (int i = 0; i < prisoners.size(); i++) {
				System.out.print(" " + prisoners.get(i).getPrinum() + "\t"+prisoners.get(i).getName() + "\t"
							+ prisoners.get(i).crimeToString() + "\t" + prisoners.get(i).typeToString() +"\t");
				if(prisoners.get(i).getPenalty()>11&&prisoners.get(i).getPenalty()%12!=0) {
					                  System.out.print("형량 : " + prisoners.get(i).getPenalty()/12 + "년" + prisoners.get(i).getPenalty()%12 + "개월"+ "\t" );
					               }else if(prisoners.get(i).getPenalty()>11&&prisoners.get(i).getPenalty()%12==0) {
					                  System.out.print("형량 : " + prisoners.get(i).getPenalty()/12 + "년"+ "\t" );
					               }else if(prisoners.get(i).getPenalty()<=11) {
					                  System.out.print("형량 : " + prisoners.get(i).getPenalty() + "개월"+ "\t" );
					               }
				System.out.println(prisoners.get(i).getWork() + "시간" + "\t" + prisoners.get(i).isIll()
							+ "\t" + prisoners.get(i).getPunish()+"번");
				}
			System.out.println("===============================================================================");
			System.out.println();
	}

}
