package com.joaomeleski.peoplemanager.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "address")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_address", nullable = false)
    private Long id;

    @Size(min = 2, max = 2)
    @Column(name = "state", nullable = false)
    private String state;

    @Size(min = 2, max = 25)
    @Column(name = "city", nullable = false)
    private String city;

    @Size(min = 2, max = 30)
    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Size(min = 2, max = 30)
    @Column(name = "street", nullable = false)
    private String street;

    @Min(1)
    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "main_address", nullable = false)
    private boolean mainAddress;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id_person")
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
