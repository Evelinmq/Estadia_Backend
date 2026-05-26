package mx.edu.utez.JuventudxTemixco.service.Programs;

import mx.edu.utez.JuventudxTemixco.models.Sections.BeanSection;
import mx.edu.utez.JuventudxTemixco.models.Sections.SectionRepository;
import mx.edu.utez.JuventudxTemixco.models.programs.BeanProgram;
import mx.edu.utez.JuventudxTemixco.models.programs.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private SectionRepository sectionRepository;

    public ProgramService(ProgramRepository programRepository, SectionRepository sectionRepository) {
        this.programRepository = programRepository;
        this.sectionRepository = sectionRepository;
    }

    @Transactional
    public BeanProgram save (Long section_id, MultipartFile image) throws IOException {

        BeanSection section = sectionRepository.findById(section_id).
                orElseThrow(() -> new RuntimeException("Section not found"));

        BeanProgram program = new BeanProgram();

        program.setSection(section);
        program.setImage(image.getBytes());

        return programRepository.save(program);
    }

    @Transactional
    public BeanProgram update(Long id, Long section_id, MultipartFile file) throws IOException {
        BeanProgram beanProgram = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found with ID: " + id));

        if (file != null && !file.isEmpty()) {
            beanProgram.setImage(file.getBytes());
        }

        if (section_id != null ) {
            BeanSection beanSection = sectionRepository.findById(section_id)
                    .orElseThrow(() -> new RuntimeException("Section not found with ID: " + section_id));
            beanProgram.setSection(beanSection);
        }

        return programRepository.save(beanProgram);
    }

    @Transactional(readOnly = true)
    public List<BeanProgram> list() {
        return programRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<BeanProgram> listBySection(Long sectionId) {
        return programRepository.findBySectionId(sectionId);
    }

    @Transactional
    public void delete(Long id) {
        if (!programRepository.existsById(id)) {
            throw new RuntimeException("It is not possible to delete. Program not found.");
        }
        programRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BeanProgram> searchBySectionName(String name) {
        if (name == null || name.isEmpty()) {
            return programRepository.findAll();
        }

        return programRepository.findBySectionName(name);
    }
}
