package action.search;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import model.Prisoner;
import model.PrisonerDAO;
import view.ConsoleViewer;

/**
 * 죄목으로 검색하기 Action
 * @author JSLHRD
 *
 */
public class SearchByCrimeAction implements Action {
	
	PreparedStatement pstmt = null;
			ResultSet rs = null;
			
	@Override
	public void execute(Scanner sc) {
		String crime;
		boolean x = true;
		try {
			System.out.println("\n3. 검색");
			PrisonerDAO dao = new PrisonerDAO();
			
			// 유효한 죄목을 입력할 때까지 계속 입력받기
			while(x) {
				System.out.print("죄목 = ");
				crime = sc.nextLine();
				
				if(crime.equals("/cancel")) {
					System.out.println("작업을 취소합니다.\n");
					return;
				}

				if(dao.selectPrisoners(crime, PrisonerDAO.FIELD_CRIME) != null){
					ArrayList<Prisoner> foundPrisoners = new ArrayList<Prisoner>();
					foundPrisoners.addAll(dao.selectPrisoners(crime, PrisonerDAO.FIELD_CRIME));
					ConsoleViewer.showSearchResult(foundPrisoners, crime, ConsoleViewer.SEARCH_MENU_CRIME);
					x = false;
					
					if(x == true) {
						System.out.println();
						System.out.println("유효하지 않은 이름입니다. 메뉴로 돌아갑니다.\n");
						return;
					}
					
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
