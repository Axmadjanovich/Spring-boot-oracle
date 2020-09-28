package gc.springframework.converters;

import gc.springframework.commands.AbonentForm;
import gc.springframework.domain.Abonentlar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AbonentFormToAbonent implements Converter<AbonentForm, Abonentlar> {

    @Override
    public Abonentlar convert(AbonentForm abonentForm) {
        Abonentlar abonentlar = new Abonentlar();

        if (abonentForm.getId() != null && !StringUtils.isEmpty(abonentForm.getId())){
            abonentlar.setId(abonentForm.getId());
        }
        abonentlar.setIsmi(abonentForm.getIsmi());
        abonentlar.setFamiliyasi(abonentForm.getFamiliyasi());
        abonentlar.setOtasining_ismi(abonentForm.getOtasining_ismi());
        abonentlar.setInn(abonentForm.getInn());
        abonentlar.setUsername(abonentForm.getUsername());

        return abonentlar;
    }

}
