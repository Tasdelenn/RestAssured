import POJO.Location;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    @Test
    public void test() {

        given()
                     // hazırlık işlemlerini yapacağız (token,send body, parametreler)

                .when()
                     // link i ve metodu veriyoruz

                .then()
                     //  assertion ve extract ile verileri ele alma
        ;

    }


    @Test
    public void statusCodeTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()         // log.all() bütün response u gösterir
                .statusCode(200)    // status kontrolü
        ;

    }


    @Test
    public void contentTypeTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()                   // log.all() bütün response u gösterir
                .statusCode(200)             // status kontrolü
                .contentType(ContentType.JSON)  // hatalı durum kontrolünü yapalım
        ;
    }

    @Test
    public void checkStateInResponseBody() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .body("country", equalTo("United States")) // body.country == United States ?
                .statusCode(200)
        ;
    }

    /**
        body.country  -> body("country"),
        body.'post code' -> body("post code"),
        body.'country abbreviation' -> body("country abbreviation"),
        body.places[0].'place name' ->  body( "body.places[0].'place name'"),
        body.places[0].state -> body("places[0].state")
     */

    @Test
    public void bodyJsonPathTest2() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .body("places[0].state", equalTo("California")) // birebir eşit mi
                .statusCode(200)
        ;
    }

    @Test
    public void bodyJsonPathTest3() {

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .log().body()
                .body("places.'place name'", hasItem("Çaputçu Köyü"))
                //  bir index verilmezse dizinin bütün elemanlarında arar
                //  "places.'place name'"  bu bilgilerin içinde "Çaputçu Köyü" adlı bir item var mı?
                .statusCode(200)
        ;
    }

    @Test
    public void bodyArrayHasSizeTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")
                //.get("http://api.zippopotam.us/us/90210") // bunun liste uzunluğu da 1 mesela...


                .then()
                .log().body()
                .body("places", hasSize(71)) // Verilen path deki listin size kontrolü
                .statusCode(200)
        ;
    }

    @Test
    public void combiningTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .body("places", hasSize(1)) // verilen path deki listin size kontrolü
                .body("places.state", hasItem("California"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))
                .statusCode(200)
        ;
    }

    @Test
    public void pathParamTest() {

        given()
                .pathParam("Country","us")
                .pathParam("ZipKod",90210)
                .log().uri() //request linki
                // Request URI (http://api.zippopotam.us/us/90210)

                .when()
                .get("http://api.zippopotam.us/{Country}/{ZipKod}")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test
    public void pathParamTest2() {

       /** 90220 dan 90224 e kadar test sonuçlarında places in size nın hepsinde 1 gediğini test ediniz.  */

        for(int i=90220 ;i <=90224 ;i++ ) {
            given()
                    .pathParam("Country", "us")
                    .pathParam("ZipKod", i)
                    .log().uri()

                    .when()
                    .get("http://api.zippopotam.us/{Country}/{ZipKod}")

                    .then()
                    .log().body()
                    .body("places", hasSize(1))
                    .statusCode(200)
            ;
        }
    }


    // JSON EXTRACT
    @Test
    public void extractingJsonPath() {

        String placeName =   // Değer return eder hale geldiği için, türüne göre bi değişkene atıyoruz.
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                //.log().body()
                .statusCode(200)
                .extract().path("places[0].'place name'")
                // extract metodu vasıtasıyla given ile başlayan satır,
                // bir değer döndürür hale geldi !
                // (EN SONDA EXTRACT OLMALI !)
        ;

        System.out.println("placeName = " + placeName);
    }

    @Test
    public void extractingJsonPOJO() {  // POJO : JSon Object i  (Plain Old Java Object)

        Location yer=
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .extract().as(Location.class) // Location sınıfı (şablonu)
        ;

        System.out.println("yer. = " + yer);

        System.out.println("yer.getCountry() = " + yer.getCountry());
        System.out.println("yer.getPlaces().get(0).getPlacename() = " +
                  yer.getPlaces().get(0).getPlacename());
    }




}

//    "post code": "90210",
//            "country": "United States",
//            "country abbreviation": "US",
//
//            "places": [
//            {
//            "place name": "Beverly Hills",
//            "longitude": "-118.4065",
//            "state": "California",
//            "state abbreviation": "CA",
//            "latitude": "34.0901"
//            }
//            ]
//
//class Location{
//    String postcode;
//    String country;
//    String countryabbreviation;
//    ArrayList<Place> places
//}
//
//class Place{
//    String placename;
//    String longitude;
//    String state;
//    String stateabbreviation
//    String latitude;
//}
