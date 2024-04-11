package co.edu.cuc.onlinelibrary.auth.web.controller;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.UserRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.auth.domain.service.IUserService;
import co.edu.cuc.onlinelibrary.auth.domain.util.CustomPage;
import co.edu.cuc.onlinelibrary.auth.domain.util.ObjectUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
public class AuthUserController {
    private static final String MODULE = "AUTH:USER";
    private final IUserService userService;

    public AuthUserController(IUserService userService) {
        this.userService = userService;
    }
    

    @PreAuthorize("hasAuthority('user:index')")
    @GetMapping
    public ResponseEntity<CustomPage<UserDto>> index (Pageable pageable, HttpServletRequest request){
        CustomPage<UserDto> response = userService.findAll(pageable);
        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los usuarios.")
                .build();
        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('user:show')")
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> show(@PathVariable("user_id") Integer userId, HttpServletRequest request){
        UserDto response = userService.findById(userId);
        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Consultar usuario con id " + userId)
                .build();
        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('user:store')")
    @PostMapping
    public ResponseEntity<UserDto> store(@RequestBody @Valid UserRequestBody requestBody, HttpServletRequest request){
        UserDto response = userService.create(requestBody);
        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("CREATE")
                .message("Creación de usuario " + response.getUsername())
                .afterValue(response)
                .build();
        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('user:update')")
    @PutMapping("/{user_id}")
    public ResponseEntity<UserDto> update(
            @PathVariable("user_id") Integer userId,
            @RequestBody @Valid UserRequestBody requestBody,
            HttpServletRequest request) {

        UserDto userDto = userService.findById(userId);

        UserDto beforeValue = ObjectUtils.clone(userDto);

        userDto.fillFromUserRequestBody(requestBody);
        UserDto response = userService.save(userDto);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("UPDATE")
                .message("actualiza el usuario " + response.getUsername())
                .beforeValue(beforeValue)
                .afterValue(response)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/{user_id}")
    public ResponseEntity<UserDto> delete(@PathVariable("user_id") Integer userId, HttpServletRequest request){
        UserDto beforeValue = userService.deletedById(userId);
        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("DELETE")
                .message("elimiar usuario con id " + userId)
                .afterValue(beforeValue)
                .build();
        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.noContent().build();
    }
}