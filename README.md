# 🍱 Best_Lunch - 점심메뉴 추천 프로그램

Java 기반 콘솔 애플리케이션으로, 사용자가 점심메뉴를 직접 등록하고 추천을 받을 수 있는 시스템입니다.  
카테고리 기반으로 메뉴를 관리하며, 텍스트 파일을 통해 저장 및 불러오기 기능도 지원합니다.

---

## 📌 주요 기능

- ✅ 카테고리 추가 / 수정 / 삭제
- ✅ 메뉴 추가 / 수정 / 삭제
- ✅ 랜덤 메뉴 추천 기능
- ✅ 메뉴와 카테고리 정보 파일 저장 및 불러오기 (`Menus.txt`)
- ✅ 콘솔 기반 사용자 친화적 메뉴 UI
- ✅ Java 텍스트 파일 입출력 활용

---

## 🖥️ 실행 방법

1. Java 17 이상 설치
2. 프로젝트 루트에서 컴파일:

```bash
javac -d out src/main/java/idusw/sb/*.java
```

3. 실행:

```bash
java -cp out idusw.sb.Main
```

> 또는 IntelliJ에서 `Main.java` 실행

---

## 📂 폴더 구조

```
Best_Lunch/
├── src/
│   └── main/
│       └── java/
│           └── idusw/
│               └── sb/
│                   ├── Category.java
│                   ├── Menu.java
│                   ├── Screen.java
│                   ├── ManageMenu.java
│                   ├── ManageCategory.java
│                   └── Main.java
├── resources/
│   └── Menus.txt
└── README.md
```

---

## 💾 데이터 저장 예시 (`Menus.txt`)

```
Category:한식
Menu:김치찌개,한식
Menu:비빔밥,한식
Category:중식
Menu:짜장면,중식
Menu:탕수육,중식
```

---

## 👨‍💻 개발자

- 이름: 김경윤 (Kyungyoon Kim)
- GitHub: [@kyk02405](https://github.com/kyk02405)

---

## 📜 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다.
