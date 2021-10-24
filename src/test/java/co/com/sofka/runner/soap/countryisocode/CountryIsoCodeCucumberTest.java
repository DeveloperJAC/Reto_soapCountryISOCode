package co.com.sofka.runner.soap.countryisocode;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/countryisocode/countryisocode.feature"},
        glue = {"co.com.sofka.stepdefinitions.soap.countryisocode.country"}
)

public class CountryIsoCodeCucumberTest {
}
