package Campus;

import Campus.Model.Citizenship;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static Campus.Model.RandomGenerator.getRandomName;
import static Campus.Model.RandomGenerator.getRandomShortName;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CitizenshipTest {

    Cookies cookies = null;

    @BeforeClass
    public void loginCampus() {
        baseURI = "https://demo.mersys.io/";

        Map<String, String> credential = new HashMap<>();
        credential.put("username", "richfield.edu");
        credential.put("password", "Richfield2020!");
        credential.put("rememberMe", "true");

        cookies =
                given()
                        .contentType(ContentType.JSON)
                        .body(credential)

                        .when()
                        .post("auth/login")

                        .then()
                        //.log().cookies()
                        .statusCode(200)
                        .extract().response().getDetailedCookies()
        ;
        System.out.println("Login Succesfull");
    }

    String citizenshipID;
    String citizenshipName;
    String citizenshipShortName;


    @Test
    public void createCitizenship() {

        citizenshipName = getRandomName();
        citizenshipShortName = getRandomShortName();

        Citizenship citizenship = new Citizenship();
        citizenship.setName(citizenshipName);
        citizenship.setShortName(citizenshipShortName);

        citizenshipID =
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(citizenship)

                .when()

                .post("school-service/api/citizenships")

                .then()
                .log().body()
                .statusCode(201)
                .extract().jsonPath().getString("id")
        ;
        System.out.println("created the citizenship");

    }


    }
