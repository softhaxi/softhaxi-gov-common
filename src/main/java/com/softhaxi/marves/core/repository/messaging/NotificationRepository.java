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
        "WHERE A.id IN " + 
        "(SELECT A.id FROM Notification " +
            "LEFT JOIN NotificationStatus B ON B.message.id=A.id " + 
            "WHERE A.level='PUBLIC' AND (B.user IS NULL OR B.user = ?1) AND (B.delivered IS NULL OR B.delivered = false) " +
            "AND (A.dateTime >= ?2 AND A.dateTime < ?3)) " +
        "OR A.id IN " + 
        "(SELECT A.id FROM Notification " +
            "JOIN NotificationStatus B ON B.message.id=A.id " + 
            "WHERE A.level<>'PUBLIC' AND B.user = ?1 AND (B.delivered IS NULL OR B.delivered = false) " +
            "AND (A.dateTime >= ?2 AND A.dateTime < ?3)) " +
        "ORDER BY A.dateTime ASC")
    public Collection<Notification> findAllUndeliveredByUser(User user, ZonedDateTime startTime, ZonedDateTime endDate);

    @Query("SELECT A FROM Notification A " + 
        "WHERE A.id IN " + 
        "(SELECT A.id FROM Notification " +
            "LEFT JOIN NotificationStatus B ON B.message.id=A.id " + 
            "WHERE A.level='PUBLIC'AND (B.user IS NULL OR B.user = ?1))" +
        "OR A.id IN " + 
        "(SELECT A.id FROM Notification " +
            "JOIN NotificationStatus B ON B.message.id=A.id " + 
            "WHERE A.level<>'PUBLIC' AND B.user = ?1)" +
        "ORDER BY A.dateTime DESC")
    public Collection<Notification> findAllByUser(User user);

    @Query("SELECT A " + 
        "FROM Notification A " + 
        "LEFT JOIN NotificationStatus B ON B.message.id=A.id " + 
        "WHERE A.id = ?1 " +
        "AND (B.user IS NULL OR B.user = ?2)")
    public Optional<Notification> findOneByIdAndUser(UUID id, User user);
}
