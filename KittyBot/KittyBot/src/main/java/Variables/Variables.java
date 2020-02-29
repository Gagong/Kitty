package Variables;

import Utils.Base64Utils;
import java.io.UnsupportedEncodingException;

public class Variables {
    Base64Utils _base = new Base64Utils();

    private String botKey;

    {
        try {
            botKey = _base.Decode("Tmpnek16TTVNalk1TnpJd05UQTJOREEyLlhscUhxUS52WnJSZmVmWWNGVVZJckVKcmpvbkVwYTc4czQ=");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getBotKey() {
        return botKey;
    }

}
