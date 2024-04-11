package co.edu.cuc.onlinelibrary.auth.persistence.projection;

public interface MenuItemProjection {
    Integer getId();
    Integer getParentId();
    String getTitle();
    String getIcon();
    String getRoute();
    String getPermission();
    Integer getOrder();
}
