package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "OSBB_members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role roleId;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRoleId() {
        return roleId;
    }
}
