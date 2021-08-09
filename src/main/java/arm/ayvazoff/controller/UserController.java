package arm.ayvazoff.controller;

import arm.ayvazoff.domain.Office;
import arm.ayvazoff.domain.User;
import arm.ayvazoff.domain.View;
import arm.ayvazoff.repository.OfficeRepository;
import arm.ayvazoff.repository.UserRepository;
import arm.ayvazoff.service.UserService;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("{id}")
    public User getById(@PathVariable("id") User user) {
        return user;
    }

    @GetMapping("office/{id}")
    @ApiOperation(value = "Get offices attached to user",
            notes = "Provide user id to get user offices",
            response = User.class)
    public List<Office> getOffices(@PathVariable("id") User user) {
        List<Office> offices = user.getOffices();
        return user.getOffices();
    }

    @PostMapping
    public void add(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @PutMapping("/office")
    @ApiOperation(value = "Attach office to user",
            notes = "Provide user and office id's to attach office to user",
            response = User.class)
    public void attachOffice(@RequestParam("userId") String userId,
                             @RequestParam("officeId") String officeId) {

        User user = userRepository.getOne(userId);
        user.attachOffice(officeRepository.getOne(officeId));
        userRepository.save(user);
    }

    @DeleteMapping("/office")
    @ApiOperation(value = "Detach office from user",
            notes = "Provide user and office id's to detach offices from user",
            response = User.class)
    public void detachOffice(@RequestParam("userId") String userId,
                             @RequestParam("officeId") String officeId) {
        User user = userRepository.getOne(userId);
        user.detachOffice(officeRepository.getOne(officeId));
        userRepository.save(user);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") User userFromDb, @RequestBody User user) {
        BeanUtils.copyProperties(user, userFromDb, "id");
        userRepository.save(userFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepository.delete(user);
    }

    @GetMapping
    @JsonView(View.IdNameSurnameLoginPassword.class)
    public List<User> usersList() {
        return userService.findAll();
    }
}
