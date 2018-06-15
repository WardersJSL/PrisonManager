package action.search;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import model.Prisoner;
import model.PrisonerDAO;
import view.ConsoleViewer;

/**
 * 이름으로 검색 Action
 * @author JSLHRD
 *
 */
public class SearchByNameAction implements Action {

	@Override
	public void execute(Scanner sc) {
		String name = null;
		boolean x = true;
		try {
			System.out.println("\n3. 검색");
			PrisonerDAO dao = new PrisonerDAO();
			
			// 유효한 죄수번호를 입력할 때까지 계속 입력받기
			while(x) {
				System.out.print("이름 = ");
				name = sc.nextLine();
				
				if(name.equals("/cancel")) {
					System.out.println("작업을 취소합니다.\n");
					return;
				}

				if(dao.selectPrisoners(name, PrisonerDAO.FIELD_NAME) != null){
					ArrayList<Prisoner> foundPrisoners = new ArrayList<Prisoner>();
					foundPrisoners.addAll(dao.selectPrisoners(name, PrisonerDAO.FIELD_NAME));
					ConsoleViewer.showSearchResult(foundPrisoners, name, ConsoleViewer.SEARCH_MENU_NAME);
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
