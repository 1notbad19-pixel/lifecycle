package bloodpressure.controller;

import bloodpressure.model.BloodPressureModel;
import bloodpressure.view.MainFrame;
import bloodpressure.view.InputDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

public class BloodPressureController {
  private BloodPressureModel model;
  private MainFrame view;
  private InputDialog inputDialog;

  // Сохраненные данные
  private int savedDay, savedMonth, savedYear;
  private int savedWeight;
  private int savedSystolic, savedDiastolic;

  public BloodPressureController(BloodPressureModel model, MainFrame view) {
    this.model = model;
    this.view = view;

    savedDay = savedMonth = savedYear = 0;
    savedWeight = 0;
    savedSystolic = savedDiastolic = 0;

    this.view.addInputButtonListener(new InputButtonListener());
  }

  class InputButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      inputDialog = new InputDialog(view);

      // Восстанавливаем сохраненные данные
      if (savedDay > 0) {
        inputDialog.setDay(savedDay);
        inputDialog.setMonth(savedMonth);
        inputDialog.setYear(savedYear);
        inputDialog.setWeight(savedWeight);
        inputDialog.setSystolic(savedSystolic);
        inputDialog.setDiastolic(savedDiastolic);
      }

      inputDialog.addSubmitListener(new SubmitListener());
      inputDialog.setVisible(true);

      if (inputDialog.isSubmitted()) {
        // Обновляем сохраненные данные
        savedDay = inputDialog.getDay();
        savedMonth = inputDialog.getMonth();
        savedYear = inputDialog.getYear();
        savedWeight = inputDialog.getWeight();
        savedSystolic = inputDialog.getSystolic();
        savedDiastolic = inputDialog.getDiastolic();

        updateSavedDataDisplay();

        // Вычисляем возраст
        int age = calculateAge();

        // Передаем данные в модель.
        // Если данные корректны — модель САМА уведомит View через notifyListeners().
        boolean success = model.setData(age, savedWeight, savedSystolic, savedDiastolic);

        if (!success) {
          view.setResultText("Ошибка: Некорректные данные!");
        }
        // Если success == true, View обновится АВТОМАТИЧЕСКИ
      }
    }
  }

  class SubmitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        int day = inputDialog.getDay();
        int month = inputDialog.getMonth();
        int year = inputDialog.getYear();
        int weight = inputDialog.getWeight();
        int systolic = inputDialog.getSystolic();
        int diastolic = inputDialog.getDiastolic();

        if (day < 1 || day > 31 || month < 1 || month > 12 ||
            year < 1900 || year > LocalDate.now().getYear()) {
          inputDialog.showError("Некорректная дата рождения!");
          return;
        }

        if (weight < 20 || weight > 300) {
          inputDialog.showError("Некорректный вес (20-300 кг)!");
          return;
        }

        if (systolic < 50 || systolic > 250 || diastolic < 30 ||
            diastolic > 200 || systolic <= diastolic) {
          inputDialog.showError("Некорректные показатели давления!");
          return;
        }

        inputDialog.setSubmitted(true);
        inputDialog.dispose();

      } catch (NumberFormatException ex) {
        inputDialog.showError("Введите корректные числовые значения!");
      }
    }
  }

  private int calculateAge() {
    LocalDate birthDate = LocalDate.of(savedYear, savedMonth, savedDay);
    LocalDate currentDate = LocalDate.now();
    return Period.between(birthDate, currentDate).getYears();
  }

  private void updateSavedDataDisplay() {
    String display = String.format("Сохраненные данные: %d.%d.%d, Вес: %d кг, Давление: %d/%d",
        savedDay, savedMonth, savedYear, savedWeight, savedSystolic, savedDiastolic);
    view.setSavedDataText(display);
  }
}