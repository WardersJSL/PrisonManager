package action.additional;

import java.util.Scanner;

import action.Action;
import model.PrisonerDAO;

/**
 * 징계 Action
 * @author JSLHRD
 *
 */
public class PunishAction implements Action {

	@Override
	public void execute(Scanner sc) {
String prisonerNo;
	
		
		try {
			PrisonerDAO dao = new PrisonerDAO();
		
			
			
		while(true) {
			System.out.print("죄수번호 = ");
			prisonerNo = sc.nextLine();
			if(prisonerNo.equals("/cancel")) {
				System.out.println("작업을 취소합니다.\n");
				return;
			}
			
				if(dao.selectPrisonerByPrinum(prisonerNo) != null) {
					if(dao.initScore(prisonerNo)) {
						System.out.println();
						System.out.println("징계 받음\n");
						break;
					} else {
						System.out.println();
						System.out.println("징계 점수가 부족합니다.\n");
						break;
					}
					
				} else {
					System.out.println();
					System.out.println("죄수번호가 존재하지 않습니다.\n");
					break;
				}
			
	}
		}catch(Exception ex) {
					ex.printStackTrace();
				}
	}

}
