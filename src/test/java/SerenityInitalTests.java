import models.users.Datum;
import models.users.RegisterUserInfo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.GetUsers;
import tasks.RegisterUser;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityInitalTests {

    private static final String restApiUrl = "https://reqres.in/api";

    @Test
    public void getUsers(){
        Actor diego = Actor.named("Diego the user")
                .whoCan(CallAnApi.at(restApiUrl));

        diego.attemptsTo(
                GetUsers.fromPage(1)
        );
        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);

        diego.should(
                seeThat("The response code is: ", ResponseCode.was(), equalTo(200))
        );

        Datum user = new GetUsersQuestion().answeredBy(diego)
                .getData().stream().filter(x -> x.getId() == 1).findFirst().orElse(null);

        diego.should(
                seeThat("User is not null", act -> user, notNullValue())
        );

        diego.should(
                seeThat("User email", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("User avatar", act -> user.getAvatar(), equalTo("https://reqres.in/img/faces/1-image.jpg"))
        );

    }

    @Test
    public void registerUsers(){
        Actor diego = Actor.named("Diego the user")
                .whoCan(CallAnApi.at(restApiUrl));

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();

        registerUserInfo.setName("George");
        registerUserInfo.setJob("Bluth");
        registerUserInfo.setEmail("george.bluth@reqres.in");
        registerUserInfo.setPassword("serenity");

        diego.attemptsTo(
                RegisterUser.withInfo(registerUserInfo)
        );

        diego.should(
                seeThat("The response code is: ", ResponseCode.was(), equalTo(200))
        );

    }



}
