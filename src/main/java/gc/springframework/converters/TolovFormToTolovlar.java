package gc.springframework.converters;

import gc.springframework.commands.TolovForm;
import gc.springframework.domain.Abonentlar;
import gc.springframework.domain.Tolovlar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TolovFormToTolovlar implements Converter<TolovForm, Tolovlar> {

    @Override
    public Tolovlar convert(TolovForm tolovForm) {
        Tolovlar tolov = new Tolovlar();
        if (tolovForm.getId() != null  && !StringUtils.isEmpty(tolovForm.getId())) {
            tolov.setId(new Long(tolovForm.getId()));
        }
        tolov.setTuri(tolovForm.getTuri());
        tolov.setNarxi(tolovForm.getNarxi());
        Abonentlar abonentlar = new Abonentlar();
        abonentlar.setInn(tolovForm.getInn());
        tolov.setAbonentlar(abonentlar);
        return tolov;
    }
}
