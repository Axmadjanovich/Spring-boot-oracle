package gc.springframework.services;

import gc.springframework.commands.AbonentForm;
import gc.springframework.converters.AbonentFormToAbonent;
import gc.springframework.domain.Abonentlar;
import gc.springframework.repositories.AbonentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbonentServiceImple implements AbonentService{


    private final AbonentRepository abonentRepository;
    private final AbonentFormToAbonent abonentFormToAbonent;

    @Autowired
    public AbonentServiceImple(AbonentRepository abonentRepository, AbonentFormToAbonent abonentFormToAbonent) {
        this.abonentRepository = abonentRepository;
        this.abonentFormToAbonent = abonentFormToAbonent;
    }


    @Override
    public List<Abonentlar> listAll() {
        List <Abonentlar> list = new ArrayList<>();
        abonentRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Abonentlar getById(Long id) {
        return abonentRepository.findOne(id);
    }

    @Override
    public Abonentlar saveOrUpdate(Abonentlar abonentlar) {
        return abonentRepository.save(abonentlar);
    }

    @Override
    public void delete(Long id) {
        abonentRepository.delete(id);
    }
    @Override
    public void saveOrUpdateAbonentForm(AbonentForm abonentlar) {
        saveOrUpdate(abonentFormToAbonent.convert(abonentlar));
    }

    @Override
    public Abonentlar findByInn(Long inn) {
        return abonentRepository.findAbonentlarByInn(inn).get();
    }
}
