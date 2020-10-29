package com.softhaxi.marves.core.service.account;

import java.util.Map;
import java.util.Optional;

import javax.naming.Name;

import com.softhaxi.marves.core.domain.access.Role;
import com.softhaxi.marves.core.domain.access.UserRole;
import com.softhaxi.marves.core.domain.account.Profile;
import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.employee.Employee;
import com.softhaxi.marves.core.repository.access.RoleRepository;
import com.softhaxi.marves.core.repository.access.UserRoleRepository;
import com.softhaxi.marves.core.repository.account.ProfileRepository;
import com.softhaxi.marves.core.repository.account.UserRepository;
import com.softhaxi.marves.core.repository.employee.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

/**
 * @author Andryan Situngkir
 * @since 1
 */
@Service
public class UserService {

    @Value("${spring.ldap.embedded.base-dn}")
    private String baseDN;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProfileRepository profileRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRoleRepository userRoleRepo;

    public Map<?, ?> retrieveUserLdapDetail(String username) {
        Name dn = null;
        Map<?, ?> object = null;
        AttributesMapper<?> mapper = attributes -> 
            Map.of("employeeNo", attributes.get("sn").get(), "fullName",
                attributes.get("cn").get(), "email", attributes.get("mail").get());
        try {
            dn = LdapNameBuilder.newInstance(baseDN)
                .add("ou", "people")
                .add("uid", username)
                .build();
            object = (Map<?, ?>)ldapTemplate.lookup(dn, mapper);
        } catch(Exception ex) {
            try {
                dn = LdapNameBuilder.newInstance(baseDN)
                    .add("ou", "otherpeople")
                    .add("uid", username)
                    .build();
                object = (Map<?, ?>) ldapTemplate.lookup(dn, mapper);
            } catch(Exception ex1) {
                object = null;
            }
        }
        return object;
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
    public Optional<User> findUserByUsernameAndPassword(String username, String password){
        return userRepo.findUserByUsernameAndPassword(username, password);
    }

    public User saveMobileUser(User user) {
        Profile profile = user.getProfile();
        Employee employee = user.getEmployee();
        user.setProfile(null);
        user.setEmployee(null);
        user = userRepo.save(user);

        profile.setUser(user);
        profileRepo.save(profile);
        
        employee.setUser(user);
        employeeRepo.save(employee);
        
        Role role = roleRepo.findByName("MOBILE").orElse(null);
        if(role != null) {
            userRoleRepo.save(new UserRole(user, role));
        }

        return user;
    }
}