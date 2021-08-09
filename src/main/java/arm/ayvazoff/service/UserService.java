package arm.ayvazoff.service;

import arm.ayvazoff.domain.Role;
import arm.ayvazoff.domain.User;
import arm.ayvazoff.domain.View;
import arm.ayvazoff.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);

        if(user == null)
            throw new UsernameNotFoundException("User name not found");

        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByName(user.getUsername());

        if(userFromDb == null)
            return false;

        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(user.getPassword());

        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
