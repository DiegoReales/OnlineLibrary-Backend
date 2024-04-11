package co.edu.cuc.onlinelibrary.auth.web.controller;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.MenuRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.auth.domain.service.IMenuService;
import co.edu.cuc.onlinelibrary.auth.domain.util.ObjectUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auth/menu")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AuthMenuController {

    private static final String MODULE = "AUTH:MENU";
    private final IMenuService menuService;

    @PreAuthorize("hasAuthority('menu:index')")
    @GetMapping
    @Operation(summary = "Obtener todos los menús", description = "Este endpoint obtiene todos los menús disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Éxito, devuelve la lista de menús"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<MenuDto>> index(HttpServletRequest request) {
        List<MenuDto> response = menuService.findAll();

        ActionLogDto actionLogDto = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los menus.")
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('menu:index')")
    @GetMapping("/tree")
    @Operation(summary = "Obtener arbol de menu", description = "Este endpoint obtiene todos los menús disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Éxito, devuelve la lista de menús"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<MenuDto>> tree(HttpServletRequest request) {
        List<MenuDto> response = menuService.getAllTree();

        ActionLogDto actionLogDto = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar arbol de menus.")
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('menu:show')")
    @GetMapping("/{menu_id}")
    @Operation(summary = "Obtener detalles de un menú por ID", description = "Este endpoint obtiene detalles de un menú por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Éxito, devuelve los detalles del menú"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "Menú no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MenuDto> show(
            @PathVariable("menu_id")
            @Parameter(description = "ID del menú a obtener")
            Integer menuId,
            @Parameter(hidden = true)
            HttpServletRequest request) {
        MenuDto response = menuService.findById(menuId);

        ActionLogDto actionLogDto = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar menu id " + response.getId())
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDto);
        return  ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('menu:store')")
    @PostMapping
    @Operation(summary = "Crear un nuevo menú", description = "Este endpoint crea un nuevo menú.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Éxito, devuelve el menú creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MenuDto> store(@Valid @RequestBody MenuDto menuItemDto, HttpServletRequest request) {
        MenuDto newMenu = menuService.save(menuItemDto);

        ActionLogDto actionLogDto = ActionLogDto.builder()
                .module(MODULE).action("CREATE")
                .message("Creando menu con id " + newMenu.getId())
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMenu);
    }

    @PreAuthorize("hasAuthority('menu:update')")
    @PutMapping("/{menu_id}")
    @Operation(summary = "Actualizar un menú por ID", description = "Este endpoint actualiza un menú por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Éxito, devuelve el menú actualizado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "Menú no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MenuDto> update
            (@PathVariable("menu_id")
             @Parameter(description = "ID del menú a actualizar")
             Integer menuId,
             @RequestBody @Valid MenuRequestBody requestBody,
             HttpServletRequest request) {

        MenuDto menuDto = menuService.findById(menuId);
        MenuDto beforeValue = ObjectUtils.clone(menuDto);

        menuDto.fillFromMenuRequestBody(requestBody);
        MenuDto afterValue = menuService.save(menuDto);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("UPDATE")
                .message("Editando menu con id " + afterValue.getId())
                .beforeValue(beforeValue)
                .afterValue(afterValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(afterValue);
    }

    @PreAuthorize("hasAuthority('menu:delete')")
    @DeleteMapping("/{menu_id}")
    @Operation(summary = "Eliminar un menú por ID", description = "Este endpoint elimina un menú por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Éxito, menú eliminado con éxito"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "Menú no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MenuDto> delete
            (@PathVariable("menu_id")
             @Parameter(description = "ID del menú a eliminar")
             Integer menuId,
             HttpServletRequest request) {
        MenuDto beforeValue = menuService.findById(menuId);
        menuService.deletedById(menuId);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("DELETE")
                .message("Eliminar menú con id " + menuId)
                .beforeValue(beforeValue)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.noContent().build();
    }
}
