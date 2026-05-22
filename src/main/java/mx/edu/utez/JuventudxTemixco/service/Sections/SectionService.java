package mx.edu.utez.JuventudxTemixco.service.Sections;

import mx.edu.utez.JuventudxTemixco.models.Sections.BeanSection;
import mx.edu.utez.JuventudxTemixco.models.Sections.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public BeanSection save(MultipartFile file, String descripcion) throws IOException {
        BeanSection beanSection = new BeanSection();

        beanSection.setImage(file.getBytes());
        beanSection.setDescription(descripcion);

        return sectionRepository.save(beanSection);
    }

    @Transactional(readOnly = true)
    public List<BeanSection> list(){
        return sectionRepository.findAll();
    }
}
