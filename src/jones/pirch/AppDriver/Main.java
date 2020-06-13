package jones.pirch.AppDriver;

import jones.pirch.GUI.KeyManager;
import jones.pirch.GUI.MainPage;
import jones.pirch.GUI.SettingsManager;

//TODO  - check serialization vulnerability
//      - finish the connection between aws
//      - finish settings page
//      - add description and title to key
//      - plan encryption

public class Main {

    private static final KeyManager keyManager = new KeyManager();
    private static final SettingsManager settingsManager = new SettingsManager();

    public static void main(String[] args) {
        MainPage main = new MainPage(keyManager, settingsManager);
        main.display();
    }
}
