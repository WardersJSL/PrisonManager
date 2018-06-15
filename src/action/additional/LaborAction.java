package action.additional;

import java.util.Scanner;

import action.Action;
import model.Prisoner;
import model.PrisonerDAO;

/**
 * 노동량 갱신 Action
 * @author JSLHRD
 *
 */
public class LaborAction implements Action {

	@Override
	public void execute(Scanner sc) {
		Prisoner prisoner = null;
		int LaborToAdd = 0;	// 부여할 노동량
		
		
		try {
			
			PrisonerDAO dao = new PrisonerDAO();
			
			String prisonerNo;
			System.out.print("\n죄수번호 = ");
			prisonerNo = sc.nextLine();
			prisoner = dao.selectPrisonerByPrinum(prisonerNo);
			
			if(prisoner != null) {
				// 사형수 인지 아닌지 판단 후 노동량 갱신
				while(true) {
					if(prisoner.typeToString() == "사형수" || prisoner.typeToString() == "EXECUTE ") {
						System.out.println();
						System.out.println("사형수는 노동량을 갱신할 수 없습니다. ");
						break;
					} else { // 노동량 주기
						while(true) {
						System.out.print("노동량 = ");
						LaborToAdd = Integer.parseInt(sc.nextLine());
						
							if(LaborToAdd >= 1 && LaborToAdd <= 99999) {
								prisoner.setWork(LaborToAdd);
								dao.updatePrisoner(prisoner);
							} else {
								System.out.println();
								System.out.println("노동량은 1~99999시간 입니다. 다시 입력해주세요. ");
								break;
							}
						}
						// 노동량 업데이트
						System.out.println();
						System.out.println(prisoner.getName() + "(" + prisonerNo + ")에게 노동량 " + LaborToAdd + "시간을 주었습니다.");
						
					}
				}
				
				
			} else {
				System.out.println();
				System.out.println("죄수번호가 존재하지 않습니다.\n");
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
