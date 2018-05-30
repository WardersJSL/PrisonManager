package main.console;

import view.ConsoleViewer;

public class MainProcessor {
	
	public static void main(String[] args) {
		ConsoleViewer console = new ConsoleViewer();	// 콘솔 UI 관리자
		int menu = 0;
		
		while(true) {
			// 메뉴 출력
			// 사용자에게 메뉴 번호를 받아 변수 menu에 저장
			switch(menu) {
			case 1:
				// 입소메뉴 실행
				break;
			case 2:
				// 출소메뉴 실행
				break;
			case 3:
				// 검색메뉴 실행
				break;
			case 4:
				// 전체조회 실행
				break;
			case 5:
				// 프로그램 종료
				System.exit(0);
			}
		}
	}
}
