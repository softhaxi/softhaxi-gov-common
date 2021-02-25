package com.softhaxi.marves.core.repository.employee;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.employee.Invitation;
import com.softhaxi.marves.core.domain.employee.InvitationMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvitationMemberRepository extends JpaRepository<InvitationMember, UUID> {
    @Query("FROM InvitationMember WHERE invitation.id = ?1 AND deleted=false")
    public Collection<InvitationMember> findByInvitationId(UUID id);

    @Query("SELECT a FROM User a JOIN InvitationMember b ON b.user.id= a.id WHERE b.invitation = ?1 AND b.deleted=false")
    public Collection<User> findAllUserByInvitation(Invitation invitation);

    @Query("FROM InvitationMember WHERE user = ?1 AND invitation.id = ?2 and deleted=false")
    public Optional<InvitationMember> findByUserAndInvitationId(User user, UUID id);

    public Optional<InvitationMember> findByUserAndInvitation(User user, Invitation invitation);
}
