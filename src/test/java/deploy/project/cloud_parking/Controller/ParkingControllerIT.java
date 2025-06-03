package deploy.project.cloud_parking.Controller;

import deploy.project.cloud_parking.Controller.DTO.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void whenfindAllThemCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .extract().response().body().prettyPrint();
    }

    @Test
    void whenCreateParkingCheckResult() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("Prata");
        createDTO.setLicense("JVC-3305");
        createDTO.setModel("Gol");
        createDTO.setState("GO");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(201)
                .extract().response().body().prettyPrint();
    }
}