package server;

public enum Menu2 {
    EXIT(0, "연결 종료"),
    ADD_STUDENT(1, "신규 학생 정보 등록"),
    GET_STUDENT_INFO(2, "학생 목록 출력"),
    DELETE_STUDENT(3, "학생 삭제"),
    EDIT_STUDENT(4, "학생 수정"),
    SEARCH_STUDENT(5, "학생 검색"),
    UPLOAD_FILE(6, "파일 업로드");

    private final int code;
    private final String description;

    Menu2(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Menu2 fromCode(int code) {
        for (Menu2 menu : values()) {
            if (menu.code == code) {
                return menu;
            }
        }
        return null;
    }

    public static void printMenu() {
        System.out.println("<< 학생 관리 시스템 >>");
        for (Menu2 menu : values()) {
            System.out.printf("%d. %s%n", menu.getCode(), menu.getDescription());
        }
        System.out.print(">> ");
    }
}