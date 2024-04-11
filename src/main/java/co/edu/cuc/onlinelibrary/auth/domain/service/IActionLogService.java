package co.edu.cuc.onlinelibrary.auth.domain.service;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;

public interface IActionLogService {
    void save(ActionLogDto actionLogDto);
}
