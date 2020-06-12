package jones.pirch.GUI;

import javax.swing.*;

public abstract class TabBase {
    protected JPanel panel;
    protected TabBase() {
        this.panel = new JPanel();
    }
    public JPanel getPanel() { return this.panel;}
}
