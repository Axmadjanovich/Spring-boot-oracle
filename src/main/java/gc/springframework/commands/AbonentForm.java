package gc.springframework.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbonentForm {
    private Long id;
    @NotEmpty(message = "Ismn kiritilmagan")
    private String ismi;
    @NotEmpty(message = "Familiya kiritilmagan")
    private String familiyasi;
    @NotEmpty(message = "Otasining ismi kiritilmagan")
    private String otasining_ismi;
    @NotEmpty(message = "Username kiritilmagan")
    private String username;
    @NotNull(message = "INN kiritilmagan")
    @Min(value = 100000000, message = "INN noto'g'ri kiritilgan") //9 xonali sonlarni qabul qilish uchun
    @Max(value = 999999999, message = "INN noto'g'ri kiritilgan")
    private Long inn;
}
