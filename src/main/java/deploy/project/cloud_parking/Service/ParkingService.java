package deploy.project.cloud_parking.Service;

import deploy.project.cloud_parking.Exception.ParkingNotFoundException;
import deploy.project.cloud_parking.Model.Parking;
import deploy.project.cloud_parking.Repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking findById(String id) {

        Parking parking = parkingRepository.findById(id).get();
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking dto) {
        return parkingRepository.save(dto);
    }

    public Parking update(String id,Parking dto) {
        findById(id);
        return parkingRepository.save(dto);
    }

    public void delete(String id) {
        Parking parking = findById(id);
        parkingRepository.delete(parking);
    }
}
