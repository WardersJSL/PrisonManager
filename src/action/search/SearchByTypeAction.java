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
					
//					for(int i = 0; i < foundPrisoners.size(); i++) {
//						System.out.println();
//						System.out.println("-------------------");
//						System.out.println("죄수번호 : " + foundPrisoners.get(i).getPrinum());
//						System.out.println("이름 : " + foundPrisoners.get(i).getName());
//						System.out.println("죄목 : " + foundPrisoners.get(i).typeToString());
//						System.out.println("구분 : " + foundPrisoners.get(i).typeToString());
//						if(foundPrisoners.get(i).getPenalty()>11&&foundPrisoners.get(i).getPenalty()%12!=0) {
//							System.out.println("형량 : " + foundPrisoners.get(i).getPenalty()/12 + "년" + foundPrisoners.get(i).getPenalty()%12 + "개월");
//						}else if(foundPrisoners.get(i).getPenalty()>11&&foundPrisoners.get(i).getPenalty()%12==0) {
//							System.out.println("형량 : " + foundPrisoners.get(i).getPenalty()/12 + "년");
//						}else if(foundPrisoners.get(i).getPenalty()<=11) {
//							System.out.println("형량 : " + foundPrisoners.get(i).getPenalty() + "개월");
//						}
//						System.out.println("상벌점 : " + foundPrisoners.get(i).getScore() + "점");
//						System.out.println("징벌횟수 : " + foundPrisoners.get(i).getPunish() + "번");
//						System.out.println("-------------------");
//						System.out.println();
//						x=false;
//					}
					
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
