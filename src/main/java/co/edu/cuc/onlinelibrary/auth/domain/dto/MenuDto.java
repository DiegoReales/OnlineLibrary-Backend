package co.edu.cuc.onlinelibrary.auth.domain.dto;

import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.MenuRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.tree.NodeTree;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuDto implements NodeTree {

	protected Integer id;
	@NotNull
	protected Integer parentId;
	@NotNull
	protected String title;
	@NotNull
	protected String icon;
	@NotNull
	protected String route;
	@NotNull
	protected Integer permissionId;
	@NotNull
	protected Integer order;

	protected boolean active;

	private List<MenuDto> children;

	public void fillFromMenuRequestBody(MenuRequestBody requestBody) {
		this.parentId = requestBody.getParentId();
		this.title = requestBody.getTitle();
		this.route = requestBody.getRoute();
		this.permissionId = requestBody.getPermissionId();
		this.order = requestBody.getOrder();
		this.active = requestBody.getActive();
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public Integer getParentId() {
		return this.parentId;
	}

	@Override
	public List<MenuDto> getChildren(){
		return this.children;
	}

	@Override
	public void setChildren(List<? extends NodeTree> children) {
		this.children = children.stream()
				.filter(MenuDto.class::isInstance)
				.map(e -> (MenuDto) e)
				.toList();
	}
}
