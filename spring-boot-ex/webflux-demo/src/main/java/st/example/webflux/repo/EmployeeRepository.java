package st.example.webflux.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import st.example.webflux.model.Employee;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {

    @Query("{ 'name': ?0 }")
    Flux<Employee> findByName(String name);
}
