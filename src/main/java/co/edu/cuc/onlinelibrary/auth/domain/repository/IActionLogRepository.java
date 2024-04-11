package co.edu.cuc.onlinelibrary.auth.domain.repository;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;

public interface IActionLogRepository {
    void save(ActionLogDto actionLogDto);
}
