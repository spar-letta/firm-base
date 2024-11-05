package auth.repository;

import auth.entity.Counter;
import auth.entity.dataType.CounterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounterRepository extends JpaRepository<Counter, Long> {

    Optional<Counter> findByCounterType(CounterType counterType);
}
