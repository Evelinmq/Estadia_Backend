package mx.edu.utez.JuventudxTemixco.service.AlianzaService;

import mx.edu.utez.JuventudxTemixco.Dto.alliesDTO.AliadoDTO;
import mx.edu.utez.JuventudxTemixco.models.Allies.AllyRepository;
import mx.edu.utez.JuventudxTemixco.models.Allies.BeanAlly;
import mx.edu.utez.JuventudxTemixco.models.users.BeanUser;

import java.io.IOException;
import java.util.List;

public class AlianzaService {

    public AllyRepository allyRepository;

    public AlianzaService(AllyRepository allyRepository) {
        this.allyRepository = allyRepository;
    }


    public BeanAlly createAlianza(AliadoDTO datos){

        BeanAlly bean = new BeanAlly();

        bean.setName(datos.getNombre());
        try {
            bean.setImage(datos.getImagen().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allyRepository.save(bean);

    }

    public BeanAlly editarAlianza(AliadoDTO datos, Long id){

        BeanAlly existente = allyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alianza no encontrada"));

        existente.setName(datos.getNombre());
        try {
            existente.setImage(datos.getImagen().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
}
