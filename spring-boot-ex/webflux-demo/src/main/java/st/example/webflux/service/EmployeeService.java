package st.example.webflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import st.example.webflux.model.Employee;
import st.example.webflux.repo.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

    EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public void create(Employee e) {
        employeeRepo.save(e).subscribe();
    }

    public Mono<Employee> findById(Integer id) {
        return employeeRepo.findById(id);
    }

    public Flux<Employee> findByName(String name) {
        return employeeRepo.findByName(name);
    }

    public Flux<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Mono<Employee> update(Employee e) {
        return employeeRepo.save(e);
    }

    public Mono<Void> delete(Integer id) {
        return employeeRepo.deleteById(id);
    }
}
