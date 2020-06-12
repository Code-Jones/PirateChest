package jones.pirch.ProblemDomain;

import java.util.Date;

public class Key {
    private String url;
    private String login;
    private String password;
    private int count = 0;
    private long current = new Date().getTime();

    public Key(String login, String password){
        this.login = login;
        this.password = password;
    }
    public Key(String url) {
        super();
        this.url = url;
    }
}
