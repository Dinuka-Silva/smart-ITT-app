package com.smartitt.dashboard.controller;

import com.smartitt.trip.entity.TripStatus;
import com.smartitt.trip.repository.TripRepository;
import com.smartitt.user.repository.UserRepository;
import com.smartitt.driver.repository.DriverRepository;
import com.smartitt.user.entity.AccountStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Supervisor dashboard statistics")
public class DashboardController {

    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;

    @GetMapping
    @Operation(summary = "Get supervisor dashboard statistics")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalDrivers", driverRepository.count());
        stats.put("activeDrivers", driverRepository.findByStatus(AccountStatus.ACTIVE).size());
        stats.put("totalTripsToday", tripRepository.countTodayTrips(startOfDay, endOfDay));
        stats.put("tripsInProgress", tripRepository.countByStatus(TripStatus.IN_PROGRESS));
        stats.put("completedTrips", tripRepository.countByStatus(TripStatus.COMPLETED));
        stats.put("pendingApprovals", tripRepository.countByStatus(TripStatus.PENDING_APPROVAL));
        stats.put("approvedTrips", tripRepository.countByStatus(TripStatus.APPROVED));
        stats.put("rejectedTrips", tripRepository.countByStatus(TripStatus.REJECTED));

        // Terminal-wise stats for all 6 terminals
        Map<String, Long> terminalStats = new LinkedHashMap<>();
        for (String t : new String[]{"CICT", "CWIT", "ECT", "JCT", "UCT", "SAGT"}) {
            terminalStats.put(t, tripRepository.countBySourceTerminalToday(t, startOfDay, endOfDay));
        }
        stats.put("terminalWiseTrips", terminalStats);

        return ResponseEntity.ok(stats);
    }
}
