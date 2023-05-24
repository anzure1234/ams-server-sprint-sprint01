package ams.repository;

import ams.model.entity.Trainee;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:mr.doantheson@gmail.com"> SonDT21
 * <p>
 * This interface represents the repository for Trainee entity.
 */

@Repository
public interface TraineeRepository extends BaseRepository<Trainee, Long> {
}
