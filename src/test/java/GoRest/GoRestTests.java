package GoRest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestTests {

    @Test
    public void queryParamTest() {
        //https://gorest.co.in/public/v1/users?page=1

        given()
                .param("page",1)
                .log().uri() //request linki

                .when()
                .get("https://gorest.co.in/public/v1/users")

                .then()
                .log().body()
                .body("meta.pagination.page", equalTo(1) )
                .statusCode(200)
        ;
    }

    @Test
    public void queryParamTest2() {
        //https://gorest.co.in/public/v1/users?page=X

        for (int pageNo = 1; pageNo <= 10; pageNo++) {
            given()
                    .param("page", pageNo)
                    .log().uri() //request linki

                    .when()
                    .get("https://gorest.co.in/public/v1/users")

                    .then()
                    .log().body()
                    .body("meta.pagination.page", equalTo(pageNo))
                    .statusCode(200)
            ;
        }
    }


    @Test
    public void extractingJsonPathInt() {

        int limit =     // Değer return eder hale geldiği için, türüne göre bi değişkene atıyoruz.
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("meta.pagination.limit");
                        // extract metodu vasıtasıyla given ile başlayan satır,
                        // bir değer döndürür hale geldi !
                        // (EN SONDA EXTRACT OLMALI !)
        ;
        System.out.println("limit = " + limit);
        Assert.assertEquals(limit,10,"test sonucu");
    }

    @Test
    public void extractingJsonPathInt2() {

        int id=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("data[2].id");
        ;
        System.out.println("id = " + id);
    }

    @Test
    public void extractingJsonPathIntList() {

        List<Integer> idler =    // Extract 'la elde edeceğimiz türün ne olduğu önemli. (Atayabilmek için)
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("data.id") // Data daki bütün idleri bir List şeklinde verir
                ;

        System.out.println("idler = " + idler);
        Assert.assertTrue(idler.contains(4057));
    }

    @Test
    public void extractingJsonPathStringList() {

        List<String> isimler=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("data.name") // data daki bütün idleri bir List şeklinde verir
                ;

        System.out.println("isimler = " + isimler);
        Assert.assertTrue(isimler.contains("Gajabahu Adiga"));
    }

    @Test
    public void extractingJsonPathResponsAll() {

        Response response=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().response() // bütün body alındı

                ;


        List<Integer> idler = response.path("data.id");
        List<String> isimler = response.path("data.name");
        int limit = response.path("meta.pagination.limit");

        System.out.println("limit = " + limit);
        System.out.println("isimler = " + isimler);
        System.out.println("idler = " + idler);
    }



    RequestSpecification requestSpecs;
    ResponseSpecification responseSpecs;

    @BeforeClass
    void Setup(){

        baseURI="https://gorest.co.in/public/v1";
        // RestAssured'un kendi statik değişkenine (baseURI) tanımlı değer atanıyor.
        // "    https://gorest.co.in/public/v1/         users?page=1    "

        requestSpecs = new RequestSpecBuilder()
                .log(LogDetail.URI)
                .setAccept(ContentType.JSON)
                .build();

        responseSpecs = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();
    }

    @Test
    public void requestResponseSpecification() {

        //  https://gorest.co.in/public/v1/users?page=1
        //  ( baseURI/users?page=1 )

        given()
                .param("page",1)
                .spec(requestSpecs)

                .when()
                .get("/users")  // URL nin başında http yoksa bile baseURI deki değer otomatik geliyor.

                .then()
                .body("meta.pagination.page", equalTo(1) )
                                    // jsonpathfinder.com 'dan faydalanıldı...
                .spec(responseSpecs)
        ;
    }
}
