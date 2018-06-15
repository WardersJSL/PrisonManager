package action.additional;

import java.util.Scanner;

import action.Action;
import model.Prisoner;
import model.PrisonerDAO;

/**
 * 질병유무 갱신 Action
 * @author JSLHRD
 *
 */
public class IllAction implements Action {

	@Override
	public void execute(Scanner sc) {
		/*// TODO Auto-generated method stub
		
		// TODO 이 주석과 아래 코드를 제거하고 이 메서드를 구현할 것
		System.out.println("질병유무 갱신 기능은 개발중입니다.\n");*/
		
		Prisoner prisoner = null;
		String yesOrNo;
		
		
		try {
			PrisonerDAO dao = new PrisonerDAO();
			String prisonerNo;
			System.out.print("\n죄수번호 = ");
			prisonerNo = sc.nextLine();
			
			if(prisonerNo.equals("/cancel")) {
				System.out.println("작업을 취소합니다.\n");
				return;
			}
			
			prisoner = dao.selectPrisonerByPrinum(prisonerNo);
			
			while(true) {
				System.out.print("질병이 있습니까?(y/n) = ");
				yesOrNo = sc.nextLine();
				
				if(yesOrNo.equals("/cancel")) {
					System.out.println("작업을 취소합니다.\n");
					return;
				}
				
				if(yesOrNo.equalsIgnoreCase("Y")) {
					prisoner.setIll(true);
					dao.updatePrisoner(prisoner);
					break;
				}
				else if(yesOrNo.equalsIgnoreCase("N")) {
					prisoner.setIll(false);
					dao.updatePrisoner(prisoner);
					break;
				}
				else {
					System.out.println("잘못된 입력입니다. 다시 입력해 주십시오.");
				}
			}
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
			
	}

}
