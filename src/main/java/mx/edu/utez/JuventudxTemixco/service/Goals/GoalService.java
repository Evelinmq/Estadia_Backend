package mx.edu.utez.JuventudxTemixco.service.Goals;

import mx.edu.utez.JuventudxTemixco.controller.Goal.dto.UpdateDescriptionDTO;
import mx.edu.utez.JuventudxTemixco.models.Goals.BeanGoal;
import mx.edu.utez.JuventudxTemixco.models.Goals.GoalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoalService {

    private GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Transactional
    public BeanGoal updateDescription(Long id, UpdateDescriptionDTO dto){
        BeanGoal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        goal.setDescription(dto.getDescription());

        return goalRepository.save(goal);
    }

    @Transactional(readOnly = true)
    public List<BeanGoal> list(){
        return goalRepository.findAll();
    }
}
