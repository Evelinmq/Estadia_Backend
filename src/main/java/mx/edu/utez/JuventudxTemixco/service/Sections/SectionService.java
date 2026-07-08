package mx.edu.utez.JuventudxTemixco.service.Sections;

import mx.edu.utez.JuventudxTemixco.models.Sections.BeanSection;
import mx.edu.utez.JuventudxTemixco.models.Sections.SectionRepository;
import mx.edu.utez.JuventudxTemixco.models.programs.BeanProgram;
import mx.edu.utez.JuventudxTemixco.models.programs.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final ProgramRepository programRepository;

    public SectionService(SectionRepository sectionRepository, ProgramRepository programRepository) {
        this.sectionRepository = sectionRepository;
        this.programRepository = programRepository;
    }

    @Transactional
    public BeanSection save(MultipartFile file, String name, String descripcion) throws IOException {
        BeanSection beanSection = new BeanSection();

        beanSection.setImage(file.getBytes());
        beanSection.setName(name);
        beanSection.setDescription(descripcion);

        return sectionRepository.save(beanSection);
    }

    @Transactional
    public BeanSection update(Long id, MultipartFile file, String descripcion, String name) throws IOException {
        BeanSection beanSection = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found with ID: " + id));

        if (file != null && !file.isEmpty()) {
            beanSection.setImage(file.getBytes());
        }
        beanSection.setDescription(descripcion);

        if (name != null) {
            beanSection.setName(name);
        }

        return sectionRepository.save(beanSection);
    }

    @Transactional(readOnly = true)
    public List<BeanSection> list() {
        return sectionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<BeanSection> findById(Long id) {
        return sectionRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<BeanSection> findByName(String name) {
        if (name == null || name.isEmpty()) {
            return sectionRepository.findAll();
        }

        return sectionRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public void delete(Long id) {
        if (!sectionRepository.existsById(id)) {
            throw new RuntimeException("No es posible eliminar. Sección no encontrada");
        }

        if (programRepository.existsBySectionId(id)) {
            throw new IllegalStateException("No se puede eliminar: la sección tiene programas asociados");
        }


        sectionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public byte[] getImagenSeccion(Long id) {
        BeanSection section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        if (section.getImage() == null) {
            throw new RuntimeException("La sección no cuenta con una imagen guardada");
        }

        return section.getImage();
    }

    @Transactional(readOnly = true)
    public List<BeanProgram> getProgramasPorSeccion(Long id) {
        BeanSection section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        return section.getPrograms();
    }

    @Transactional(readOnly = true)
    public byte[] getImagenPrograma(Long id) {
        BeanProgram program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        if (program.getImage() == null) {
            throw new RuntimeException("El programa no cuenta con una imagen guardada");
        }

        return program.getImage();
    }

}
