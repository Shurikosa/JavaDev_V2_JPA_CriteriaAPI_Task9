package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flat_id")
    private int id;

    @Column(name = "flat_number")
    private int number;

    @Column(name = "area_m2")
    private int area;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id")
    private Building buildingId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Building getBuildingId() {
        return buildingId;
    }
}
