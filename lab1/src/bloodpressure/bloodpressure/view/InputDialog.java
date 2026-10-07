package bloodpressure.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class InputDialog extends JDialog {
  private JTextField dayField;
  private JTextField monthField;
  private JTextField yearField;
  private JTextField weightField;
  private JTextField systolicField;
  private JTextField diastolicField;
  private JButton submitButton;
  private JButton cancelButton;

  private boolean submitted = false;

  public InputDialog(Frame parent) {
    super(parent, "Ввод данных", true);
    setSize(350, 300);
    setLocationRelativeTo(parent);
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Дата рождения
    gbc.gridx = 0; gbc.gridy = 0;
    add(new JLabel("День рождения:"), gbc);
    gbc.gridx = 1;
    dayField = new JTextField(5);
    add(dayField, gbc);

    gbc.gridx = 0; gbc.gridy = 1;
    add(new JLabel("Месяц рождения:"), gbc);
    gbc.gridx = 1;
    monthField = new JTextField(5);
    add(monthField, gbc);

    gbc.gridx = 0; gbc.gridy = 2;
    add(new JLabel("Год рождения:"), gbc);
    gbc.gridx = 1;
    yearField = new JTextField(5);
    add(yearField, gbc);

    // Вес
    gbc.gridx = 0; gbc.gridy = 3;
    add(new JLabel("Вес (кг):"), gbc);
    gbc.gridx = 1;
    weightField = new JTextField(5);
    add(weightField, gbc);

    // Давление
    gbc.gridx = 0; gbc.gridy = 4;
    add(new JLabel("Верхнее давление:"), gbc);
    gbc.gridx = 1;
    systolicField = new JTextField(5);
    add(systolicField, gbc);

    gbc.gridx = 0; gbc.gridy = 5;
    add(new JLabel("Нижнее давление:"), gbc);
    gbc.gridx = 1;
    diastolicField = new JTextField(5);
    add(diastolicField, gbc);

    // Кнопки
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    submitButton = new JButton("OK");
    cancelButton = new JButton("Отмена");
    buttonPanel.add(submitButton);
    buttonPanel.add(cancelButton);

    gbc.gridx = 0; gbc.gridy = 6;
    gbc.gridwidth = 2;
    add(buttonPanel, gbc);

    // Действия для кнопок
    cancelButton.addActionListener(e -> {
      submitted = false;
      dispose();
    });
  }

  public void addSubmitListener(ActionListener listener) {
    submitButton.addActionListener(listener);
  }

  public int getDay() {
    return Integer.parseInt(dayField.getText());
  }

  public int getMonth() {
    return Integer.parseInt(monthField.getText());
  }

  public int getYear() {
    return Integer.parseInt(yearField.getText());
  }

  public int getWeight() {
    return Integer.parseInt(weightField.getText());
  }

  public int getSystolic() {
    return Integer.parseInt(systolicField.getText());
  }

  public int getDiastolic() {
    return Integer.parseInt(diastolicField.getText());
  }

  public void setDay(int day) { dayField.setText(String.valueOf(day)); }
  public void setMonth(int month) { monthField.setText(String.valueOf(month)); }
  public void setYear(int year) { yearField.setText(String.valueOf(year)); }
  public void setWeight(int weight) { weightField.setText(String.valueOf(weight)); }
  public void setSystolic(int systolic) { systolicField.setText(String.valueOf(systolic)); }
  public void setDiastolic(int diastolic) { diastolicField.setText(String.valueOf(diastolic)); }

  public boolean isSubmitted() { return submitted; }
  public void setSubmitted(boolean submitted) { this.submitted = submitted; }

  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
  }
}