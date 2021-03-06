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
		int laborToAdd = 0;	// 부여할 노동량
		
		
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
			
			if(prisoner != null) {
				// 사형수 인지 아닌지 판단 후 노동량 갱신
				while(true) {
					if(prisoner.typeToString() == "사형수" || prisoner.typeToString() == "EXECUTE ") {
						System.out.println();
						System.out.println("사형수는 노동량을 갱신할 수 없습니다.\n");
						break;
					} else { // 노동량 주기
						while(true) {
						System.out.print("노동량 = ");
						String strLaborToAdd = sc.nextLine();
						laborToAdd = Integer.parseInt(strLaborToAdd);
						
						if(strLaborToAdd.equals("/cancel")) {
							System.out.println("작업을 취소합니다.\n");
							return;
						}
							if(prisoner.getWork()+laborToAdd < 99999) { // 노동량 업데이트
								prisoner.setWork(prisoner.getWork()+laborToAdd);
								dao.updatePrisoner(prisoner);
								System.out.println();
								System.out.println(prisoner.getName() + "(" + prisonerNo + ")에게 노동량 " + laborToAdd + "일을 주었습니다.\n");
								break;
							} else {
								System.out.println();
								System.out.println("노동량을 초과하였습니다. 다시 입력해주세요. ");
								System.out.println("현재 노동량 - " + prisoner.getWork());
								System.out.println("할 수 있는 노동량 - " + (99999-prisoner.getWork()));
								break;
							}
						}
						
						
					}
					break;
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
