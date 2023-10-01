package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "OSBB_memebers_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Roles roles;

    public int getRoleId() {
        return roleId;
    }

    public Roles getRoles() {
        return roles;
    }

}
