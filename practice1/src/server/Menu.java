package server;

public enum Menu {
    EXIT(0),
    ADD_STUDENT(1),
    GET_STUDENT_INFO(2),
    DELETE_STUDENT(3),
    EDIT_STUDENT(4),
    SEARCH_STUDENT(5),
    UPLOAD_FILE(6);

    private final int code;

    Menu(int code) {
        this.code = code;
    }

    public static Menu fromCode(int code) {
        for (Menu menu : values()) {
            if (menu.code == code) {
                return menu;
            }
        }
        return null;
    }
}
