package com.example.app.service.user;

import com.example.app.domain.user.User;
import com.example.app.repository.user.UserRepository;
import com.example.app.service.dto.JoinRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    @Transactional
    public User join(JoinRequestDTO dto) {
        return userRepository.save(dto.toEntity());
    }
}
