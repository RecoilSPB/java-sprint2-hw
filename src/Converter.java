/*
 * Преобразование шагов в килокалории и километры
 * */
class Converter {

    //  метод, который переводит количество шагов в километры
    int convertToKm(int steps){
        return steps * 75 / 100 / 1000;
    }

    // метод, который переводит количество шагов в килокалории
    int convertStepsToKilocalories(int steps){
        return steps * 50 / 1000;
    }
}
