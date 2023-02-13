package in.bushansirgur.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bushansirgur.restapi.dao.EmployeeDAO;
import in.bushansirgur.restapi.model.NotesModel;
import in.bushansirgur.restapi.service.SequenceGeneratorService;

@RestController
@RequestMapping("/api")
public class NotesController {
	@Autowired
	EmployeeDAO employeeDAO;
	@Autowired
	SequenceGeneratorService seqGeneratorService;
	@PostMapping("/create")
	public NotesModel create(@RequestBody NotesModel newEmployeeObject) {
		newEmployeeObject.setId(seqGeneratorService.generateSequence(NotesModel.SEQUENCE_NAME));
		return employeeDAO.save(newEmployeeObject);
	}
	@GetMapping("/read")
	public List<NotesModel> read(){
		return employeeDAO.findAll();
	}
	
	@GetMapping("/read/{id}")
	public NotesModel read(@PathVariable Long id) {
		Optional<NotesModel> employeeObj = employeeDAO.findById(id);
		if(employeeObj.isPresent()) {
			return employeeObj.get();
		}else {
			throw new RuntimeException("Employee not found with id "+id);
		}
	}
	@PutMapping("/update")
	public NotesModel update(@RequestBody NotesModel modifiedEmployeeObject) {
		return employeeDAO.save(modifiedEmployeeObject);
	}
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Optional<NotesModel> employeeObj = employeeDAO.findById(id);
		if(employeeObj.isPresent()) {
			employeeDAO.delete(employeeObj.get());
			return "Employee deleted with id "+id;
		}else {
			throw new RuntimeException("Employee not found for id "+id);
		}
	}
	
}
