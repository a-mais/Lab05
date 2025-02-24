package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends PessoaFisica{

    private String contato;

    private Boolean ativo;
}
