package gc.springframework.repositories;

import gc.springframework.domain.Tolovlar;
import org.springframework.data.repository.CrudRepository;

public interface TolovRepository extends CrudRepository<Tolovlar, Long> {
}
