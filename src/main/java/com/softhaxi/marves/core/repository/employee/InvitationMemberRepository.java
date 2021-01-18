package com.softhaxi.marves.core.repository.employee;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.employee.InvitationMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvitationMemberRepository extends JpaRepository<InvitationMember, UUID> {
    @Query("FROM InvitationMember WHERE invitation.id = ?1")
    public Collection<InvitationMember> getByInvitationId(UUID id);

    @Query("FROM InvitationMember WHERE user = ?1 AND invitation.id = ?2")
    public Optional<InvitationMember> getByUserAndInvitationId(User user, UUID id);
}
