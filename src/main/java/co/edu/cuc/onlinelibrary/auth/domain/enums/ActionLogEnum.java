package co.edu.cuc.onlinelibrary.auth.domain.enums;

public enum ActionLogEnum {
    ATTRIBUTE_NAME("action_log");

    final String value;

    ActionLogEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
