package ClickPetModel {

    part def Pet {
        attribute name : String;
        attribute level : Integer;
        attribute coins : Integer;
        part state : PetState;
        part inventory : Inventory;
    }

    part def PetState {
        attribute hunger : Real;      // 0..100
        attribute happiness : Real;   // 0..100
        attribute energy : Real;      // 0..100
        attribute health : Real;      // 0..100
    }

    part def Inventory {
        attribute food : Integer;
        attribute toys : Integer;
        attribute medicine : Integer;
    }

    part def CareAction {
        attribute name : String;
        attribute costCoins : Integer;
        attribute effect : String;
    }

    part def SaveManager {
        attribute filePath : String;
        attribute autoSaveIntervalSec : Integer;
    }

    part application {
        part pet : Pet;
        part actions : CareAction[4]; // feed, play, sleep, heal
        part saveManager : SaveManager;
    }
}