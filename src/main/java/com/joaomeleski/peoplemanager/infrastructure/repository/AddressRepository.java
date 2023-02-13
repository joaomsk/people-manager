package com.joaomeleski.peoplemanager.infrastructure.repository;

import com.joaomeleski.peoplemanager.domain.entity.Address;
import com.joaomeleski.peoplemanager.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Transactional
    @Modifying
    @Query("update Address a set a.person = :person where a.id in :id")
    void updatePersonAddressById(@Param("person") Person person, @Param("id") Set<Long> id);

    @Query("select count(a) from Address a where a.person.id = :personId and a.mainAddress = true")
    Integer countMainAddressesByPersonId(long personId);
}