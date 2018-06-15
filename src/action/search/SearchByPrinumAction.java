package action.search;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import model.Prisoner;
import model.PrisonerDAO;
import view.ConsoleViewer;

/**
 * 죄수번호로 검색 Action
 * @author JSLHRD
 *
 */
public class SearchByPrinumAction implements Action {

	@Override
	public void execute(Scanner sc) {
		String prisonerNO = null;
		boolean x = true;
		try {
			System.out.println("\n3. 검색");
			PrisonerDAO dao = new PrisonerDAO();
			
			// 유효한 죄수번호를 입력할 때까지 계속 입력받기
			while(x) {
				System.out.print("죄수번호 = ");
				prisonerNO = sc.nextLine();
				
				if(prisonerNO.equals("/cancel")) {
					System.out.println("작업을 취소합니다.\n");
					return;
				}

				if(dao.selectPrisonerByPrinum(prisonerNO)!=null){
					Prisoner foundPrisoner = dao.selectPrisonerByPrinum(prisonerNO);
					ConsoleViewer.showSearchResult(foundPrisoner);
					x = false;
					
				}else if(x == true) {
					System.out.println();
					System.out.println("유효하지 않은 죄수번호입니다. 메뉴로 돌아갑니다.");
					return;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
