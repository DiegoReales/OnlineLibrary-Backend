package co.edu.cuc.onlinelibrary.auth.persistence;

import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuItemDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IMenuRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.crudrepository.MenuCrudRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.MenuEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.mapper.MenuMapper;
import co.edu.cuc.onlinelibrary.auth.persistence.projection.MenuItemProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuRepository implements IMenuRepository {

    private final MenuCrudRepository menuCrudRepository;
    private final MenuMapper menuMapper;

    @Override
    public List<MenuItemDto> findByRoleId(int roleId) {
        List<MenuItemProjection> entities = menuCrudRepository.findMenuByRoleId(roleId);
        return menuMapper.toListMenuItemDto(entities);
    }

    @Override
    @Transactional
    public MenuDto save(MenuDto menuItemDto) {
        MenuEntity entity = menuMapper.toMenuEntity(menuItemDto);
        return menuMapper.toMenuDto(menuCrudRepository.save(entity));
    }

    @Override
    public Optional<MenuDto> findByMenuId(int menuId) {
        return menuCrudRepository.findById(menuId)
                .map(menuMapper::toMenuDto);
    }

    @Override
    public List<MenuDto> findAll() {
        List<MenuEntity> entities = (List<MenuEntity>) menuCrudRepository.findAll();
        return menuMapper.toListMenuDto(entities);
    }

    @Override
    @Transactional
    public void deletedById(int menuId) {
        menuCrudRepository.deleteById(menuId);
    }
}
