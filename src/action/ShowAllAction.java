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
			System.out.println("=======================================================================================================");
			System.out.println("죄수번호\t\t이름\t\t죄목\t\t구분\t\t형량\t\t상벌점\t\t징벌횟수");
			System.out.println("-------------------------------------------------------------------------------------------------------");
				for (int i = 0; i < prisoners.size(); i++) {
				System.out.println(prisoners.get(i).getPrinum() + "\t"+prisoners.get(i).getName() + "\t\t"
							+ prisoners.get(i).crimeToString() + "\t\t" + prisoners.get(i).typeToString() +"\t\t" +
							prisoners.get(i).getPenalty() + "개월" + "\t\t" + prisoners.get(i).getScore() + "점"
							+ "\t\t" + prisoners.get(i).getPunish()+"번");
				}
			System.out.println("=======================================================================================================");
			System.out.println();
	}

}
