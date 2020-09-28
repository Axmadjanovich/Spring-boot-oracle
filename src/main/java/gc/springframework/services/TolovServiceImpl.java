package gc.springframework.services;

import gc.springframework.commands.TolovForm;
import gc.springframework.converters.TolovFormToTolovlar;
import gc.springframework.domain.Abonentlar;
import gc.springframework.domain.Tolovlar;
import gc.springframework.repositories.AbonentRepository;
import gc.springframework.repositories.TolovRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TolovServiceImpl implements TolovService {

    private final TolovRepository tolovRepository;
    private final TolovFormToTolovlar tolovFormToTolovlar;
    private final AbonentRepository abonentRepository;

    @Autowired
    public TolovServiceImpl(TolovRepository productRepository,
                            TolovFormToTolovlar productFormToProduct,
                            AbonentRepository abonentRepository) {
        this.tolovRepository = productRepository;
        this.tolovFormToTolovlar = productFormToProduct;
        this.abonentRepository = abonentRepository;
    }


    @Override
    public List<Tolovlar> listAll() {
        List<Tolovlar> tolovlar = new ArrayList<>();
        tolovRepository.findAll().forEach(tolovlar::add);
        return tolovlar;
    }

    @Override
    public Tolovlar getById(Long id) {
        return tolovRepository.findOne(id);
    }

    @Override
    public Tolovlar saveOrUpdate(Tolovlar tolov) {
        Abonentlar abonentlar = abonentRepository.findAbonentlarByInn(tolov.getAbonentlar().getInn()).get();
        tolov.setAbonentlar(abonentlar);
        tolovRepository.save(tolov);
        return tolov;
    }

    @Override
    public void delete(Long id) {
        tolovRepository.delete(id);
    }

    @Override
    public Tolovlar saveOrUpdateTolovForm(TolovForm tolovForm) {

        Tolovlar tolovlar = saveOrUpdate(tolovFormToTolovlar.convert(tolovForm));

        System.out.println("Saved Product Id: " + tolovlar.getId());
        return tolovlar;
    }
}
