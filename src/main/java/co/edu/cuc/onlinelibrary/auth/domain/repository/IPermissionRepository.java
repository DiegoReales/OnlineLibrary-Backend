package co.edu.cuc.onlinelibrary.auth.domain.repository;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PermissionDto;

import java.util.List;
import java.util.Optional;

public interface IPermissionRepository {
    List<PermissionDto> findAll();
    Optional<PermissionDto> findById(int permissionId);
    PermissionDto save(PermissionDto permissionDto);
    void deletedById(int permissionId);
}
