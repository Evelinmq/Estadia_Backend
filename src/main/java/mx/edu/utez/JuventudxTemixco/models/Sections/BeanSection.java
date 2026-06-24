package mx.edu.utez.JuventudxTemixco.models.Sections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.JuventudxTemixco.models.programs.BeanProgram;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="section")
public class BeanSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotEmpty
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    @JsonIgnore
    private byte[] image;

    @JsonManagedReference
    @OneToMany(mappedBy = "section")
    private List<BeanProgram> programs;

}
