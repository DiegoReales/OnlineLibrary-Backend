package co.edu.cuc.onlinelibrary.auth.persistence;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PermissionDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IPermissionRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.crudrepository.PermissionCrudRepository;
import co.edu.cuc.onlinelibrary.auth.persistence.entity.PermissionEntity;
import co.edu.cuc.onlinelibrary.auth.persistence.mapper.PermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PermissionRepository implements IPermissionRepository {
    private final PermissionCrudRepository permissionCrudRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public List<PermissionDto> findAll() {
        List<PermissionEntity> entities = (List<PermissionEntity>) permissionCrudRepository.findAll();
        return permissionMapper.toListPermissionDto(entities);
    }

    @Override
    public Optional<PermissionDto> findById(int permissionId) {
        return permissionCrudRepository.findById(permissionId)
                .map(permissionMapper::toPermissionDto);
    }

    @Override
    public PermissionDto save(PermissionDto permissionDto) {
        PermissionEntity entity = permissionMapper.toPermissionEntity(permissionDto);
        return permissionMapper.toPermissionDto(permissionCrudRepository.save(entity));
    }

    @Override
    public void deletedById(int permissionId) {
        permissionCrudRepository.deleteById(permissionId);
    }
}
