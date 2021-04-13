package ptua.ex2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptua.ex2.Model.Car;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findByCarId(Long carId);
    public List<Car> findAll();
}
