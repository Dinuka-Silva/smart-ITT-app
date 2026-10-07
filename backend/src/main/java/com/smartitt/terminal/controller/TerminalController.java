package com.smartitt.terminal.controller;

import com.smartitt.terminal.entity.Terminal;
import com.smartitt.terminal.service.TerminalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/terminals")
@RequiredArgsConstructor
@Tag(name = "Terminals", description = "Terminal management")
public class TerminalController {

    private final TerminalService terminalService;

    @GetMapping
    @Operation(summary = "Get all active terminals")
    public ResponseEntity<List<Terminal>> getTerminals() {
        return ResponseEntity.ok(terminalService.getActiveTerminals());
    }
}
