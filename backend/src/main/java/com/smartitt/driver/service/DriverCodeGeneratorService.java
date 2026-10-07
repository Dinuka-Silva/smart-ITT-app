package com.smartitt.driver.service;

import com.smartitt.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverCodeGeneratorService {

    private final DriverRepository driverRepository;
    private static final Pattern DRV_PATTERN = Pattern.compile("DRV-(\\d+)");

    /**
     * Generates a unique, non-duplicated driver code in format DRV-00001, DRV-00002...
     * Synchronized to guarantee concurrent safety within the application node.
     */
    @Transactional(readOnly = true)
    public synchronized String generateNextDriverCode() {
        Optional<String> maxCodeOpt = driverRepository.findMaxDriverCode();
        long nextNum = 1;

        if (maxCodeOpt.isPresent()) {
            String maxCode = maxCodeOpt.get();
            Matcher matcher = DRV_PATTERN.matcher(maxCode);
            if (matcher.find()) {
                try {
                    nextNum = Long.parseLong(matcher.group(1)) + 1;
                } catch (NumberFormatException e) {
                    log.warn("Could not parse driver number from code: {}", maxCode);
                    nextNum = driverRepository.count() + 1;
                }
            } else {
                nextNum = driverRepository.count() + 1;
            }
        } else {
            long count = driverRepository.count();
            nextNum = count + 1;
        }

        String candidateCode = String.format("DRV-%05d", nextNum);

        // Safety fallback check against collisions
        while (driverRepository.existsByDriverCode(candidateCode)) {
            nextNum++;
            candidateCode = String.format("DRV-%05d", nextNum);
        }

        log.info("Generated unique Driver Code: {}", candidateCode);
        return candidateCode;
    }
}
