package com.softhaxi.marves.core.repository.employee;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.employee.Invitation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvitationRepository extends JpaRepository<Invitation, UUID> {

    @Query("FROM Invitation WHERE deleted=false")
    public Collection<Invitation> findAllNotDeleted();

    @Query("FROM Invitation " +
        "WHERE startDate <= ?1 AND endDate >= ?1 " +
        "AND deleted=false")
    public Collection<Invitation> findAllDaily(LocalDate date);

    @Query("FROM Invitation " +
        "WHERE startDate <= ?1 AND endDate >= ?1 " +
        "AND function('TO_CHAR', startTime, 'hh:mi')=  ?2 " +
        "AND deleted=false")
    public Collection<Invitation> findAllDailyInvitationByTime(LocalDate date, String time);

    @Query("SELECT a FROM Invitation a JOIN InvitationMember b ON b.invitation.id=a.id WHERE b.user = ?1 AND a.id = ?2 AND a.deleted=false AND b.deleted=false")
    public Optional<Invitation> findByUserAndId(User user, UUID id);

    @Query("SELECT DISTINCT a FROM Invitation a " +
        " JOIN InvitationMember b ON b.invitation.id=a.id " +
        " WHERE b.user = ?1 AND a.startDate <= ?2 AND a.endDate >= ?2 AND a.deleted=false AND b.deleted=false")
    public Collection<Invitation> findAllUserDailyInvitation(User user, LocalDate date);

    @Query("SELECT DISTINCT a FROM Invitation a " +
       // " JOIN InvitationMember b ON b.invitation.id=a.id " +
        " where a.createdBy = ?1 AND a.deleted=false")
    public Collection<Invitation> findAllUserDailyInvitationByCreated(String userId, LocalDate date);
    // public default Collection<Invitation> findAllUserDailyInvitation(User user, LocalDate actionDate) {
    //     return findAllByUserAndDate(user, 
    //         actionDate.atStartOfDay(ZoneId.systemDefault()), 
    //         actionDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()));
    // }

    // @Query("SELECT a FROM Invitation a " +
    //     " JOIN InvitationMember b ON b.invitation.id=a.id " +
    //     " WHERE b.user = ?1 AND a.startTime >= ?2 AND a.startTime <= ?3")
    // Collection<Invitation> findAllByUserAndDate(User user, ZonedDateTime startTime, ZonedDateTime endTime);

    @Query("SELECT DISTINCT a FROM Invitation a " +
    // " JOIN InvitationMember b ON b.invitation.id=a.id " +
     " where a.createdBy = ?1 AND a.category IN ?2 AND a.deleted=false")
    public Collection<Invitation> findAllUserDailyInvitationByCategory(String userId, List<String> category, LocalDate date);
}
