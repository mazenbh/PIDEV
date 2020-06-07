// Install the Java helper library from twilio.com/docs/libraries/java
package utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC480f2c7c33af34d12995e5d44a974ae5";
    public static final String AUTH_TOKEN =
            "dd1d845163ec9a627c03436e13c3ddb4";

    public void sendsms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21654043829"), // to
                        new PhoneNumber("+13346058266"), // from
                        "Bien Inscrit au évenement").create();

        System.out.println(message.getSid());
    }
}