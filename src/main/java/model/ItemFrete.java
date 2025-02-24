package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "item_frete")
@AllArgsConstructor
@NoArgsConstructor
public class ItemFrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_frete", nullable = false)
    private Frete frete;

    private String descricao;

    private Float peso;
}
