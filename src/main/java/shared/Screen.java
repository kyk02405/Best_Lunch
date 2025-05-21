package shared;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Screen {
    private Scanner sc = new Scanner(System.in);
    private ManageCategory manageCategory = new ManageCategory();
    private ManageMenu manageMenu = new ManageMenu();

    public void screenMain() {
        while (true) {
            System.out.println("----------------------------------");
            System.out.println("| 1. 메뉴 보기 / 2. 메뉴 관리 / 0. 종료 |");
            System.out.println("----------------------------------");
            String input = sc.nextLine();
            switch (input) {
                case "1": screenView(); break;
                case "2": screenManage(); break;
                case "0": return;
                default: System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void screenView() {
        while (true) {
            System.out.println("-------------메뉴 보기 / 추천------------");
            System.out.println("| 1. 메뉴 보기 / 2. 메뉴 추천 / 0. 뒤로가기 |");
            System.out.println("-------------------------------------");
            String input = sc.nextLine();
            switch (input) {
                case "1": viewMenus(); break;
                case "2": recommendMenu(); break;
                case "0": return;
                default: System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void viewMenus() {
        List<Category> categories = Category.getCategories();
        if (categories.isEmpty()) {
            System.out.println("등록된 카테고리가 없습니다.");
            return;
        }

        System.out.println("-----------전체 메뉴 목록-----------");
        for (Category category : categories) {
            System.out.println("[카테고리: " + category.getName() + "]");
            for (Menu menu : category.getMenus()) {
                System.out.println(" - " + menu.getName());
            }
        }
    }

    private void recommendMenu() {
        List<Category> categories = Category.getCategories();
        if (categories.isEmpty()) {
            System.out.println("등록된 카테고리가 없습니다.");
            return;
        }

        System.out.println("--------카테고리 목록--------");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("카테고리 번호를 입력하세요: ");

        try {
            int index = Integer.parseInt(sc.nextLine()) - 1;
            if (index < 0 || index >= categories.size()) {
                System.out.println("잘못된 카테고리 번호입니다.");
                return;
            }

            Category selected = categories.get(index);
            List<Menu> menus = selected.getMenus();
            if (menus.isEmpty()) {
                System.out.println("해당 카테고리에 메뉴가 없습니다.");
                return;
            }

            Menu recommended = menus.get((int)(Math.random() * menus.size()));
            System.out.println("추천 메뉴: " + recommended.getName());

        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
        }
    }

    private void screenManage() {
        while (true) {
            System.out.println("-------------------------------------------------------------메뉴 / 카테고리 관리-------------------------------------------------------------");
            System.out.println("| 1. 메뉴 추가 / 2. 메뉴 수정 / 3. 메뉴 삭제 / 4. 카테고리 추가 / 5. 카테고리 수정 / 6. 카테고리 삭제 / 7. 메뉴, 카테고리 저장 / 8. 메뉴, 카테고리 불러오기 / 0. 뒤로가기 |");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.print("추가할 메뉴의 이름과 카테고리를 입력하세요 (,로 구분): ");
                    String[] parts = sc.nextLine().split(",");
                    if (parts.length == 2) {
                        manageMenu.addMenu(parts[0].trim(), parts[1].trim());
                    } else {
                        System.out.println("입력 형식이 잘못되었습니다.");
                    }
                    break;
                case "2":
                    System.out.print("수정할 메뉴 이름을 입력하세요: ");
                    String oldName = sc.nextLine();
                    System.out.print("새 메뉴 이름을 입력하세요: ");
                    String newName = sc.nextLine();
                    manageMenu.editMenuName(oldName, newName);
                    break;
                case "3":
                    System.out.print("삭제할 메뉴 이름을 입력하세요: ");
                    manageMenu.removeMenu(sc.nextLine());
                    break;
                case "4":
                    System.out.print("추가할 카테고리 이름을 입력하세요: ");
                    manageCategory.addCategory(sc.nextLine());
                    break;
                case "5":
                    System.out.print("수정할 카테고리 이름을 입력하세요: ");
                    String oldCat = sc.nextLine();
                    System.out.print("새 카테고리 이름을 입력하세요: ");
                    String newCat = sc.nextLine();
                    manageCategory.editCategoryName(oldCat, newCat);
                    break;
                case "6":
                    System.out.print("삭제할 카테고리 이름을 입력하세요: ");
                    manageCategory.removeCategory(sc.nextLine());
                    break;
                case "7":
                    System.out.print("저장할 파일명을 입력하세요: ");
                    try {
                        Category.saveToFile(sc.nextLine());
                        System.out.println("파일 저장 완료.");
                    } catch (IOException e) {
                        System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
                    }
                    break;
                case "8":
                    System.out.print("불러올 파일명을 입력하세요: ");
                    try {
                        Category.loadFromFile(sc.nextLine());
                        System.out.println("파일 불러오기 완료.");
                    } catch (IOException e) {
                        System.out.println("파일 불러오기 중 오류 발생: " + e.getMessage());
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
