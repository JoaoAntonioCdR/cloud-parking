package deploy.project.cloud_parking.Repository;

import deploy.project.cloud_parking.Model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,Long> {
}
