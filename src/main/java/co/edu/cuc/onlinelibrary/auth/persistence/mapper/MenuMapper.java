package co.edu.cuc.onlinelibrary.auth.persistence.mapper;

import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuItemDto;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.MenuEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.projection.MenuItemProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

	MenuItemDto toMenuItemDto(MenuItemProjection entity);
	List<MenuItemDto> toListMenuItemDto(List<MenuItemProjection> entity);

	@Mapping(target = "children", ignore = true)
	MenuDto toMenuDto(MenuEntity entity);
	MenuEntity toMenuEntity(MenuDto dto);

	List<MenuDto> toListMenuDto(List<MenuEntity> entity);
	List<MenuEntity> toListMenuEntity(List<MenuDto> dto);
}
