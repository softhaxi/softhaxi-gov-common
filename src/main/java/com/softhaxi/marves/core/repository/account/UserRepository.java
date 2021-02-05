package com.softhaxi.marves.core.repository.account;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Andryan Situngkir
 * @since 1
 */
public interface UserRepository extends JpaRepository<User, UUID>{

    public Optional<User> findByUsername(String username);

    @Query("FROM User WHERE lower(username) = lower(?1) or lower(email)=lower(?1)")
    public Optional<User> findByUsernameOrEmailIgnoreCase(String username);
    
    @Query("FROM User WHERE username =?1 and password=?2")
    public Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Query("FROM User u left join UserRole ur on u.id = ur.user.id left join Role r on r.id = ur.role.id where r.name='MOBILE'")
    public Collection<User> findAllNonAdminUsers();
    
    //@Query("FROM User u left join UserRole ur on u.id = ur.user.id left join Role r on r.id = ur.role.id where r.name='MOBILE' and u.username like ?1%")
    @Query("FROM User u left join UserRole ur on u.id = ur.user.id "+
    "left join Role r on r.id = ur.role.id"+
    " left join Profile p on p.user.id=u.id "+
    " where r.name='MOBILE' and lower(p.fullName) like lower(concat(?1,'%'))")
    public List<User> findUserByUsernameLike(String username);

    @Query("FROM User a JOIN Profile b ON b.user.id=a.id " +
    "LEFT JOIN UserRole c ON c.user.id=a.id " +
    "WHERE c.role.name='MOBILE' AND a.status='ACTIVE' " +
    "ORDER BY a.email ASC")
    public Collection<User> findAllActiveMobileUser();

    @Query("FROM User WHERE lower(email)=lower(?1)")
    public Optional<User> findUserByEmail(String email);
}