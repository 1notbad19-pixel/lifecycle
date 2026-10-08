package bloodpressure;

import bloodpressure.model.BloodPressureModel;
import bloodpressure.view.MainFrame;
import bloodpressure.controller.BloodPressureController;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
        e.printStackTrace();
      }

      // Создаем модель
      BloodPressureModel model = new BloodPressureModel();

      // Создаем View, передавая ему модель (View подпишется на уведомления)
      MainFrame view = new MainFrame(model);

      // Создаем Controller
      BloodPressureController controller = new BloodPressureController(model, view);

      view.setVisible(true);
    });
  }
}