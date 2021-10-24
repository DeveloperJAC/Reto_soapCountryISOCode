package co.com.sofka.stepdefinitions.soap.countryisocode;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SetUp {

    protected static final String URL_BASE = "http://webservices.oorsprong.org";
    protected static final String RESOURCE = "/websamples.countryinfo/CountryInfoService.wso";
    protected String bodyRequest ;
    protected Actor actor;

    private void setUpActor(){
        actor = Actor.named("Pais");
    }

    private void setUpActorAndApi(){
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected void setUp(){
        setUpActor();
        setUpActorAndApi();
    }

    protected HashMap<String, Object> headers(){
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "text/xml;charset=UTF-8");
        headers.put("SOAPAction", "");
        return headers;
    }

    protected String soapResponse(Actor actor){
        return  new String(
                LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8
        );
    }

}
