package mx.edu.utez.JuventudxTemixco.Dto.Program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDTO {

    private Long sectionId;
    private MultipartFile image;

}
