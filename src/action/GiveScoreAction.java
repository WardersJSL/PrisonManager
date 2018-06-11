package action;

import java.util.Scanner;

import model.Prisoner;
import model.PrisonerDAO;

public class GiveScoreAction implements Action {

	@Override
	public void execute(Scanner sc) {
		Prisoner prisoner = null;
		int subMenu = 0;	// 상점인지 벌점인지를 결정하는 변수(1: 상점, 2: 벌점)
		int maxGiving = 0;	// 부여 가능한 상점 또는 벌점의 상한
		int scoreToAdd = 0;	// 부여할 상벌점
		
		try {
			PrisonerDAO dao = new PrisonerDAO();
			
			String prisonerNo;
			System.out.print("\n죄수번호 = ");
			prisonerNo = sc.nextLine();
			prisoner = dao.selectPrisonerByPrinum(prisonerNo);
			
			if(prisoner != null) {
				
				while(true) {
					try {
						System.out.println("1. 상점\t2. 벌점");
						System.out.print("번호를 입력하세요 = ");
						subMenu = Integer.parseInt(sc.nextLine());
						if(subMenu != 1 && subMenu != 2)
							System.out.println("입력 범위를 벗어났습니다.");
						else
							break;
					} catch(NumberFormatException ex) {
						System.out.println("숫자를 입력해 주세요.");						
					}
				}
				
				// 상점 부여 모드일 때의 입력 상한은 80 - (현재 상벌점)
				// 벌점 부여 모드일 때의 입력 상한은 (현재 상벌점) + 40
				maxGiving = subMenu == 1 ? 80 - prisoner.getScore() : prisoner.getScore() + 40;
				
				while(true) {
					try {
						System.out.print(subMenu == 1 ? "상점 부여(1~" + maxGiving + ") = " : "벌점 부여(1~" + maxGiving + ") = ");
						scoreToAdd = Integer.parseInt(sc.nextLine());
						if(scoreToAdd < 1 || scoreToAdd > maxGiving) {
							System.out.println("범위를 벗어났습니다.");
						}
						else
							break;
					} catch(NumberFormatException ex) {
						System.out.println("숫자를 입력해 주세요.");
					}
				}
				
				// 상벌점 갱신
				prisoner.setScore(prisoner.getScore() + scoreToAdd * (subMenu == 1 ? 1 : -1));
				
				// 상벌점 부여 처리
				if(dao.updatePrisoner(prisoner)) {
					System.out.println();
					System.out.println(prisoner.getName() + "(" + prisonerNo + ")에게 " + (subMenu == 1 ? "상점 " : "벌점 ") + scoreToAdd + "점을 부여하였습니다.");
					
					// 가석방 대상 또는 징계 대상이 되었을 경우 이를 알림
					if(prisoner.getScore() == 80)
						System.out.println("가석방 심사 대상이 되었습니다.");
					else if(prisoner.getScore() == -40)
						System.out.println("징계 대상이 되었습니다.");
					
					System.out.println();
				}
			} else {
				System.out.println();
				System.out.println("죄수번호가 존재하지 않습니다.\n");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
