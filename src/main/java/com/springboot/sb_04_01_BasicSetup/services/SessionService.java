package com.springboot.sb_04_01_BasicSetup.services;

import com.springboot.sb_04_01_BasicSetup.entities.SessionEntity;
import com.springboot.sb_04_01_BasicSetup.entities.UserEntity;
import com.springboot.sb_04_01_BasicSetup.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void genarateNewSession(UserEntity user, String refreshToken) {
        List<SessionEntity> userSessions = sessionRepository.findByUser(user);

        // Delete oldest session if the session limit exceeds
        if (userSessions.size() >= SESSION_LIMIT) {
            userSessions.sort(Comparator.comparing(SessionEntity::getLastUsedAt));

            SessionEntity lastSession = userSessions.getFirst();
            sessionRepository.delete(lastSession);
        }

        // Store the new session
        SessionEntity newSession = SessionEntity.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {
        // Optional<SessionEntity> validSession = sessionRepository.findByRefreshToken(refreshToken);

        SessionEntity validSession = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session not found for provided refreshToken."));

        validSession.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(validSession);
    }
}
