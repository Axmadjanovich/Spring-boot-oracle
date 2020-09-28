package gc.springframework.converters;

import gc.springframework.commands.AbonentForm;
import gc.springframework.domain.Abonentlar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AbonentToAbonentForm implements Converter<Abonentlar, AbonentForm> {
    @Override
    public AbonentForm convert(Abonentlar source) {
        AbonentForm abonentForm = new AbonentForm();
        abonentForm.setId(source.getId());
        abonentForm.setIsmi(source.getIsmi());
        abonentForm.setFamiliyasi(source.getFamiliyasi());
        abonentForm.setOtasining_ismi(source.getOtasining_ismi());
        abonentForm.setInn(source.getInn());
        abonentForm.setUsername(source.getUsername());
        return abonentForm;
    }
}
