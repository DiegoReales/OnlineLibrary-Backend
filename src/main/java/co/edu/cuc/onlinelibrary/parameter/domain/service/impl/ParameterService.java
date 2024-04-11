package co.edu.cuc.onlinelibrary.parameter.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.parameter.domain.ParameterKey;
import co.edu.cuc.onlinelibrary.parameter.domain.dto.ParameterDto;
import co.edu.cuc.onlinelibrary.parameter.domain.repository.IParameterRepository;
import co.edu.cuc.onlinelibrary.parameter.domain.service.IParameterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ParameterService implements IParameterService {

    private final IParameterRepository parameterRepository;

    private static final String YES = "S";

    public ParameterService(IParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public String getValueByKey(ParameterKey parameterKey) {
        return parameterRepository.getValueByKey(parameterKey.getValue());
    }

    @Override
    public String getValueByKeyOrDefault(ParameterKey parameterKey, String defaultValue) {
        String value = this.getValueByKey(parameterKey);
        return Objects.nonNull(value) ? value : defaultValue;
    }

    @Override
    public Integer getIntegerValueByKey(ParameterKey parameterKey) {
        try {
            return Integer.valueOf(getValueByKeyOrDefault(parameterKey, null));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public boolean getBooleanValueByKey(ParameterKey parameterKey) {
        return YES.equals(getValueByKeyOrDefault(parameterKey, "N"));
    }


    /** CRUD */

    @Override
    public List<ParameterDto> findAll() {
        return parameterRepository.findAll();
    }

    @Override
    public ParameterDto findByKey(String parameterKey) {
        return parameterRepository.findTopByKey(parameterKey)
                .orElseThrow(() -> new HttpNotFoundException("Parametro no encontrado"));
    }

    @Override
    public ParameterDto save(ParameterDto parameterDto) {
        return parameterRepository.save(parameterDto);
    }

}
