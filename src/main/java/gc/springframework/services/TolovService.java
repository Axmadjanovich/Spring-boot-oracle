package gc.springframework.services;

import gc.springframework.commands.TolovForm;
import gc.springframework.domain.Tolovlar;

import java.util.List;

public interface TolovService {

    List<Tolovlar> listAll();

    Tolovlar getById(Long id);

    Tolovlar saveOrUpdate(Tolovlar tolovlar);

    void delete(Long id);

    Tolovlar saveOrUpdateTolovForm(TolovForm tolovForm);
}
