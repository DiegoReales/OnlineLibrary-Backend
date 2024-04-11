package co.edu.cuc.onlinelibrary.auth.domain.repository;

import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuItemDto;

import java.util.List;
import java.util.Optional;

public interface IMenuRepository {
    List<MenuItemDto> findByRoleId(int roleId);
    MenuDto save(MenuDto menuItemDto);
    Optional<MenuDto> findByMenuId(int menuId);
    List<MenuDto> findAll();
    void deletedById(int menuId);
}
