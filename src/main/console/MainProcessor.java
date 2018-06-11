package main.console;

import java.util.Scanner;

import action.Action;
import action.EnterAction;
import action.GiveScoreAction;
import action.PunishAction;
import action.ReleaseAction;
import action.ShowAllAction;
import view.ConsoleViewer;

public class MainProcessor {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Action action;
		int menu = 0;
		
		while(true) {
			// 메뉴 출력
			ConsoleViewer.showMenu();
			
			// 사용자에게 메뉴 번호를 받아 변수 menu에 저장
			switch(ConsoleViewer.menuCheck(sc)) {
			case 1:
				// 입소메뉴 실행
				action = new EnterAction();
				action.execute(sc);
				break;
			case 2:
				// 출소메뉴 실행
				action = new ReleaseAction();
				action.execute(sc);
				break;
			case 3:
				// 검색메뉴 실행
				//console.search();
				// (Action 구현중)
				break;
			case 4:
				// 전체조회 실행
				action = new ShowAllAction();
				action.execute(sc);
				break;
			case 5:
				// 상벌점메뉴 실행
				action = new GiveScoreAction();
				action.execute(sc);
				break;
			case 6:
				// 징벌메뉴 실행
				action = new PunishAction();
				action.execute(sc);
				break;
			case 7 :
				// 프로그램 종료
				System.exit(0);
			}
		}
	}
}
