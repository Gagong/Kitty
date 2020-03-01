package Utils;

import Debug.Debug;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonUtils {

    private static Collection<String> RatedUsers = new HashSet<String>();

    public static void writeJson(JsonBase data) throws Exception {
        JSONObject User = new JSONObject();
        User.put("userID", data.userID);
        User.put("userName", data.userName);
        User.put("currentRole", data.currentRole);
        User.put("roleName", data.roleName);
        User.put("nextRoleName", data.nextRoleName);
        User.put("rating", data.rating);
        User.put("nextStep", data.nextStep);
        User.put("rateTime", Instant.now().toString());
        Files.write(Paths.get("RateUsers/" + data.userID + ".txt"), User.toJSONString().getBytes());
    }

    public static Object readJson(String userID) throws Exception {
        FileReader reader = new FileReader("RateUsers/" + userID + ".txt");
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    public static void createNewUser(String userID) throws IOException {
        File userFile = new File("RateUsers/" + userID + ".txt");
        if (userFile.createNewFile())
            Debug.p("JsonUtils", "createNewUser", "DataBase file for " + userID + " created!");
        else
            Debug.p("JsonUtils", "createNewUser", "DataBase file for " + userID + " already exist in this directory!");
    }

    public static JsonBase JsonParseToBase(String UID) {
        Gson gson = new Gson();
        JsonBase data = null;
        try {
            data = gson.fromJson(readJson(UID).toString(), JsonBase.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void refreshUsers() throws IOException {
        RatedUsers.clear();
        Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir") + "/RateUsers"));
        List<String> result = walk.map(Path::toString).filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
        result.forEach(f -> {
            String[] name = f.split("RateUsers");
            String UID = name[1].substring(1).split(".txt")[0].toString();
            RatedUsers.add(UID);
        });
    }

    public static Collection<String> returnBase() {
        return RatedUsers;
    }
}
