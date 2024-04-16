package co.edu.cuc.onlinelibrary.auth.domain.service;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PermissionDto;

import java.util.List;

public interface IPermissionService {
    List<PermissionDto> findAll();
    PermissionDto findById(int permissionId);
    PermissionDto save(PermissionDto permissionDto);
    void deletedById(int permissionId);
}
