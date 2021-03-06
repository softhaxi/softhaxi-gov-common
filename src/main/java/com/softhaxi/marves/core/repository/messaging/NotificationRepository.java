package com.softhaxi.marves.core.repository.messaging;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.messaging.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    @Query("SELECT A FROM Notification A " +
        "WHERE A.id IN (" +
        "SELECT A.id FROM Notification A " +
            "LEFT JOIN NotificationStatus B ON B.message.id=A.id AND B.user IS NULL " +
            "WHERE A.level='PUBLIC' " +
            "AND (A.dateTime >= ?2 AND A.dateTime < ?3)) " +
        "OR A.id IN (" +
        "SELECT A.id FROM Notification A "+
            "JOIN NotificationStatus B ON B.message.id=A.id " +
            "WHERE B.user = ?1 AND (B.delivered IS NULL OR B.delivered = false) " +
            "AND (A.dateTime >= ?2 AND A.dateTime < ?3)) " +
        "ORDER BY A.dateTime ASC")
    public Collection<Notification> findAllUndeliveredByUser(User user, ZonedDateTime startTime, ZonedDateTime endDate);

    @Query("SELECT A FROM Notification A " +
        "WHERE A.id IN (" +
        "SELECT A.id FROM Notification A " +
            "LEFT JOIN NotificationStatus B ON B.message.id=A.id " +
            "WHERE A.level='PUBLIC') " +
        "OR A.id IN (" +
        "SELECT A.id FROM Notification A "+
            "JOIN NotificationStatus B ON B.message.id=A.id " +
            "WHERE B.user = ?1) " +
        "ORDER BY A.dateTime DESC")
    public Collection<Notification> findAllByUser(User user);

    @Query("SELECT A FROM Notification A " +
        "WHERE A.id IN (" +
        "SELECT A.id FROM Notification A " +
            "LEFT JOIN NotificationStatus B ON B.message.id=A.id " +
            "WHERE A.level='PUBLIC' " +
            "AND (A.dateTime >= ?2 AND A.dateTime < ?3)) " +
        "OR A.id IN (" +
        "SELECT A.id FROM Notification A "+
            "JOIN NotificationStatus B ON B.message.id=A.id " +
            "WHERE B.user = ?1 " +
            "AND (A.dateTime >= ?2 AND A.dateTime < ?3)) " +
        "ORDER BY A.dateTime DESC")
    public Collection<Notification> findAllByUserAndDateRange(User user, ZonedDateTime from, ZonedDateTime to);

    @Query("SELECT A " + 
        "FROM Notification A " + 
        "LEFT JOIN NotificationStatus B ON B.message.id=A.id " + 
        "WHERE A.id = ?1 " +
        "AND (B.user IS NULL OR B.user = ?2)")
    public Optional<Notification> findOneByIdAndUser(UUID id, User user);

    @Query("FROM Notification WHERE user = ?1 AND referenceId = ?2")
    public Optional<Notification> findOneByUserAndReferenceId(User user, String referenceId);

    @Query("FROM Notification WHERE source = ?1 AND dateTime >= ?2 AND dateTime <= ?3 ORDER BY dateTime DESC")
    public Collection<Notification> findAllBySourceAndDateRange(String source, ZonedDateTime start, ZonedDateTime end);
}
