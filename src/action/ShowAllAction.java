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
			System.out.println("죄수가 없습니다.\n");
			return;
		}
			System.out.println();
			System.out.println("==================================================================================");
			System.out.println(" 죄수번호\t\t이름\t죄목\t구분\t형량\t 상벌점\t질병유무\t징벌횟수\t노동량");
			System.out.println("----------------------------------------------------------------------------------");
				for (int i = 0; i < prisoners.size(); i++) {
				System.out.print(" " + prisoners.get(i).getPrinum() + "\t"+prisoners.get(i).getName() + "\t"
							+ prisoners.get(i).crimeToString() + "\t" + prisoners.get(i).typeToString() +"\t");
				if(prisoners.get(i).getPenalty() > 11 && prisoners.get(i).getPenalty() % 12 != 0) {
					  System.out.print(prisoners.get(i).getPenalty()/12 + "Y" + prisoners.get(i).getPenalty()%12 + "M" + "\t ");
				}else if(prisoners.get(i).getPenalty() > 11 && prisoners.get(i).getPenalty() % 12 == 0) {
					  System.out.print(prisoners.get(i).getPenalty()/12 + "Y" + "\t ");
				}else if(prisoners.get(i).getPenalty() <= 11) {
					  System.out.print(prisoners.get(i).getPenalty() + "M" + "\t ");
				}
				System.out.print(prisoners.get(i).getScore() + "점" + "\t");
				if(prisoners.get(i).isIll()) System.out.print("있음");
					else System.out.print("없음"); 
					System.out.print("\t" + prisoners.get(i).getPunish()+"번" + "\t" + prisoners.get(i).getWork() + "일\n");
				}
			System.out.println("==================================================================================");
			System.out.println();
	}
				
}
