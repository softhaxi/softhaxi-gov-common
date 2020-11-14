package com.softhaxi.marves.core.service.account;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.directory.SearchControls;

import com.softhaxi.marves.core.domain.access.Role;
import com.softhaxi.marves.core.domain.access.UserRole;
import com.softhaxi.marves.core.domain.account.Profile;
import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.repository.access.RoleRepository;
import com.softhaxi.marves.core.repository.access.UserRoleRepository;
import com.softhaxi.marves.core.repository.account.ProfileRepository;
import com.softhaxi.marves.core.repository.account.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

/**
 * @author Andryan Situngkir
 * @since 1
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${app.ldap.base-dn}")
    private String baseDN;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProfileRepository profileRepo;

    // @Autowired
    // private EmployeeRepository employeeRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRoleRepository userRoleRepo;

    public Map<?, ?> retrieveUserLdapDetail(String username) {
        SearchControls sc = new SearchControls();
        sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
        sc.setTimeLimit(3000);
        sc.setCountLimit(1);
        // sc.setReturningAttributes(new String[]{"cn"});

        AttributesMapper<?> mapper = attributes -> 
            List.of(attributes.get("uid").get(), attributes.get("cn").get(), attributes.get("mail").get());

        String filter = "(|(uid=" + username.toLowerCase() + ")(mail="+ username.toLowerCase()  +"))";
        List<?> info = (List<?>) ldapTemplate.search(LdapUtils.emptyLdapName(), filter, sc, mapper).get(0);
        logger.info("[retrieveUserLdapDetail] Data..." + info.toString());
        
        // Name dn = null;
        // Map<?, ?> object = null;
        // AttributesMapper<?> mapper = attributes -> 
        //     Map.of("fullName",
        //         attributes.get("cn").get(), "email", attributes.get("mail").get());
        // try {
        //     dn = LdapNameBuilder.newInstance(baseDN)
        //         .add("uid", username)
        //         .build();
        //     object = (Map<?, ?>)ldapTemplate.lookup(dn, mapper);
        // } catch(Exception ex) {
        //     logger.error("[retrieveUserLdapDetail] Exception" + ex.getMessage(), ex);
        //     // try {
        //     //     dn = LdapNameBuilder.newInstance(baseDN)
        //     //         .add("ou", "otherpeople")
        //     //         .add("uid", username)
        //     //         .build();
        //     //     object = (Map<?, ?>) ldapTemplate.lookup(dn, mapper);
        //     // } catch(Exception ex1) {
        //     //     object = null;
        //     // }
        // }
        return Map.of("username", info.get(0), "fullName", info.get(1), "email", info.get(2));
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsernameOrEmailIgnoreCase(username.trim());
    }
    
    public Optional<User> findUserByUsernameAndPassword(String username, String password){
        return userRepo.findUserByUsernameAndPassword(username, password);
    }

    public User saveMobileUser(User user) {
        Profile profile = user.getProfile();
        //Employee employee = user.getEmployee();
        user.setProfile(null);
        user.setEmployee(null);
        user = userRepo.save(user);

        profile.setUser(user);
        profileRepo.save(profile);
        
        // employee.setUser(user);
        // employeeRepo.save(employee);
        
        Role role = roleRepo.findByName("MOBILE").orElse(null);
        if(role != null) {
            userRoleRepo.save(new UserRole(user, role));
        }

        return user;
    }
}