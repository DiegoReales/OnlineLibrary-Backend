package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.repository.IActionLogRepository;
import co.edu.cuc.onlinelibrary.auth.domain.service.IActionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionLogService  implements IActionLogService {

    private final IActionLogRepository actionLogRepository;

    @Override
    public void save(ActionLogDto actionLogDto) {
        actionLogRepository.save(actionLogDto);
    }
}
