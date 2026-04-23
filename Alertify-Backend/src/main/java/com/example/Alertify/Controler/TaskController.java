package com.example.Alertify.Controler;

import com.example.Alertify.Dto.TaskDTO;
import com.example.Alertify.Entity.Task;
import com.example.Alertify.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping("/createTask")
    public ResponseEntity<Task> create(@RequestBody TaskDTO dto) {
        return ResponseEntity.ok(service.createTask(dto));
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Task>> getAll(@RequestParam(required = false) String email) {
        if (email != null) {
            return ResponseEntity.ok(service.getTasksByEmail(email));
        }
        return ResponseEntity.ok(service.getTasksByEmail(email));
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(service.updateTask(id, dto));
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}