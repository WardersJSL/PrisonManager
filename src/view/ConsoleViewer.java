package view;

import java.util.ArrayList;
import java.util.Scanner;

import action.Action;
import action.EnterAction;
import action.ReleaseAction;
import action.ShowAllAction;
import action.additional.GiveScoreAction;
import action.additional.IllAction;
import action.additional.LaborAction;
import action.additional.ParoleAction;
import action.additional.PunishAction;
import action.search.SearchByCrimeAction;
import action.search.SearchByNameAction;
import action.search.SearchByPrinumAction;
import action.search.SearchByTypeAction;
import controller.PrisonController;
import model.Prisoner;
import model.Prisoner.Crime;
import model.Prisoner.Type;

//콘솔 UI 클래스
public class ConsoleViewer {
	// 메뉴 식별번호 상수
	public static final int MENU_ID_MAIN = 1;			// 메인메뉴
	public static final int MENU_ID_ADDITIONAL = 2;		// 부가기능 메뉴
	public static final int MENU_ID_SEARCH = 3;			// 검색메뉴
	
	// 메뉴별 항목 갯수에 대한 상수
	public static final int MAX_MAIN_MENU = 7;			// 메인메뉴 항목 갯수
	public static final int MAX_ADDITIONAL_MENU = 6;	// 부가기능 메뉴 항목 갯수
	public static final int MAX_SEARCH_MENU = 5;		// 검색메뉴 항목 갯수
	
	// 메인 메뉴 항목 번호
	public static final int MAIN_MENU_ENTER = 1;		// 입소
	public static final int MAIN_MENU_RELEASE = 2;		// 출소
	public static final int MAIN_MENU_SEARCH = 3;		// 검색
	public static final int MAIN_MENU_SHOWALL = 4;		// 전체조회
	public static final int MAIN_MENU_ADDITIONAL = 5;	// 부가기능
	public static final int MAIN_MENU_EXIT = 6;			// 종료
	
	// 부가기능 메뉴 항목 번호
	public static final int ADDITIONAL_MENU_SCORE = 1;	// 상벌점 부여
	public static final int ADDITIONAL_MENU_PUNISH = 2;	// 징계
	public static final int ADDITIONAL_MENU_PAROLE = 3;	// 가석방
	public static final int ADDITIONAL_MENU_LABOR = 4;	// 노동량 갱신
	public static final int ADDITIONAL_MENU_ILL = 5;	// 질병유무 갱신
	public static final int ADDITIONAL_MENU_BACK = 6;	// 메인메뉴로 복귀
	
	// 검색기능 메뉴 항목 번호
	public static final int SEARCH_MENU_NAME = 1;		// 이름으로 검색
	public static final int SEARCH_MENU_PRINUM = 2;		// 죄수번호로 검색
	public static final int SEARCH_MENU_TYPE = 3;		// 죄수구분으로 검색
	public static final int SEARCH_MENU_CRIME = 4;		// 죄목으로 검색
	public static final int SEARCH_MENU_BACK = 5;		// 메인메뉴로 복귀
	
	private PrisonController prisonMgr;	// 교도소 관리자
	private Scanner sc = new Scanner(System.in);
	private Action action = null;
	
	public ConsoleViewer() {
		prisonMgr = new PrisonController();
	}
	
	public static void showMenu() { // 기본 메뉴
		System.out.println();
		System.out.println("=======================");
		System.out.println("  교도소 관리 프로그램");
		System.out.println("-----------------------");
		System.out.println("  1. 입소");
		System.out.println("  2. 출소");
		System.out.println("  3. 검색");
		System.out.println("  4. 전체조회");
		System.out.println("  5. 부가기능");
		System.out.println("  6. 종료");
		System.out.println("=======================");
	}
	
	/**
	 * 부가기능 메뉴 출력
	 */
	public static void showAdditionalMenu() {
		System.out.println("\n=======================");
		System.out.println(" 교도소 관리자 - 부가기능");
		System.out.println("-----------------------");
		System.out.println("  1. 상벌점 부여");
		System.out.println("  2. 징벌");
		System.out.println("  3. 가석방");
		System.out.println("  4. 노동량 갱신");
		System.out.println("  5. 질병유무 갱신");
		System.out.println("  6. 메인메뉴로 돌아가기");
		System.out.println("=======================");
	}
	
	/**
	 * 검색 메뉴 출력
	 */
	public static void showSearchMenu() {
		System.out.println("\n=======================");
		System.out.println(" 교도소 관리자 - 검색메뉴");
		System.out.println("-----------------------");
		System.out.println("  1. 이름으로 검색");
		System.out.println("  2. 죄수번호로 검색");
		System.out.println("  3. 죄수구분으로 검색");
		System.out.println("  4. 죄목으로 검색");
//		System.out.println("  5. 수감시설별 검색");
		System.out.println("  5. 메인메뉴로 돌아가기");	// Todo: 5번 메뉴를 사용하려면 이 번호를 수정할 것
		System.out.println("=======================");
	}
	
	/**
	 * 메인메뉴 실행
	 */
	public void runMainMenu() {
		while(true) {
			// 메뉴 출력
			showMenu();
			
			// 사용자에게 메뉴 번호를 받아 변수 menu에 저장
			switch(menuCheck(sc, MENU_ID_MAIN)) {
			case MAIN_MENU_ENTER:
				// 입소메뉴 실행
				action = new EnterAction();
				action.execute(sc);
				break;
			case MAIN_MENU_RELEASE:
				// 출소메뉴 실행
				action = new ReleaseAction();
				action.execute(sc);
				break;
			case MAIN_MENU_SEARCH:
				// 검색메뉴 실행
				runSearchMenu();
				break;
			case MAIN_MENU_SHOWALL:
				// 전체조회 실행
				action = new ShowAllAction();
				action.execute(sc);
				break;
			case MAIN_MENU_ADDITIONAL:
				runAdditionalMenu();
				break;
			case MAIN_MENU_EXIT:
				// 프로그램 종료
				System.exit(0);
			}
		}
	}
	
	/**
	 * 부가기능 메뉴 실행
	 */
	public void runAdditionalMenu() {
		while(true) {
			// 메뉴 출력
			showAdditionalMenu();
			
			// 사용자에게 메뉴 번호를 받아 변수 menu에 저장
			switch(menuCheck(sc, MENU_ID_ADDITIONAL)) {
			case ADDITIONAL_MENU_SCORE:
				action = new GiveScoreAction();
				action.execute(sc);
				break;
			case ADDITIONAL_MENU_PUNISH:
				action = new PunishAction();
				action.execute(sc);
				break;
			case ADDITIONAL_MENU_PAROLE:
				action = new ParoleAction();
				action.execute(sc);
				break;
			case ADDITIONAL_MENU_LABOR:
				action = new LaborAction();
				action.execute(sc);
				break;
			case ADDITIONAL_MENU_ILL:
				action = new IllAction();
				action.execute(sc);
				break;
			case ADDITIONAL_MENU_BACK:
				System.out.println();
				return;
			}
		}
	}
	
	/**
	 * 검색메뉴 실행
	 */
	public void runSearchMenu() {
		while(true) {
			// 메뉴 출력
			showSearchMenu();
			
			// 사용자에게 메뉴 번호를 받아 변수 menu에 저장
			switch(menuCheck(sc, MENU_ID_SEARCH)) {
			case SEARCH_MENU_NAME:
				action = new SearchByNameAction();
				action.execute(sc);
				break;
			case SEARCH_MENU_PRINUM:
				action = new SearchByPrinumAction();
				action.execute(sc);
				break;
			case SEARCH_MENU_TYPE:
				action = new SearchByTypeAction();
				action.execute(sc);
				break;
			case SEARCH_MENU_CRIME:
				action = new SearchByCrimeAction();
				action.execute(sc);
				break;
			case SEARCH_MENU_BACK:
				System.out.println();
				return;
			}
		}
	}
	
	public void enter() { // 입소메뉴
		String prisonerNo, name;	// 죄수번호, 이름
		int inputType;			// 죄수구분(정수형, 사용자 입력용)
		int inputCrime;			// 죄목(정수형, 사용자 입력용)
		int panaltyMonths = 0;	// 형량
		Prisoner.Crime crime;	// 죄목
		Prisoner.Type type;		// 죄수구분
		
		System.out.println("\n1. 입소");
		
		// 죄수번호 입력받기
		while(true) {
			System.out.print("죄수번호 = ");
			prisonerNo = sc.nextLine();
			if(!prisonMgr.isValidPrisonerNo(prisonerNo)) {
				System.out.println();
				System.out.println("잘못된 죄수번호입니다. 다시 입력해 주세요.");
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
		if(prisonMgr.AddPrisoner(newPrisoner)) {
			System.out.println();
			System.out.println("(" + newPrisoner.getName() + ")/(" + newPrisoner.getPrinum() + ") 의 입소가 완료되었습니다.");
		}
		else {
			System.out.println();
			System.out.println("입소 처리에 실패하였습니다.");
		}
	}
	
	// 죄수번호를 입력받고 일치하는 죄수를 삭제
	public void release() { // 출소메뉴
		String prisonerNo;	// 죄수번호
		
		System.out.println("\n2. 출소");
		
		// 유효한 죄수번호를 입력할 때까지 계속 입력받기
		while(true) {
			System.out.print("죄수번호 = ");
			prisonerNo = sc.nextLine();
			
			if(!prisonMgr.isValidPrisonerNo(prisonerNo)) {
				System.out.println();
				System.out.println("유효하지 않은 죄수번호입니다. 다시 입력해 주세요.");
			}
			else
				break;
		}
		
		if(prisonMgr.isExistPrisonerNo(prisonerNo)) {
			// 일치하는 죄수번호가 있으면, 삭제 작업을 수행하고 성공 여부 출력
			if(prisonMgr.DeletePrisoner(prisonerNo)) {
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
	}
	
	// 이름으로 검색
	public void search() { // 검색메뉴
		String name;	// 입력받을 이름
		ArrayList<Prisoner> foundPrisoners;	// 검색 결과를 저장할 리스트
		
		System.out.println("\n3. 검색");
		
		// 이름 입력받기
		System.out.print("이름 = ");
		name = sc.nextLine();
		
		// 이름이 일치하는 죄수정보 가져오기
		foundPrisoners = prisonMgr.FindPrisoner(name);
		
		// 죄수가 없을 경우 메시지 출력
		if(foundPrisoners == null || foundPrisoners.size() < 1) {
			System.out.println();
			System.out.println("이름이 일치하는 죄수가 없습니다.");
		}
		else { // 검색된 죄수(들)을 출력
			
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
			}
		}
	}
	
	public void showAll() { // 전체출력
		ArrayList<Prisoner> prisoners = prisonMgr.ShowAll();
		
		if(prisonMgr.ShowAll().size() < 1) {	// 죄수가 없으면 죄수 없음 메시지 출력
			System.out.println();
			System.out.println("죄수가 없습니다.");
			return;
		}
			System.out.println();
			System.out.println("=======================================================================================================");
			System.out.println("죄수번호\t\t이름\t\t죄목\t\t구분\t\t형량\t\t상벌점\t\t징벌횟수");
			System.out.println("-------------------------------------------------------------------------------------------------------");
				for (int i = 0; i < prisoners.size(); i++) {
				System.out.println(prisoners.get(i).getPrinum() + "\t"+prisoners.get(i).getName() + "\t\t"
							+ prisoners.get(i).crimeToString() + "\t\t" + prisoners.get(i).typeToString() +"\t\t" +
							prisoners.get(i).getPenalty() + "개월" + "\t\t" + prisoners.get(i).getScore() + "점"
							+ "\t\t" + prisoners.get(i).getPunish()+"번");
				}
			System.out.println("=======================================================================================================");
			System.out.println();
		}
	
	/**
	 * 사용자로부터 메뉴 번호를 입력받고 그 번호를 반환
	 * @param sc 스캐너 객체
	 * @param menuId 메뉴의 종류에 대한 식별번호
	 * @return 사용자에게 입력받은 메뉴번호
	 */
	public static int menuCheck(Scanner sc, int menuId) { // 사용자가 메뉴에서 입력한 값 체크
		int menu = 0;
		int menuNum = 0;
		
		// 메뉴 식별번호에 따른 메뉴 항목 갯수 설정
		switch(menuId) {
		case MENU_ID_MAIN:
			menuNum = MAX_MAIN_MENU;
			break;
		case MENU_ID_ADDITIONAL:
			menuNum = MAX_ADDITIONAL_MENU;
			break;
		case MENU_ID_SEARCH:
			menuNum = MAX_SEARCH_MENU;
			break;
		default:
			System.out.println("잘못된 매개변수입니다.");
			return 0;
		}
		
		do {
			try {
			System.out.print("메뉴 = ");
			menu = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println();
				System.out.println("잘못된 입력입니다.");
			}
			
			if(menu >= 1 && menu <= menuNum) {
				return menu;
			} else {
				System.out.println();
				System.out.println("범위를 벗어났습니다.");
			}
		} while(true);
	}
	
	
	 // 상벌점 부여 메뉴 실행
	public void giveScore() {
		//   사용자에게 죄수번호를 입력받고 그 죄수에게 상벌점 입력
		//   상점을 줄지 벌점을 줄지 선택
		//   상벌점을 부여했을 때 범위를 넘어가는 값이 입력될 경우 메시지를 출력하고 작업 취소
		String prisonerNo;
		while(true) {
			System.out.print("죄수번호 = ");
			prisonerNo = sc.nextLine();
			
			if(prisonMgr.isExistPrisonerNo(prisonerNo)) {
			System.out.print("상벌점 부여(-40~80) = ");
			int score = Integer.parseInt(sc.nextLine());
				if(prisonMgr.updateScore(prisonerNo, score)) {
					System.out.println();
					System.out.println(prisonerNo + "에게 " + score + "점을 부여하였습니다.");
					break;
				} else {
					System.out.println();
					System.out.println("범위를 잘못 입력했습니다. ");
					break;
				}
			} else {
				System.out.println();
				System.out.println("죄수번호가 존재하지 않습니다. ");
				break;
			}
		}
	}
	
	/**
	 * 징계 실행
	 */
	public void punish() {
		String prisonerNo;
		// 사용자에게 죄수번호를 입력받고 그 죄수에 대한 징계를 실행
		//   상벌점이 -40인 죄수만 징계 가능
		//   징계받은 죄수의 상벌점은 0으로 초기화(이 부분은 PrisonController.initScore() 메서드에서 처리함)
		
		while(true) {
		System.out.print("죄수번호 = ");
		prisonerNo = sc.nextLine();
		
			if(prisonMgr.isExistPrisonerNo(prisonerNo)) {
				if(prisonMgr.initScore(prisonerNo)) {
					System.out.println();
					System.out.println("징계 받음");
					break;
				} else {
					System.out.println();
					System.out.println("징계 점수가 부족합니다.");
					break;
				}
				
			} else {
				System.out.println();
				System.out.println("죄수번호가 존재하지 않습니다.");
				break;
			}
		}
	}
}
