package action.search;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import model.Prisoner;
import model.PrisonerDAO;

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

				if(dao.selectPrisoners(name, PrisonerDAO.FIELD_NAME) != null){
					ArrayList<Prisoner> foundPrisoners = new ArrayList<Prisoner>();
					foundPrisoners.addAll(dao.selectPrisoners(name, PrisonerDAO.FIELD_NAME));
					for(int i = 0; i < foundPrisoners.size(); i++) {
						System.out.println();
						System.out.println("-------------------");
						System.out.println("죄수번호 : " + foundPrisoners.get(i).getPrinum());
						System.out.println("이름 : " + foundPrisoners.get(i).getName());
						System.out.println("죄목 : " + foundPrisoners.get(i).crimeToString());
						System.out.println("구분 : " + foundPrisoners.get(i).typeToString());
						System.out.println("형량 : " + foundPrisoners.get(i).getPenalty() + "개월");
						System.out.println("상벌점 : " + foundPrisoners.get(i).getScore() + "점");
						System.out.println("징벌횟수 : " + foundPrisoners.get(i).getPunish() + "번");
						System.out.println("-------------------");
						System.out.println();
						/*if(i < foundPrisoners.size() - 1) {
							System.out.println("================");
						}*/
						x=false;
					}
				}else {
					System.out.println();
					System.out.println("유효하지 않은 이름입니다. 다시 입력해 주세요.");
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
