package mx.edu.utez.JuventudxTemixco.controller.Section;

import mx.edu.utez.JuventudxTemixco.service.Sections.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/section")
@CrossOrigin("*")
public class SectionController {

    private final SectionService service;

    public SectionController(SectionService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save (
            @RequestParam("archivo")MultipartFile archivo,
            @RequestParam("name") String name,
            @RequestParam("description")String description
            ) throws IOException {
        return new ResponseEntity<>(service.save(archivo, name, description), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("description") String description,
            @RequestParam(value = "name") String name
    ) throws IOException {
        return new ResponseEntity<>(service.update(id, archivo, description, name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> list(){
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/byName")
    public ResponseEntity<?> listByName(@RequestParam String name) {
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
