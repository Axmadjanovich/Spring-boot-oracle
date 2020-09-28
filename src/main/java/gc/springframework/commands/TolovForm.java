package gc.springframework.commands;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class TolovForm {
    private Long id;
    @NotEmpty(message = "To'lov turini kiriting")
    private String turi;
    @NotNull(message = "To'lov narxini kiriting")
    private Long narxi;
    @NotNull(message = "Jismoniy shaxsning INN sini kiriting")
    private Long inn;
    }
