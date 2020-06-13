package jones.pirch.ProblemDomain;

import java.io.Serializable;
import java.util.Date;

public class Key implements Serializable {
    private String url;
    private String login;
    private String password;
    private int count = 0;
    private long current = new Date().getTime();
    private transient int SSN;

    public Key(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public Key(String[] split) {
        this.url = split[0];
        this.login = split[1];
        this.password = split[2];
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return  "url: " + url +
                ", login: " + login +
                ", password: " + password;
    }
}
