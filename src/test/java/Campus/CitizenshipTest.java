package Campus;

import Campus.Model.Citizenship;
import Campus.Model.Country;
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
import static org.hamcrest.Matchers.equalTo;

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
    Citizenship citizenship = new Citizenship();


    @Test
    public void createCitizenship() {

        citizenshipName = getRandomName();
        citizenshipShortName = getRandomShortName();

//      Citizenship citizenship = new Citizenship();
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

    @Test(dependsOnMethods = "createCitizenship")
    public void createCitizenshipNegative()
    {
        //  "message":"The Citizenship with Name \"German\" already exists."

//      Citizenship citizenship=new Citizenship();
        citizenship.setName(citizenshipName);
        citizenship.setShortName(citizenshipShortName);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(citizenship)

                .when()
                .post("school-service/api/citizenships")

                .then()
                .log().body()
                .statusCode(400)
                .body("message",equalTo("The Citizenship with Name \""+citizenshipName+"\" already exists."))
        ;
        System.out.println("Could not recreate the citizenship with same name");
    }


    @Test(dependsOnMethods = "createCitizenship")
    public void updateCitizenship()
    {
        citizenshipName = getRandomName();

//      Citizenship citizenship=new Citizenship();
        citizenship.setId(citizenshipID);
        citizenship.setName(citizenshipName);
        citizenship.setShortName(citizenshipShortName);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(citizenship)

                .when()
                .put("school-service/api/citizenships")

                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo(citizenshipName))
        ;
        System.out.println("the citizenship updated successfully");
    }

    @Test(dependsOnMethods = "updateCitizenship")
//  @Test(dependsOnMethods = "createCitizenship")
    public void deleteCitizenshipById()
    {
        given()
                .cookies(cookies)
                .pathParam("citizenshipID", citizenshipID)

                .when()
                .delete("school-service/api/citizenships/{citizenshipID}")

                .then()
                .log().body()
                .statusCode(200)
        ;
        System.out.println("the citizenship just deleted successfully");
    }

    @Test(dependsOnMethods = "deleteCitizenshipById")
    public void deleteCitizenshipByIdNegative()
    {
        given()
                .cookies(cookies)
                .pathParam("citizenshipID", citizenshipID)
                .log().uri()
                .when()
                .delete("school-service/api/citizenships/{citizenshipID}")

                .then()
                .log().body()
                .statusCode(400)
        ;
    }



}
