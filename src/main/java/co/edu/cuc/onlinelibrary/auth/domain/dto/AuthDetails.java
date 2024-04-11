package co.edu.cuc.onlinelibrary.auth.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class AuthDetails {

    private int userId;
    private Integer emulatedId;

    public boolean isEmulated() {
        return Objects.nonNull(emulatedId) && emulatedId > 0;
    }
}
