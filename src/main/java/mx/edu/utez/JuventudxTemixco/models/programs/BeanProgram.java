package mx.edu.utez.JuventudxTemixco.models.programs;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.JuventudxTemixco.models.Sections.BeanSection;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "program")
public class BeanProgram {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_seccion")
    private BeanSection id_Section;

    @Column(columnDefinition = "LONGTEXT")
    private String image;
}
