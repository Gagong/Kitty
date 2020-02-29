package Utils;

import Debug.Debug;
import Variables.Variables;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;

public class GetJDA {

    private JDA jda;
    private Variables Variables = new Variables();

    public void buildJDA() {
        EventListener EventListener = new EventListener();
        try {
            try {
                jda = new JDABuilder(Variables.getBotKey())
                        .addEventListeners(EventListener)
                        .setActivity(Activity.playing("Online!"))
                        .build()
                        .awaitReady();
            } catch (LoginException e) {
                e.printStackTrace();
            }
            Debug.p("API", "JDA", "Finished Building JDA!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JDA getJDA() {
        return jda;
    }
}
