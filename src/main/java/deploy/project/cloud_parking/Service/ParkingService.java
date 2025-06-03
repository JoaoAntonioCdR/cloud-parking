package deploy.project.cloud_parking.Service;

import deploy.project.cloud_parking.Exception.ParkingNotFoundException;
import deploy.project.cloud_parking.Model.Parking;
import deploy.project.cloud_parking.Repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Parking findById(Long id) {
        return parkingRepository.findById(id)
                .orElseThrow(() -> new ParkingNotFoundException(id));
    }

    public Parking create(Parking dto) {
        return parkingRepository.save(dto);
    }

    public Parking update(Long id,Parking dto) {
        findById(id);
        return parkingRepository.save(dto);
    }

    public void delete(Long id) {
        Parking parking = findById(id);
        parkingRepository.delete(parking);
    }

    public Parking checkOut(Long id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        return parkingRepository.save(parking);
    }
}
