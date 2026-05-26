package mx.edu.utez.JuventudxTemixco.controller.Program;

import mx.edu.utez.JuventudxTemixco.models.programs.BeanProgram;
import mx.edu.utez.JuventudxTemixco.service.Programs.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/program")
@CrossOrigin("*")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save (
            @RequestParam("section_id") Long section_id,
            @RequestParam("archivo")MultipartFile archivo
    ) throws IOException {
        return new ResponseEntity<>(programService.save(section_id, archivo), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update (
            @PathVariable Long id,
            @RequestParam (value = "section_id", required = false) Long section_id,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo
    ) throws IOException {
        return new ResponseEntity<>(programService.update(id, section_id, archivo), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(programService.list(), HttpStatus.OK);
    }

    @GetMapping("/bySection/{idSection}")
    public ResponseEntity<?> listBySection( @PathVariable Long idSection) {
        return new ResponseEntity<>(programService.listBySection(idSection), HttpStatus.OK);
    }

    @GetMapping("/bySectionName")
    public ResponseEntity<?> listBySectionName(
            @RequestParam(value = "sectionName", required = true) String sectionName
    ) {
        return new ResponseEntity<>(programService.searchBySectionName(sectionName), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id ) {
        programService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
