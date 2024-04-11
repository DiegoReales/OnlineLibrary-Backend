package co.edu.cuc.onlinelibrary.parameter.domain.service;

import co.edu.cuc.onlinelibrary.parameter.domain.ParameterKey;
import co.edu.cuc.onlinelibrary.parameter.domain.dto.ParameterDto;

import java.util.List;

public interface IParameterService {
    String getValueByKey(ParameterKey parameterKey);
    String getValueByKeyOrDefault(ParameterKey parameterKey, String defaultValue);
    Integer getIntegerValueByKey(ParameterKey parameterKey);
    boolean getBooleanValueByKey(ParameterKey parameterKey);

    List<ParameterDto> findAll();

    ParameterDto findByKey(String parameterKey);
    ParameterDto save(ParameterDto parameterDto);
}
