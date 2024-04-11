package co.edu.cuc.onlinelibrary.auth.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class MenuBranchDto extends MenuItemDto {
	
	private List<MenuBranchDto> children;

	public MenuBranchDto(MenuItemDto item) {
		this.id = item.getId();
		this.parentId = item.getParentId();
		this.permission = item.getPermission();
		this.order = item.getOrder();
		this.title = item.getTitle();
		this.icon = item.getIcon();
		this.route = item.getRoute();
	}

	public static List<MenuBranchDto> buildNestedMenu(List<MenuItemDto> menu, int parentId) {
		List<MenuBranchDto> branches = new ArrayList<>();
		for (MenuItemDto item : menu) {
			if (item.getParentId() != parentId)
				continue;
			List<MenuBranchDto> children = buildNestedMenu(menu, item.getId());

			MenuBranchDto branch = new MenuBranchDto(item);
			if ((!children.isEmpty()))
				branch.setChildren(children);
			branches.add(branch);
		}
		return branches;
	}
}
