package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCode;
import tasks.GetUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ConsultUserStepDefinitions {

    private static final String restApiUrl = "https://reqres.in/api";
    Actor diego;

    @Given("Diego as administrator wants to get the users from application")
    public void diegoAsAdministratorWantsToGetTheUsersFromApplication() {
        diego = Actor.named("Diego the admin")
                .whoCan(CallAnApi.at(restApiUrl));
    }

    @When("He send the consult information")
    public void heSendTheConsultInformation() {
        diego.attemptsTo(
                GetUsers.fromPage(1)
        );
    }

    @Then("He must get a list of users")
    public void heMustGetAListOfUsers() {
        diego.should(
                seeThat("The response code is: ", ResponseCode.was(), equalTo(200))
        );

    }

}
