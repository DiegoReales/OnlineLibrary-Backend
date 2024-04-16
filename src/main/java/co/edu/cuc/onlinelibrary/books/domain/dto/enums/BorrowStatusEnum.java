package co.edu.cuc.onlinelibrary.books.domain.dto.enums;

public enum BorrowStatusEnum {
    PENDING(1),
    FINISHED(2);

    final int value;

    BorrowStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
