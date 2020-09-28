package gc.springframework.services;

import gc.springframework.commands.AbonentForm;
import gc.springframework.domain.Abonentlar;

import java.util.List;

public interface AbonentService {
    List<Abonentlar> listAll();

    Abonentlar getById(Long id);

    Abonentlar saveOrUpdate(Abonentlar abonentlar);

    void delete(Long id);

    void saveOrUpdateAbonentForm(AbonentForm abonentlar);

    Abonentlar findByInn(Long inn);
}
