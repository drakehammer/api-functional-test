package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.users.RegisterUserInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCode;
import tasks.RegisterAsUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterUserStepDefinitions {

    private static final String restApiUrl = "https://reqres.in/api";
    Actor diego;

    @Given("Diego it's a client who wants to manage his products")
    public void diegoItSAClientWhoWantsToManageHisProducts() {
        diego = Actor.named("Diego the client")
                .whoCan(CallAnApi.at(restApiUrl));
    }

    @When("He send the login information")
    public void heSendTheLoginInformation() {
        RegisterUserInfo registerUserInfo = new RegisterUserInfo();

        registerUserInfo.setName("George");
        registerUserInfo.setJob("Bluth");
        registerUserInfo.setEmail("george.bluth@reqres.in");
        registerUserInfo.setPassword("serenity");

        diego.attemptsTo(
                RegisterAsUser.withInfo(registerUserInfo)
        );
    }

    @Then("He must get a account to login when required")
    public void heMustGetAAccountToLoginWhenRequired() {
        diego.should(
                seeThat("The response code is: ", ResponseCode.was(), equalTo(200))
        );
    }

}
