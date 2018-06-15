package action;

import java.util.Scanner;

import controller.PrisonController;
import model.PrisonerDAO;

public class ReleaseAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		String prisonerNo;	// 죄수번호
		
		try {
			System.out.println("\n2. 출소");
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
			
			
			
			if(dao.selectPrisonerByPrinum(prisonerNo) != null) {
				// 일치하는 죄수번호가 있으면, 삭제 작업을 수행하고 성공 여부 출력
				if(dao.deletePrisoner(prisonerNo)) {
					System.out.println();
					System.out.println("죄수번호 " + prisonerNo + "의 출소가 완료되었습니다.");
				}
				else {
					System.out.println();
					System.out.println("출소 처리에 실패하였습니다.");
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
