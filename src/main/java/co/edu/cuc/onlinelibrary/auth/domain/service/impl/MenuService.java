package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.tree.NodeTree;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IMenuRepository;
import co.edu.cuc.onlinelibrary.auth.domain.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements IMenuService {

    private final IMenuRepository menuRepository;


    @Override
    public List<MenuDto> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public List<MenuDto> getAllTree() {
        List<MenuDto> menu = menuRepository.findAll();
        return NodeTree.buildNestedNodeTree(menu);
    }

    @Override
    public MenuDto findById(int menuId) {
        return menuRepository.findByMenuId(menuId)
                .orElseThrow(() -> new HttpNotFoundException("Menú no encontrado"));
    }

    @Override
    public MenuDto save(MenuDto menuItemDto) {
        return menuRepository.save(menuItemDto);
    }

    @Override
    public void deletedById(int menuId) {
        menuRepository.deletedById(menuId);
    }
}
