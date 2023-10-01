package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Residents")
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_id")
    private int id;
    @Column(name = "car_access")
    private boolean carAccess;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "flat_id")
    private Flat flatId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member memberId;

    public int getId() {
        return id;
    }

    public boolean isCarAccess() {
        return carAccess;
    }

    public Flat getFlatId() {
        return flatId;
    }

    public Member getMemberId() {
        return memberId;
    }
}
