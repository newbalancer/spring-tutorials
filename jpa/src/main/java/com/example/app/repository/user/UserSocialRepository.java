package com.example.app.repository.user;

import com.example.app.domain.user.UserSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSocialRepository extends JpaRepository<UserSocial, Long> {
}
