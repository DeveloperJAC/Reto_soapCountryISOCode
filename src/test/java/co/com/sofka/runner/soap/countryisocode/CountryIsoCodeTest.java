package co.com.sofka.runner.soap.countryisocode;


import co.com.sofka.stepdefinitions.soap.countryisocode.SetUp;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.charset.StandardCharsets;


import static co.com.sofka.question.ReturnStringValue.systemValue;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SerenityRunner.class)
public class CountryIsoCodeTest extends SetUp {

    private static final String ADD_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\calculator\\add.xml";


    @Test
    public void testCountry(){
        String bodyRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.oorsprong.org/websamples.countryinfo\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:CountryISOCode>\n" +
                "         <web:sCountryName>Colombia</web:sCountryName>\n" +
                "      </web:CountryISOCode>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";



        Actor actor = Actor.named("Pais");
        actor.can(CallAnApi.at(URL_BASE));

        actor.attemptsTo(
                Post.to(RESOURCE)
                        .with(
                                req -> req.relaxedHTTPSValidation()
                                        .headers(headers())
                                        .body(bodyRequest)
                        )
        );
        String soapResponse = new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);

        actor.should(
                seeThatResponse(
                        "El codigo de respuesta HTTP debe ser:",
                        response -> response.statusCode(HttpStatus.SC_OK)
                ),
                seeThat(
                        "El resultado de la busqueda debe ser: ",
                        systemValue(soapResponse),
                        containsString("<m:CountryISOCodeResult>CO</m:CountryISOCodeResult>")
                )
        );

    }


}
