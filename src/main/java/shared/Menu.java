package shared;

public class Menu {
    private String name;
    private Category category;

    public Menu(String name, Category category) {
        this.name = name;
        this.category = category;
        category.addMenu(this);
    }

    // 메뉴 이름 Get
    public String getName() {
        return name;
    }

    // 메뉴 이름 Set
    public void setName(String name) {
        this.name = name;
    }

    // 메뉴 카테고리 Get
    public Category getCategory() {
        return category;
    }

    // 메뉴 카테고리 Set
    public void setCategory(Category category) {
        this.category = category;
        category.addMenu(this);
    }

    // 메뉴 존재 여부 확인
    public static boolean isContainsMenu(String name) {
        for (Category category : Category.getCategories()) {
            for (Menu menu : category.getMenus()) {
                if (menu.getName().equals(name)) return true;
            }
        }
        return false;
    }
}
