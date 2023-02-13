package com.joaomeleski.peoplemanager.infrastructure.repository;

import com.joaomeleski.peoplemanager.domain.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Transactional
    @Modifying
    @Query("""
            update Person p set p.firstName = :firstName, p.lastName = :lastName, p.cpf = :cpf, p.birthDate = :birthDate
            where p.id = :id""")
    void updatePersonById(@NonNull @Param("firstName") String firstName, @NonNull @Param("lastName") String lastName, @NonNull @Param("cpf") String cpf, @NonNull @Param("birthDate") LocalDate birthDate, @Param("id") Long id);

    @Query("select p from Person p where p.id = :id")
    Optional<Person> getPersonById(@Param("id") Long id);

    @Query("select p from Person p order by p.id")
    Page<Person> getPageablePersons(Pageable pageable);

}