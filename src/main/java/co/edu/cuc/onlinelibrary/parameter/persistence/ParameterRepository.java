package co.edu.cuc.onlinelibrary.parameter.persistence;

import co.edu.cuc.onlinelibrary.parameter.domain.dto.ParameterDto;
import co.edu.cuc.onlinelibrary.parameter.domain.repository.IParameterRepository;
import co.edu.cuc.onlinelibrary.parameter.persistence.crudrepository.ParameterCrudRepository;
import co.edu.cuc.onlinelibrary.parameter.persistence.entity.ParameterEntity;
import co.edu.cuc.onlinelibrary.parameter.persistence.mapper.ParameterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ParameterRepository implements IParameterRepository {

    private final ParameterCrudRepository parameterCrudRepository;
    private final ParameterMapper parameterMapper;

    @Override
    public String getValueByKey(String key) {
        Optional<String> optionalValue = parameterCrudRepository.getValueByKey(key);
        return optionalValue.map(String::valueOf).orElse(null);
    }

    @Override
    public List<ParameterDto> findAll() {
        List<ParameterEntity> entities = (List<ParameterEntity>) parameterCrudRepository.findAll();
        return entities.stream().map(parameterMapper::toParameterDto).toList();
    }

    @Override
    public Optional<ParameterDto> findTopByKey(String key) {
        return parameterCrudRepository.findTopByKey(key).map(parameterMapper::toParameterDto);
    }

    @Override
    public ParameterDto save(ParameterDto parameterDto) {
        ParameterEntity entity = parameterMapper.toParameterEntity(parameterDto);
        return parameterMapper.toParameterDto(parameterCrudRepository.save(entity));
    }

}
