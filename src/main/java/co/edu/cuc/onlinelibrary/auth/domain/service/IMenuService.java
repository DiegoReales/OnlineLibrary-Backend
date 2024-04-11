package co.edu.cuc.onlinelibrary.auth.domain.service;

import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuBranchDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuDto;

import java.util.List;

public interface IMenuService {
    List<MenuDto> findAll();
    List<MenuDto> getAllTree();
    MenuDto findById(int menuId);
    MenuDto save(MenuDto menuItemDto);
    void deletedById(int menuId);

}
