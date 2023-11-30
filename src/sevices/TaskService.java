package sevices;

import dtos.TaskDTO;
import enums.TaskStatus;
import repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {
    public void createTask(TaskDTO taskDTO) {
        taskDTO.setStatus(TaskStatus.ACTIVE);
        taskDTO.setCreated_date(LocalDateTime.now());
        boolean result = TaskRepository.create(taskDTO);
        if (result) {
            System.out.println("Success task added");
        }

    }

    public void activeTaskList() {
        List<TaskDTO> dtoList = TaskRepository.getAll(TaskStatus.ACTIVE);
        for (TaskDTO dto: dtoList) {
            System.out.println(dto);
        }

    }

    public void search(String title){
        List<TaskDTO> dtoList = TaskRepository.search(title.toLowerCase());
        for (TaskDTO dto:dtoList) {
            System.out.println(dto);
        }
    }

    public Boolean update(TaskDTO taskDTO, int id) {
        return TaskRepository.update(taskDTO,id);

    }

    public Boolean delete(int id) {
        return TaskRepository.delete(id);
    }


    public Boolean mark(int id) {
        return TaskRepository.mark(id);
    }
}
