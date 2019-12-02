package st.example.boot.parser.repositiry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import st.example.boot.parser.model.Record;

@Repository
public interface RecordRepository extends CrudRepository<Record, Integer> {

}