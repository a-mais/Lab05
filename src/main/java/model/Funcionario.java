package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends PessoaFisica {

    private int matricula;

    @ManyToOne
    @JoinColumn(name = "id_filial", nullable = false)
    private Filial filial;
}
