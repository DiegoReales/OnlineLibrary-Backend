package co.edu.cuc.onlinelibrary.auth.web.controller;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.PermissionDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.PermissionRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.auth.domain.service.IPermissionService;
import co.edu.cuc.onlinelibrary.auth.domain.util.ObjectUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/permissions")
@RequiredArgsConstructor
public class AuthPermissionController {
    private static final String MODULE = "AUTH:PERMISSION";

    private final IPermissionService permissionService;

    @PreAuthorize("hasAuthority('permission:index')")
    @GetMapping
    public ResponseEntity<List<PermissionDto>> index(HttpServletRequest request) {
        List<PermissionDto> response = permissionService.findAll();

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los permisos.")
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('permission:show')")
    @GetMapping("/{permission_id}")
    public ResponseEntity<PermissionDto> show(@PathVariable("permission_id") Integer permissionId, HttpServletRequest request) {
        PermissionDto response = permissionService.findById(permissionId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Consultar permiso con id " + permissionId)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('permission:store')")
    @PostMapping
    public ResponseEntity<PermissionDto> store(
            @RequestBody @Valid PermissionRequestBody requestBody,
            HttpServletRequest request) {

        PermissionDto permissionDto = new PermissionDto();
        permissionDto.fillFromPermissionRequestBody(requestBody);
        PermissionDto response = permissionService.save(permissionDto);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("CREATE")
                .message("Creando permiso con id " + response.getId())
                .afterValue(response)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAuthority('permission:update')")
    @PutMapping("/{permission_id}")
    public ResponseEntity<PermissionDto> update(
            @PathVariable("permission_id") Integer permissionId,
            @RequestBody @Valid PermissionRequestBody requestBody,
            HttpServletRequest request) {

        PermissionDto permissionDto = permissionService.findById(permissionId);
        PermissionDto beforeValue = ObjectUtils.clone(permissionDto);

        permissionDto.fillFromPermissionRequestBody(requestBody);
        PermissionDto afterValue = permissionService.save(permissionDto);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("UPDATE")
                .message("Editando permiso con id " + afterValue.getId())
                .beforeValue(beforeValue)
                .afterValue(afterValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(afterValue);
    }

    @PreAuthorize("hasAuthority('permission:delete')")
    @DeleteMapping("/{permission_id}")
    public ResponseEntity<PermissionDto> delete(
            @PathVariable("permission_id") Integer permissionId,
            HttpServletRequest request) {

        PermissionDto beforeValue = permissionService.findById(permissionId);
        permissionService.deletedById(permissionId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("DELETE")
                .message("Eliminar permiso con id " + permissionId)
                .beforeValue(beforeValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.noContent().build();
    }
}
