package mx.edu.utez.JuventudxTemixco.service.Goals;

import mx.edu.utez.JuventudxTemixco.Dto.Goal.UpdateDescriptionDTO;
import mx.edu.utez.JuventudxTemixco.models.Goals.BeanGoal;
import mx.edu.utez.JuventudxTemixco.models.Goals.GoalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    private GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Transactional
    public BeanGoal updateDescription(@PathVariable Long id, @RequestBody UpdateDescriptionDTO dto){
        BeanGoal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        goal.setDescription(dto.getDescription());

        return goalRepository.save(goal);
    }

    @Transactional(readOnly = true)
    public List<BeanGoal> list(){
        return goalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BeanGoal findByName(String name) {
        Optional<BeanGoal> goal = goalRepository.findByName(name);

        if (goal.isEmpty()) {
            throw new RuntimeException("No se encontró el objetivo con nombre: " + name);
        }

        return goal.orElse(null);
    }
}
