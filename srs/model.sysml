package ClickPetModel {

    // Определение питомца
    part def Pet {
        attribute name : String;
        attribute level : Integer;
        attribute coins : Integer;

        part state : PetState;
        part inventory : Inventory;
    }

    // Состояние питомца (шкалы)
    part def PetState {
        attribute hunger : Real;      // 0..100
        attribute happiness : Real;   // 0..100
        attribute energy : Real;      // 0..100
        attribute health : Real;      // 0..100
    }

    // Инвентарь игрока
    part def Inventory {
        attribute food : Integer;
        attribute toys : Integer;
        attribute medicine : Integer;
    }

    // Действие по уходу
    part def CareAction {
        attribute name : String;
        attribute costCoins : Integer;
        attribute effect : String;
    }

    // Менеджер сохранения
    part def SaveManager {
        attribute filePath : String;
        attribute autoSaveIntervalSec : Integer;
    }

    // Состояния питомца (для диаграммы состояний)
    state def PetLifecycle {
        state Happy;
        state Hungry;
        state Sick;
        state Sleeping;

        // Примеры переходов
        transition Happy -> Hungry;
        transition Hungry -> Sick;
        transition Sick -> Happy;
        transition Happy -> Sleeping;
        transition Sleeping -> Happy;
    }

    // Корневая система
    part application {
        part pet : Pet;
        part actions : CareAction[4]; // feed, play, sleep, heal
        part saveManager : SaveManager;
        part lifecycle : PetLifecycle;
    }
}