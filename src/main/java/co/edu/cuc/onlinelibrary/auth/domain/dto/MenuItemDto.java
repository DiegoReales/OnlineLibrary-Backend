package co.edu.cuc.onlinelibrary.auth.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItemDto implements Serializable {
    @JsonIgnore
    protected int id;

    @JsonIgnore
    protected int parentId;

    protected String title;
    protected String icon;
    protected String route;
    protected String permission;

    @JsonIgnore
    protected int order;
}
