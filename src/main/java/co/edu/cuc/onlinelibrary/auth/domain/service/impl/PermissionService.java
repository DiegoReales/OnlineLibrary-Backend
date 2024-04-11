package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.PermissionDto;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpNotFoundException;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IPermissionRepository;
import co.edu.cuc.onlinelibrary.auth.domain.service.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {

    private final IPermissionRepository permissionRepository;


    @Override
    public List<PermissionDto> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public PermissionDto findById(int permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new HttpNotFoundException("Permiso no encontrado"));
    }

    @Override
    public PermissionDto save(PermissionDto permissionDto) {
        return permissionRepository.save(permissionDto);
    }

    @Override
    public void deletedById(int permissionId) {
        permissionRepository.deletedById(permissionId);
    }
}
