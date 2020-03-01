package Utils;

public class JsonBase {
    public String userID;
    public String userName;
    public String currentRole;
    public String roleName;
    public String nextRoleName;
    public int rating;
    public int nextStep;
    public String rateTime;

    public JsonBase (String userID, String userName, String currentRole, String roleName, String nextRoleName, int rating, int nextStep, String rateTime) {
        this.userID = userID;
        this.userName = userName;
        this.currentRole = currentRole;
        this.roleName = roleName;
        this.nextRoleName = nextRoleName;
        this.rating = rating;
        this.nextStep = nextStep;
        this.rateTime = rateTime;
    }
}
