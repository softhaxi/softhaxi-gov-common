package com.softhaxi.marves.core.repository.attendence;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.attendence.Attendence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendenceRepository extends JpaRepository<Attendence, UUID> {
    public Optional<Attendence> getByUserAndId(User user, UUID id);
}
