package deploy.project.cloud_parking.Controller;

import deploy.project.cloud_parking.Controller.DTO.ParkingCreateDTO;
import deploy.project.cloud_parking.Controller.DTO.ParkingDTO;
import deploy.project.cloud_parking.Controller.Mapper.ParkingMapper;
import deploy.project.cloud_parking.Model.Parking;
import deploy.project.cloud_parking.Service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @Operation(summary = "Find All", description = "Lista todos os Estacionamentos")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Parking by ID", description = "Encontra um Parking pelo ID")
    public ResponseEntity<ParkingDTO> findById(@PathVariable Long id){
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Create Parking", description = "Cria um Parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parkingCreateDTO){
        var parkingCreate = parkingMapper.createDTOToParking(parkingCreateDTO);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Parking", description = "Altera os dados de um Parking pelo ID.")
    public ResponseEntity<ParkingDTO> update(@PathVariable Long id,@RequestBody ParkingCreateDTO parkingCreateDTO){
        var parkingCreate = parkingMapper.createDTOToParking(parkingCreateDTO);
        var parking = parkingService.update(id,parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Parking", description = "Deleta um Parking por ID.")
    public ResponseEntity delete(@PathVariable Long id){
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    @Operation(summary = "Check Out Parking", description = "Calcula as horas e retorna o valor a ser pago")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable Long id){
        Parking parking = parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

}
