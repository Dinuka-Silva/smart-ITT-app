package com.smartitt.terminal.service;

import com.smartitt.terminal.entity.Terminal;
import com.smartitt.terminal.repository.TerminalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerminalService {

    private final TerminalRepository terminalRepository;

    public List<Terminal> getActiveTerminals() {
        return terminalRepository.findAll().stream()
                .filter(Terminal::isActive)
                .toList();
    }
}
