package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "veiculo")
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numeroPlaca;

    @ManyToOne
    @JoinColumn(name = "id_filial", nullable = false)
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "id_tipo_veiculo", nullable = false)
    private TipoVeiculo tipoVeiculo;
}
