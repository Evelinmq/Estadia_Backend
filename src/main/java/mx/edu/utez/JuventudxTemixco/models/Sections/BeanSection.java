package mx.edu.utez.JuventudxTemixco.models.Sections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

    private String name;

    private String description;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    @JsonIgnore
    private byte[] image;

    @JsonManagedReference
    @OneToMany(mappedBy = "section")
    private List<BeanProgram> programs;

}
