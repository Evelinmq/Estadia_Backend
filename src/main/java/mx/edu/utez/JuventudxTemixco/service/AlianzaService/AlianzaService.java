package mx.edu.utez.JuventudxTemixco.service.AlianzaService;

import mx.edu.utez.JuventudxTemixco.Dto.alliesDTO.AliadoDTO;
import mx.edu.utez.JuventudxTemixco.models.Allies.AllyRepository;
import mx.edu.utez.JuventudxTemixco.models.Allies.BeanAlly;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AlianzaService {

    public AllyRepository allyRepository;

    public AlianzaService(AllyRepository allyRepository) {
        this.allyRepository = allyRepository;
    }


    public BeanAlly createAlianza(AliadoDTO datos){

        BeanAlly bean = new BeanAlly();

        bean.setName(datos.getNombre());
        if (datos.getImagen() != null && !datos.getImagen().isEmpty()) {
            try {
                byte[] imagenEnBytes = java.util.Base64.getMimeDecoder().decode(datos.getImagen().trim());
                bean.setImage(imagenEnBytes);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al decodificar la foto del afiliado: " + e.getMessage());
                bean.setImage(null);
            }
        }


        return allyRepository.save(bean);

    }

    public BeanAlly editarAlianza(AliadoDTO datos, Long id){

        BeanAlly existente = allyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alianza no encontrada"));

        existente.setName(datos.getNombre());
        if (datos.getImagen() != null && !datos.getImagen().isEmpty()) {
            try {
                byte[] imagenEnBytes = java.util.Base64.getMimeDecoder().decode(datos.getImagen().trim());
                existente.setImage(imagenEnBytes);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al decodificar la foto del afiliado: " + e.getMessage());
                existente.setImage(null);
            }
        }


        return allyRepository.save(existente);
    }

    public void eliminarAlianza(Long id) {
        if (!allyRepository.existsById(id)) {
            throw new RuntimeException("La alianza que intentas eliminar no existe");
        }
        allyRepository.deleteById(id);
    }


    public List<BeanAlly> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return allyRepository.findAll();
        }

        return allyRepository.nombreAlianza(nombre);
    }

    public List<BeanAlly> listaAlianzas() {
        return allyRepository.findAll();

    }
}
