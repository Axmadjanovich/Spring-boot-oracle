package gc.springframework.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Data
@Entity
public class Abonentlar {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String ismi;
    private String familiyasi;
    private String otasining_ismi;
    private String username;
    private Long inn;

}
