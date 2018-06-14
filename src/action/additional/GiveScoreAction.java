package action.additional;

import java.util.Scanner;

import action.Action;
import model.Prisoner;
import model.PrisonerDAO;

/**
 * 상벌점 부여 Action
 * @author JSLHRD
 *
 */
public class GiveScoreAction implements Action {

	@Override
	public void execute(Scanner sc) {
		Prisoner prisoner = null;
		int subMenu = 0;	// 상점인지 벌점인지를 결정하는 변수(1: 상점, 2: 벌점)
		int maxGiving = 0;	// 부여 가능한 상점 또는 벌점의 상한
		int scoreToAdd = 0;	// 부여할 상벌점
		String yesNo;		// yes or no
		
		try {
			PrisonerDAO dao = new PrisonerDAO();
			
			String prisonerNo;
			System.out.print("\n죄수번호 = ");
			prisonerNo = sc.nextLine();
			
			if(prisonerNo.equals("/cancel")) {
				System.out.println("작업을 종료합니다.\n");
				return;
			}
			
			prisoner = dao.selectPrisonerByPrinum(prisonerNo);
			
			if(prisoner != null) {
				
				// 상벌점의 상한 또는 하한일 경우 이를 알리고 상황에 맞게 처리
				if(prisoner.getScore() == 80) {
					System.out.println("현재 상점이 80으로, 벌점만 부여할 수 있습니다.");
					while(true) {
						System.out.print("벌점을 부여하시겠습니까(y/n) = ");
						yesNo = sc.nextLine();
						
						if(yesNo.equals("/cancel")) {
							System.out.println("작업을 종료합니다.");
							return;
						}
						
						System.in.read();
						System.in.read();
						if(yesNo.equalsIgnoreCase("y")) {
							subMenu = 2;
							break;
						}
						else if(yesNo.equalsIgnoreCase("n")) {
							System.out.println("상벌점 부여 작업을 종료합니다.\n");
							return;
						}
						else {
							System.out.println("잘못된 입력입니다. 다시 입력해 주십시오.");
						}
					}
				}
				else if(prisoner.getScore() == -40) {
					System.out.println("현재 벌점이 40으로, 상점만 부여할 수 있습니다.");
					while(true) {
						System.out.print("상점을 부여하시겠습니까(y/n) = ");
						yesNo = sc.nextLine();
						
						if(yesNo.equals("/cancel")) {
							System.out.println("작업을 종료합니다.");
							return;
						}
						
						System.in.read();
						System.in.read();
						if(yesNo.equalsIgnoreCase("y")) {
							subMenu = 1;
							break;
						}
						else if(yesNo.equalsIgnoreCase("n")) {
							System.out.println("상벌점 부여 작업을 종료합니다.\n");
							return;
						}
						else {
							System.out.println("잘못된 입력입니다. 다시 입력해 주십시오.");
						}
					}
				}else {
					while(true) {
						try {
							System.out.println("1. 상점\t2. 벌점");
							System.out.print("번호를 입력하세요 = ");
							
							String strSubMenu = sc.nextLine();
							if(strSubMenu.equals("/cancel")) {
								System.out.println("작업을 종료합니다.");
								return;
							}
							
							subMenu = Integer.parseInt(strSubMenu);
							if(subMenu != 1 && subMenu != 2)
								System.out.println("입력 범위를 벗어났습니다.");
							else
								break;
						} catch(NumberFormatException ex) {
							System.out.println("숫자를 입력해 주세요.");						
						}
					}
				}				
				
				// 상점 부여 모드일 때의 입력 상한은 80 - (현재 상벌점)
				// 벌점 부여 모드일 때의 입력 상한은 (현재 상벌점) + 40
				maxGiving = subMenu == 1 ? 80 - prisoner.getScore() : prisoner.getScore() + 40;
				
				while(true) {
					try {
						System.out.print(subMenu == 1 ? "상점 부여(1~" + maxGiving + ") = " : "벌점 부여(1~" + maxGiving + ") = ");
						
						String strScoreToAdd = sc.nextLine();
						if(strScoreToAdd.equals("/cancel")) {
							System.out.println("작업을 종료합니다.");
							return;
						}
						
						scoreToAdd = Integer.parseInt(strScoreToAdd);
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
