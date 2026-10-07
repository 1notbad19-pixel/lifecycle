package bloodpressure.model;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureModel {
  private int age;
  private int weight;
  private int systolic;
  private int diastolic;
  private int idealSystolic;
  private int idealDiastolic;
  private String evaluation;

  // Список слушателей для активной модели
  private final List<ModelListener> listeners = new ArrayList<>();

  // Внутренний интерфейс для паттерна «Наблюдатель»
  public interface ModelListener {
    void onModelChanged();
  }

  public BloodPressureModel() {}

  // ===== Управление слушателями =====
  public void addListener(ModelListener listener) {
    listeners.add(listener);
  }

  public void removeListener(ModelListener listener) {
    listeners.remove(listener);
  }

  private void notifyListeners() {
    for (ModelListener listener : listeners) {
      listener.onModelChanged();
    }
  }

  // ===== Бизнес-логика =====
  /**
   * Устанавливает данные и уведомляет слушателей при успехе.
   * @return true, если данные корректны и модель обновлена
   */
  public boolean setData(int age, int weight, int systolic, int diastolic) {
    this.age = age;
    this.weight = weight;
    this.systolic = systolic;
    this.diastolic = diastolic;

    if (!validateData()) {
      return false;
    }

    // Вычисляем результаты
    this.idealSystolic = calculateIdealSystolic();
    this.idealDiastolic = calculateIdealDiastolic();
    this.evaluation = evaluatePressure();

    // Активная модель сама уведомляет слушателей
    notifyListeners();
    return true;
  }

  public int calculateIdealSystolic() {
    return 105 + (age / 2) + (weight / 10);
  }

  public int calculateIdealDiastolic() {
    return 65 + (age / 3) + (weight / 15);
  }

  public String evaluatePressure() {
    int diffSystolic = systolic - idealSystolic;
    int diffDiastolic = diastolic - idealDiastolic;

    if (Math.abs(diffSystolic) <= 10 && Math.abs(diffDiastolic) <= 10) {
      return "Нормальное давление";
    } else if (diffSystolic > 10 || diffDiastolic > 10) {
      return "Повышенное давление";
    } else {
      return "Пониженное давление";
    }
  }

  public boolean validateData() {
    return age > 0 && age < 120 &&
        weight > 20 && weight < 300 &&
        systolic > 50 && systolic < 250 &&
        diastolic > 30 && diastolic < 200 &&
        systolic > diastolic;
  }

  // ===== Геттеры =====
  public int getAge() { return age; }
  public int getWeight() { return weight; }
  public int getSystolic() { return systolic; }
  public int getDiastolic() { return diastolic; }
  public int getIdealSystolic() { return idealSystolic; }
  public int getIdealDiastolic() { return idealDiastolic; }
  public String getEvaluation() { return evaluation; }
}