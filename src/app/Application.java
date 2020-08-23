package app;

import model.Model;
import view.View;
import java.awt.*;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Dimension windowDimension = Toolkit.getDefaultToolkit().getScreenSize();

        Model model = new Model(windowDimension);
        View view = new View(model);
        Controller controller = new Controller(model, view);

        java.awt.EventQueue.invokeLater(() -> view.setVisible(true));
    }
}
