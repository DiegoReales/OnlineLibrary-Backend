package co.edu.cuc.onlinelibrary.parameter.web.controller;


import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.auth.domain.util.ObjectUtils;
import co.edu.cuc.onlinelibrary.parameter.domain.dto.ParameterDto;
import co.edu.cuc.onlinelibrary.parameter.domain.dto.requestbody.ParameterRequestBody;
import co.edu.cuc.onlinelibrary.parameter.domain.service.IParameterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/parameters")
@RequiredArgsConstructor
public class ParameterController {

    private static final String MODULE = "AUTH:MENU";
    private final IParameterService parameterService;


    @PreAuthorize("hasAuthority('parameter:index')")
    @GetMapping
    @Operation(summary = "Obtener todos los parametros")
    public ResponseEntity<List<ParameterDto>> index(HttpServletRequest request) {
        List<ParameterDto> response = parameterService.findAll();

        ActionLogDto actionLogDto = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Listar todos los parametros.")
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('parameter:show')")
    @GetMapping("/{parameter_key}")
    @Operation(summary = "Obtener un parametro por código")
    public ResponseEntity<ParameterDto> show(
            @PathVariable("parameter_key") String parameterKey,
            HttpServletRequest request) {
        ParameterDto response = parameterService.findByKey(parameterKey);

        ActionLogDto actionLogDto = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Obtener un parametro por código " + parameterKey)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('parameter:update')")
    @PutMapping("/{parameter_key}")
    @Operation(summary = "Obtener un parametro por código")
    public ResponseEntity<ParameterDto> update(
            @PathVariable("parameter_key") String parameterKey,
            @RequestBody ParameterRequestBody requestBody,
            HttpServletRequest request) {

        ParameterDto parameterDto = parameterService.findByKey(parameterKey);
        ParameterDto beforeValue = ObjectUtils.clone(parameterDto);

        parameterDto.setValue(requestBody.getValue());
        parameterDto.setUpdatedAt(LocalDateTime.now());
        ParameterDto response = parameterService.save(parameterDto);

        ActionLogDto actionLogDto = ActionLogDto.builder()
                .module(MODULE).action("READ")
                .message("Editar parametro por codigo " + parameterKey)
                .beforeValue(beforeValue)
                .afterValue(response)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDto);
        return ResponseEntity.ok(response);
    }
}
