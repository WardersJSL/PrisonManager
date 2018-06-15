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
	
	/**
	 * 검색 결과 출력
	 * @param prisoner 죄수 정보
	 */
	public static void showSearchResult(Prisoner prisoner) {
		System.out.println("----------------------------------------");
		System.out.print(prisoner);
		System.out.println("----------------------------------------");
	}
	
	/**
	 * 검색 결과 출력(오버로딩)
	 * @param prisoners	죄수 정보 리스트
	 * @param value		검색값
	 * @param menuId	검색 코드
	 */
	public static void showSearchResult(ArrayList<Prisoner> prisoners, String value, int menuId) {
		switch(menuId) {
		case SEARCH_MENU_NAME:
			System.out.println("========================================================================");
			System.out.println("  죄수명단\t이름 : " + value);
			System.out.println("========================================================================");
			System.out.println("죄수번호\t\t죄수구분\t죄목\t형량\t\t노동량\t질병\t징계횟수");
			System.out.println("------------------------------------------------------------------------");
			for(int i = 0; i < prisoners.size(); i++) {
				System.out.print(prisoners.get(i).getPrinum() + "\t");
				System.out.print(prisoners.get(i).typeToString() + "\t");
				System.out.print(prisoners.get(i).crimeToString() + "\t");
				if(prisoners.get(i).getPenalty() / 12 > 0)
					System.out.print(prisoners.get(i).getPenalty() / 12 + "년 ");
				if(prisoners.get(i).getPenalty() % 12 > 0)
					System.out.print(prisoners.get(i).getPenalty() % 12 + "개월");
				System.out.print("\t");
				System.out.print(prisoners.get(i).getWork() + "일\t");
				System.out.print((prisoners.get(i).isIll() ? "있음" : "없음") + "\t");
				System.out.println(prisoners.get(i).getPunish());
			}
			break;
		case SEARCH_MENU_CRIME:
			System.out.println("========================================================================");
			System.out.println("  죄수명단\t죄목 : " + value);
			System.out.println("========================================================================");
			System.out.println("죄수번호\t\t이름\t죄수구분\t형량\t\t노동량\t질병\t징계횟수");
			System.out.println("------------------------------------------------------------------------");
			for(int i = 0; i < prisoners.size(); i++) {
				System.out.print(prisoners.get(i).getPrinum() + "\t");
				System.out.print(prisoners.get(i).getName() + "\t");
				System.out.print(prisoners.get(i).typeToString() + "\t");
				if(prisoners.get(i).getPenalty() / 12 > 0)
					System.out.print(prisoners.get(i).getPenalty() / 12 + "년 ");
				if(prisoners.get(i).getPenalty() % 12 > 0)
					System.out.print(prisoners.get(i).getPenalty() % 12 + "개월");
				System.out.print("\t");
				System.out.print(prisoners.get(i).getWork() + "일\t");
				System.out.print((prisoners.get(i).isIll() ? "있음" : "없음") + "\t");
				System.out.println(prisoners.get(i).getPunish());
			}
			break;
		case SEARCH_MENU_TYPE:
			System.out.println("========================================================================");
			System.out.println("  죄수명단\t죄수구분 : " + value);
			System.out.println("========================================================================");
			System.out.println("죄수번호\t\t이름\t죄목\t형량\t\t노동량\t질병\t징계횟수");
			System.out.println("------------------------------------------------------------------------");
			for(int i = 0; i < prisoners.size(); i++) {
				System.out.print(prisoners.get(i).getPrinum() + "\t");
				System.out.print(prisoners.get(i).getName() + "\t");
				System.out.print(prisoners.get(i).crimeToString() + "\t");
				if(prisoners.get(i).getPenalty() / 12 > 0)
					System.out.print(prisoners.get(i).getPenalty() / 12 + "년 ");
				if(prisoners.get(i).getPenalty() % 12 > 0)
					System.out.print(prisoners.get(i).getPenalty() % 12 + "개월");
				System.out.print("\t");
				System.out.print(prisoners.get(i).getWork() + "일\t");
				System.out.print((prisoners.get(i).isIll() ? "있음" : "없음") + "\t");
				System.out.println(prisoners.get(i).getPunish());
			}
			break;
		default:
			System.err.println("오류: 잘못된 매개변수(ConsoleViewer.showSearchResult(ArrayList<Prisoner>, int))");
		}
		
		System.out.println("========================================================================");
	}
}
