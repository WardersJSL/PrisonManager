package action.search;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import model.Prisoner;
import model.PrisonerDAO;
import view.ConsoleViewer;

/**
 * 죄수구분으로 검색하기
 * @author JSLHRD
 *
 */
public class SearchByTypeAction implements Action {

	@Override
	public void execute(Scanner sc) {
		String type;
		boolean x = true;
		try {
			System.out.println("\n3. 검색");
			PrisonerDAO dao = new PrisonerDAO();
			
			// 
			while(x) {
				System.out.print("구분 = ");
				type = sc.nextLine();
				
				if(type.equals("/cancel")) {
					System.out.println("작업을 취소합니다.\n");
					return;
				}

				if(dao.selectPrisoners(type, PrisonerDAO.FIELD_TYPE) != null){
					ArrayList<Prisoner> foundPrisoners = new ArrayList<Prisoner>();
					foundPrisoners.addAll(dao.selectPrisoners(type, PrisonerDAO.FIELD_TYPE));
					ConsoleViewer.showSearchResult(foundPrisoners, type, ConsoleViewer.SEARCH_MENU_TYPE);
					x = false;
					
					if(x == true) {
						System.out.println();
						System.out.println("유효하지 않은 이름입니다. 메뉴로 돌아갑니다.");
						return;
					}
					
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
