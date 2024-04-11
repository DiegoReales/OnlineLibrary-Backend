package co.edu.cuc.onlinelibrary.parameter.domain.repository;

import co.edu.cuc.onlinelibrary.parameter.domain.dto.ParameterDto;

import java.util.List;
import java.util.Optional;

public interface IParameterRepository {
    String getValueByKey(String key);
    List<ParameterDto> findAll();
    Optional<ParameterDto> findTopByKey(String key);
    ParameterDto save(ParameterDto parameterDto);
}
