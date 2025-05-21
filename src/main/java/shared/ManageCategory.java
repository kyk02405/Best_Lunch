package shared;

public class ManageCategory {
    // 카테고리 추가
    public void addCategory(String name) {
        if (!checkCategoryDuplication(name)) {
            new Category(name);
            System.out.println("다음 카테고리를 추가하였습니다: " + name);
        } else {
            System.out.println("이미 존재하는 카테고리입니다.");
        }
    }

    // 카테고리 이름 변경
    public void editCategoryName(String oldName, String newName) {
        if (checkCategoryDuplication(newName)) {
            System.out.println("새 카테고리 이름이 이미 존재합니다.");
            return;
        }

        // getCategoryByName을 이용해 변경할 카테고리 가져오기
        Category category = Category.getCategoryByName(oldName);
        if (category != null) {
            category.setName(newName);
            System.out.println("카테고리 이름이 수정되었습니다: " + oldName + " -> " + newName);
        } else {
            System.out.println("수정하려는 카테고리가 존재하지 않습니다.");
        }
    }

    // 카테고리 삭제
    // 카테고리 삭제 시 해당 카테고리에 있던 메뉴는 0번 인덱스 (카테고리 없음)으로 자동 변경
    public void removeCategory(String name) {
        Category category = Category.getCategoryByName(name);
        if (category != null) {
            Category defaultCategory = Category.getCategories().get(0);
            for (Menu menu : category.getMenus()) {
                menu.setCategory(defaultCategory);
            }
            Category.getCategories().remove(category);
            System.out.println("카테고리가 삭제되었습니다: " + name);
        } else {
            System.out.println("삭제하려는 카테고리가 존재하지 않습니다.");
        }
    }

    // 카테고리 중복 여부 확인
    private boolean checkCategoryDuplication(String category) {
        return Category.isContainsCategory(category);
    }
}
