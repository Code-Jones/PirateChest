package jones.pirch.AppDriver;

import jones.pirch.GUI.MainPage;

public class Main {

    public static void main(String[] args) {
        MainPage main = new MainPage();
        main.display();
    }

    public static int adder(int x, int y) {
        return x + y;
    }
}
