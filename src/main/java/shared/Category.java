package shared;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Menu> menus = new ArrayList<>();
    private static ArrayList<Category> categories = new ArrayList<Category>();

    public Category(String name) {
        this.name = name;
        addCategory(this);
    }

    // 카테고리 이름 Get
    public String getName() {
        return name;
    }

    // 카테고리 이름 Set
    public void setName(String name) {
        this.name = name;
    }

    // 카테고리 내 메뉴 가져오기
    public List<Menu> getMenus() {
        return menus;
    }

    // 카테고리 내에 메뉴 추가
    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    // 카테고리 추가
    private void addCategory(Category category) {
        categories.add(category);
    }

    // 카테고리 전체 찾기
    public static ArrayList<Category> getCategories() {
        return categories;
    }

    // 이름을 이용해 카테고리 찾기
    public static Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) return category;
        }
        return null;
    }

    // 카테고리 존재 여부 확인
    public static boolean isContainsCategory(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) return true;
        }
        return false;
    }

    // 메뉴, 카테고리 파일로 저장
    public static void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Category category : categories) {
                writer.write("Category:" + category.getName());
                writer.newLine();
                for (Menu menu : category.getMenus()) {
                    writer.write("Menu:" + menu.getName() + "," + category.getName());
                    writer.newLine();
                }
            }
        }
    }

    // 메뉴, 카테고리 파일에서 불러오기
    public static void loadFromFile(String fileName) throws IOException {
        categories.clear();

        // 리소스에서 파일 가져오기 (resources 안에 있을 경우)
        InputStream is = Category.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new FileNotFoundException(fileName + " 파일을 resources에서 찾을 수 없습니다.");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Category:")) {
                String categoryName = line.substring(9);
                new Category(categoryName);
            } else if (line.startsWith("Menu:")) {
                String[] parts = line.substring(5).split(",");
                String menuName = parts[0].trim();
                String categoryName = parts[1].trim();
                Category category = getCategoryByName(categoryName);
                if (category != null && !Menu.isContainsMenu(menuName)) {
                    new Menu(menuName, category);
                }
            }
        }
        reader.close();
    }


}
