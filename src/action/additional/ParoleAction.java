package action.additional;

import java.util.Scanner;

import action.Action;
import controller.PrisonController;
import model.Prisoner;
import model.PrisonerDAO;

/**
 * 가석방 Action
 * @author JSLHRD
 *
 */
public class ParoleAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		String prisonerNo;	// 죄수번호
		Prisoner prisoner = null;	// 죄수객체
		
		try {
			System.out.println("\n3. 가석방");
			PrisonerDAO dao = new PrisonerDAO();
			
			// 유효한 죄수번호를 입력할 때까지 계속 입력받기
			while(true) {
				System.out.print("죄수번호 = ");
				prisonerNo = sc.nextLine();
				
				if(prisonerNo.equals("/cancel")) {
					System.out.println("작업을 취소합니다.\n");
					return;
				}
				
				if(!PrisonController.isValidPrisonerNo(prisonerNo)) {
					System.out.println();
					System.out.println("유효하지 않은 죄수번호입니다. 다시 입력해 주세요.");
				}
				else
					break;
			}
			
			prisoner = dao.selectPrisonerByPrinum(prisonerNo);
			
			// 일치하는 죄수번호가 있을 경우
			if(prisoner != null) {
				
				// 가석방 심사 대상인지 검사
				if(prisoner.getScore() != 80) {
					System.out.println("가석방 심사 대상이 아닙니다. 작업을 취소합니다.\n");
					return;
				}
				
				// 모든 조건을 충족하면, 삭제 작업을 수행하고 성공 여부 출력
				if(dao.deletePrisoner(prisonerNo)) {
					System.out.println();
					System.out.println("죄수번호 " + prisonerNo + "의 가석방이 완료되었습니다.");
				}
				else {
					System.out.println();
					System.out.println("가석방 처리에 실패하였습니다.");
				}
			}
			else {
				System.out.println();
				System.out.println("죄수번호가 일치하는 죄수가 없습니다.");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
