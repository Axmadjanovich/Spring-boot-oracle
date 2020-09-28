package gc.springframework.repositories;

import gc.springframework.domain.Abonentlar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbonentRepository extends CrudRepository<Abonentlar, Long> {
    Optional<Abonentlar> findAbonentlarByInn(Long inn);
}
