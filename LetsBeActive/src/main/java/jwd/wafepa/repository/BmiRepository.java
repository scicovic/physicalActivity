package jwd.wafepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Bmi;

@Repository
public interface BmiRepository extends JpaRepository<Bmi, Long> {

}
