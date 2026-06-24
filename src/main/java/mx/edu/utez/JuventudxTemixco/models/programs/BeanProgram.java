package mx.edu.utez.JuventudxTemixco.models.programs;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @Valid
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_seccion")
    private BeanSection section;

    @NotEmpty
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    @JsonIgnore
    private byte[] image;
}
