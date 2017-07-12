package com.facundoprecentado.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.facundoprecentado.entity.Task;
import com.facundoprecentado.repository.TaskRepository;

@Controller
@RequestMapping(path = "/")
public class TasksController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping(path = "/")
	public String getAllUsersView(Model model) {
		List<Task> tasks = new ArrayList<>();
		Task task = new Task();

		taskRepository.findAll().forEach(tasks::add);

		model.addAttribute("tasks", tasks);
		model.addAttribute("task", task);

		return "index";
	}

	@RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getTaskById(@PathVariable Long taskId, Model model) {
		Task task = taskRepository.findOne(taskId);
		model.addAttribute("task", task);
		return "task";
	}

	@RequestMapping(value = "/tasks/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addTask(Task task) {
		taskRepository.save(task);
		return "redirect:/";
	}

	@RequestMapping(value = "/tasks/update", method = RequestMethod.POST)
	public String updateTask(Task task) {
		Task updateTask = taskRepository.findOne(task.getId());
		updateTask.setTitle(task.getTitle());
		updateTask.setDescription(task.getDescription());
		taskRepository.save(updateTask);
		return "redirect:/";
	}

	@RequestMapping(value = "/tasks/delete/{taskId}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable Long taskId) {
		taskRepository.delete(taskId);
		return "redirect:/";
	}

}
