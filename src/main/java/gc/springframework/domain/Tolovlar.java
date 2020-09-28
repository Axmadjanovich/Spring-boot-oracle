package gc.springframework.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Tolovlar {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String turi;
    private Long narxi;
    @OneToOne
    private Abonentlar abonentlar;

}
