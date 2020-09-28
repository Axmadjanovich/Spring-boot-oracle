package gc.springframework.converters;

import gc.springframework.commands.TolovForm;
import gc.springframework.domain.Tolovlar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TolovToTolovForm implements Converter<Tolovlar, TolovForm> {
    @Override
    public TolovForm convert(Tolovlar tolov) {
        TolovForm tolovForm = new TolovForm();
        tolovForm.setId(tolov.getId());
        tolovForm.setTuri(tolov.getTuri());
        tolovForm.setNarxi(tolov.getNarxi());
        tolovForm.setInn(tolov.getAbonentlar().getInn());
        return tolovForm;
    }
}
