package co.edu.cuc.onlinelibrary.auth.web.controller;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.PermissionDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.RoleDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.RoleRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.auth.domain.service.IRoleService;
import co.edu.cuc.onlinelibrary.auth.domain.util.ObjectUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/auth/roles")
@RequiredArgsConstructor
public class AuthRoleController {
    private static final String MODULE = "AUTH:ROLE";
    private final IRoleService roleService;

    @PreAuthorize("hasAuthority('role:index')")
    @GetMapping
    public ResponseEntity<List<RoleDto>> index(HttpServletRequest request) {
        List<RoleDto> response = roleService.findAll();

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los roles.")
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('role:show')")
    @GetMapping("/{role_id}")
    public ResponseEntity<RoleDto> show(@PathVariable("role_id") Integer roleId, HttpServletRequest request) {
        RoleDto response = roleService.findByIdWithPermission(roleId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Consultar rol con id " + roleId)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('role:permission-by-role')")
    @GetMapping("/{role_id}/permissions")
    public ResponseEntity<List<PermissionDto>> index(@PathVariable("role_id") Integer roleId, HttpServletRequest request){
        RoleDto response = roleService.findByIdWithPermission(roleId);

        ActionLogDto actionLogDto = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Consultar permiso para el rol " + roleId)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDto);
        return ResponseEntity.ok(response.getPermissions());
    }

    @PreAuthorize("hasAuthority('role:store')")
    @PostMapping
    public ResponseEntity<RoleDto> store(
            @RequestBody @Valid RoleRequestBody requestBody,
            HttpServletRequest request) {

        RoleDto roleDto = new RoleDto();
        roleDto.fillFromRoleRequestBody(requestBody);
        RoleDto response = roleService.save(roleDto);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("CREATE")
                .message("Creando rol con id " + response.getId())
                .afterValue(response)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping("/{role_id}")
    public ResponseEntity<RoleDto> update(
            @PathVariable("role_id") Integer roleId,
            @RequestBody @Valid RoleRequestBody requestBody,
            HttpServletRequest request) {

        RoleDto roleDto = roleService.findById(roleId);
        RoleDto beforeValue = ObjectUtils.clone(roleDto);

        roleDto.fillFromRoleRequestBody(requestBody);
        RoleDto afterValue = roleService.save(roleDto);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("UPDATE")
                .message("Editando rol con id " + afterValue.getId())
                .beforeValue(beforeValue)
                .afterValue(afterValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(afterValue);
    }

    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping("/{role_id}/permissions")
    public ResponseEntity<RoleDto> syncPermissions(
            @PathVariable("role_id") Integer roleId,
            @RequestBody List<Integer> permissionsIds,
            HttpServletRequest request) {


        RoleDto afterValue = roleService.syncPermissionsToRole(roleId, permissionsIds);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("UPDATE")
                .message("Asignando permisos al rol con id " + afterValue.getId())
                .afterValue(afterValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(afterValue);
    }

    @PreAuthorize("hasAuthority('role:update')")
    @PostMapping("/{role_id}/permissions/{permission_id}")
    public ResponseEntity<RoleDto> addPermissionToRole(
            @PathVariable("role_id") Integer roleId,
            @PathVariable("permission_id") Integer permissionId,
            HttpServletRequest request) {


        RoleDto afterValue = roleService.addPermissionToRole(roleId, permissionId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("CREATE")
                .message("Asignando permiso id " + permissionId + " al role id " + afterValue.getId())
                .afterValue(afterValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(afterValue);
    }

    @PreAuthorize("hasAuthority('role:update')")
    @DeleteMapping("/{role_id}/permissions/{permission_id}")
    public ResponseEntity<RoleDto> removePermissionToRole(
            @PathVariable("role_id") Integer roleId,
            @PathVariable("permission_id") Integer permissionId,
            HttpServletRequest request) {

        RoleDto afterValue = roleService.removePermissionToRole(roleId, permissionId);
        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("DELETE")
                .message("Eliminar permiso id " + permissionId + " al role id " + afterValue.getId())
                .afterValue(afterValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(afterValue);
    }

    @PreAuthorize("hasAuthority('role:delete')")
    @DeleteMapping("/{role_id}")
    public ResponseEntity<RoleDto> delete(
            @PathVariable("role_id") Integer roleId,
            HttpServletRequest request) {

        RoleDto beforeValue = roleService.findById(roleId);
        roleService.deletedById(roleId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("DELETE")
                .message("Eliminar rol con id " + roleId)
                .beforeValue(beforeValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.noContent().build();
    }
}
