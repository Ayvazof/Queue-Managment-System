package arm.ayvazoff.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
@ApiModel(description = "Details about user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonView(View.Id.class)
    @ApiModelProperty(notes = "The uniq id of user")
    private String id;

    @NotBlank(message = "user password cannot be empty")
    @JsonView(View.IdNameSurnameLoginPassword.class)
    private String password;

    @NotBlank(message = "user name cannot be empty")
    @JsonView(View.IdName.class)
    private String name;

    @JsonView(View.IdNameSurname.class)
    private String surname;

    @JsonView(View.IdNameSurnameLogin.class)
    @NotBlank(message = "user login cannot be empty")
    private String login;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Role of the user (ADMIN, USER)")
    private Set<Role> roles;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
         name = "user_office",
         joinColumns = { @JoinColumn(name = "user_id") },
         inverseJoinColumns = { @JoinColumn(name = "office_id") }
     )
    @ApiModelProperty(notes = "List of user's offices")
    private List<Office> offices;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public String getId() {
        return id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public void setAuthorities(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void attachOffice(Office office) {
        offices.add(office);
    }

    public void detachOffice(Office office) {
        offices.remove(office);
    }
}
