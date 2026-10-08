package bloodpressure.view;

import bloodpressure.model.BloodPressureModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements BloodPressureModel.ModelListener {
  private JLabel resultLabel;
  private JLabel savedDataLabel;
  private JButton inputButton;

  private BloodPressureModel model;

  public MainFrame(BloodPressureModel model) {
    this.model = model;
    // Регистрируем View как слушателя модели
    model.addListener(this);

    setTitle("Анализатор артериального давления");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 300);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(10, 10));

    // Верхняя панель с сохраненными данными
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    savedDataLabel = new JLabel("Нет сохраненных данных");
    topPanel.add(savedDataLabel);
    add(topPanel, BorderLayout.NORTH);

    // Центральная панель с результатом
    JPanel centerPanel = new JPanel(new GridBagLayout());
    resultLabel = new JLabel("Введите данные для анализа", SwingConstants.CENTER);
    resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
    centerPanel.add(resultLabel);
    add(centerPanel, BorderLayout.CENTER);

    // Нижняя панель с кнопкой
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    inputButton = new JButton("Ввести данные");
    inputButton.setPreferredSize(new Dimension(150, 40));
    bottomPanel.add(inputButton);
    add(bottomPanel, BorderLayout.SOUTH);
  }

  /**
   * Метод вызывается АВТОМАТИЧЕСКИ моделью при изменении данных.
   * Это ключевая особенность активной модели.
   */
  @Override
  public void onModelChanged() {
    // View сам запрашивает данные у модели
    int idealSystolic = model.getIdealSystolic();
    int idealDiastolic = model.getIdealDiastolic();
    String evaluation = model.getEvaluation();

    String result = String.format(
        "<html><div style='text-align: center;'>" +
            "Идеальное давление: %d/%d<br>" +
            "Ваше давление: %d/%d<br>" +
            "Оценка: <b>%s</b>" +
            "</div></html>",
        idealSystolic, idealDiastolic,
        model.getSystolic(), model.getDiastolic(),
        evaluation
    );

    resultLabel.setText(result);
  }

  public void setSavedDataText(String text) {
    savedDataLabel.setText(text);
  }

  public void setResultText(String text) {
    resultLabel.setText(text);
  }

  public void addInputButtonListener(ActionListener listener) {
    inputButton.addActionListener(listener);
  }
}