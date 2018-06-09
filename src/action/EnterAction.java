package action;

import java.util.Scanner;

import controller.PrisonController;
import model.Prisoner;
import model.Prisoner.Crime;
import model.Prisoner.Type;
import model.PrisonerDAO;

public class EnterAction implements Action {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		
		String prisonerNo, name;	// 죄수번호, 이름
		int inputType;			// 죄수구분(정수형, 사용자 입력용)
		int inputCrime;			// 죄목(정수형, 사용자 입력용)
		int panaltyMonths = 0;	// 형량
		Prisoner.Crime crime;	// 죄목
		Prisoner.Type type;		// 죄수구분
		
		try {
			PrisonerDAO dao = new PrisonerDAO();
			
			System.out.println("\n1. 입소");
			
			// 죄수번호 입력받기
			while(true) {
				System.out.print("죄수번호 = ");
				prisonerNo = sc.nextLine();
				if(!PrisonController.isValidPrisonerNo(prisonerNo)) {
					System.out.println("잘못된 죄수번호입니다. 다시 입력해 주세요.");
				}
				else if(dao.selectPrisonerByPrinum(prisonerNo) != null) {
					System.out.println("이미 등록된 죄수번호입니다. 다시 입력해 주세요.");
				}
				else
					break;
			}
			
			// 이름 입력받기
			while(true) {
				System.out.print("이름(2~20자) = ");
				name = sc.nextLine();
				if(name.length() < 2 || name.length() > 20) {
					System.out.println();
					System.out.println("이름의 길이가 범위를 벗어났습니다. 다시 입력해 주세요.");
				}
				else
					break;
			}
			
			// 형량 입력받기
			while(true) {
				System.out.print("형량(1~9999개월) = ");
				try {
					panaltyMonths = Integer.parseInt(sc.nextLine());
					if(panaltyMonths < 0 || panaltyMonths > 9999) {
						System.out.println();
						System.out.println("형량이 범위를 벗어났습니다. 다시 입력해 주세요.");
					}
					else
						break;
				}
				catch(Exception ex) {
					System.out.println();
					System.out.println("잘못된 입력입니다. 숫자만 입력해 주세요.");
				}
				
			}
			
			// 죄목 입력받기
			while(true) {
				System.out.print("죄목(1.절도  2.사기  3.살인  4.강간  5.폭행  6.마약  7.공금횡령) = ");
				try {
					inputCrime = Integer.parseInt(sc.nextLine());
					if(inputCrime < 1 || inputCrime > 7) {
						System.out.println();
						System.out.println("입력 범위를 벗어났습니다. 다시 입력해 주세요.");
					}
					else
						break;
				}
				catch(Exception ex) {
					System.out.println();
					System.out.println("잘못된 입력입니다. 숫자만 입력해 주세요.");
				}
			}
			
			// 죄수구분 입력받기
			while(true) {
				System.out.print("죄수구분(1.일반  2.요시찰  3.마약사범  4.사형수) = ");
				try {
					inputType = Integer.parseInt(sc.nextLine());
					if(inputType < 1 || inputType > 4) {
						System.out.println();
						System.out.println("입력 범위를 벗어났습니다. 다시 입력해 주세요.");
					}
					else
						break;
				}
				catch(Exception ex) {
					System.out.println();
					System.out.println("잘못된 입력입니다. 숫자만 입력해 주세요.");
				}
			}
			
			// 죄목 및 죄수구분 설정
			switch(inputCrime) {
			case 1:	// 절도
				crime = Crime.THEFT;
				break;
			case 2:	// 사기
				crime = Crime.DECEIVE;
				break;
			case 3:	// 살인
				crime = Crime.MURDER;
				break;
			case 4:	// 강간
				crime = Crime.RAPE;
				break;
			case 5:	// 폭행
				crime = Crime.VIOLENCE;
				break;
			case 6:	// 마약
				crime = Crime.DRUG;
				break;
			case 7:	// 횡령
				crime = Crime.EMBEZZLE;
				break;
			default:
				System.out.println("죄목 값이 잘못되었습니다. 입소 처리를 취소합니다.");
				return;
			}
			switch(inputType) {
			case 1:	// 일반
				type = Type.NORMAL;
				break;
			case 2:	// 요시찰
				type = Type.WATCH;
				break;
			case 3:	// 마약사범
				type = Type.DRUG;
				break;
			case 4:	// 사형수
				type = Type.EXECUTE;
				break;
			default:
				System.out.println("죄목 값이 잘못되었습니다. 입소 처리를 취소합니다.");
				return;
			}
			
			// 죄수를 추가하고 결과 출력
			// 상벌점, 노동량은 0으로 초기화
			// 질병유무는 false로 초기화
			Prisoner newPrisoner = new Prisoner(name, prisonerNo, crime, type, panaltyMonths, 0, 0, false, 0);
			if(dao.insertPrisoner(newPrisoner)) {
				System.out.println();
				System.out.println("(" + newPrisoner.getName() + ")/(" + newPrisoner.getPrinum() + ") 의 입소가 완료되었습니다.");
			}
			else {
				System.out.println();
				System.out.println("입소 처리에 실패하였습니다.");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
