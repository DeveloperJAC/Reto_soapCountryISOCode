package co.com.sofka.stepdefinitions.soap.countryisocode.country;

import co.com.sofka.stepdefinitions.soap.countryisocode.SetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static co.com.sofka.question.ReturnStringValue.systemValue;
import static co.com.sofka.tasks.countryIsoCode.DoPost.doPost;
import static co.com.sofka.util.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

public class CountryIsoCodeCucumberStepDefinition extends SetUp {

    private static final String ADD_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\countryisocode\\countryisocode.xml";
    private static final String ISO_COD = "[StringCod]";

    @Given("que el usuario de la plataforma consulte el codigo ISO")
    public void queElUsuarioDeLaPlataformaConsulteElCodigoISO() {
     setUp();

    }

    @When("que el usuario ingrese el nombre correctamente del pais {string}")
    public void queElUsuarioIngreseElNombreCorrectamenteDelPais(String cod) {
        bodyRequest = defineBodyRequest(cod);
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );
    }


    @Then("el usuario debera ver como resultado el codigo ISO solicitado {string}")
    public void elUsuarioDeberaVerComoResultadoElCodigoISOSolicitado(String iso) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El resultado de la busqueda debe ser : ",
                        systemValue(soapResponse(actor)),
                        containsString(" <m:CountryISOCodeResult>" + iso + "</m:CountryISOCodeResult>")


                )
        );
    }

    @When("que el usuario ingrese el nombre incorrectamente del pais {string}")
    public void queElUsuarioIngreseElNombreIncorrectamenteDelPais(String cod2) {
        bodyRequest = defineBodyRequest(cod2);
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );

    }

    @Then("el usuario debera ver como resultado {string}")
    public void elUsuarioDeberaVerComoResultado(String Nocountry) {

        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El resultado de la busqueda debe ser : ",
                        systemValue(soapResponse(actor)),
                        containsString(" <m:CountryISOCodeResult>" + Nocountry + "</m:CountryISOCodeResult>")


                )
        );

    }

    private String defineBodyRequest(String cod){
        return readFile(ADD_XML)
                .replace(ISO_COD, cod);
    }


}

