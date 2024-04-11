package co.edu.cuc.onlinelibrary.auth.domain.enums;

import co.edu.cuc.onlinelibrary.parameter.domain.ParameterKey;

public enum AuthParams implements ParameterKey {
    AUTH_VGR("AUTH_VGR"),
    AUTH_IDLE_TIME("AUTH_IDLE_TIME"),
    AUTH_PASSWORD_LENGTH("AUTH_PASSWORD_LENGTH"),
    AUTH_PASSWORD_EXPMIN("AUTH_PASSWORD_EXPMIN");

    final String value;

    AuthParams(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
