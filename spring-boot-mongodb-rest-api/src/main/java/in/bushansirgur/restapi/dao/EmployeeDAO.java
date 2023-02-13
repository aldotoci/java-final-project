package in.bushansirgur.restapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import in.bushansirgur.restapi.model.NotesModel;

@Repository
public interface EmployeeDAO extends MongoRepository<NotesModel, Long> {

}
