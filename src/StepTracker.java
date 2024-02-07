import java.util.Scanner;

/*
* Логика по сохранению количества шагов (ввод месяца, дня, количества шагов и сохранение данных)
* Логика по изменению ежедневной нормы шагов
* Вывод статистики
* */
class StepTracker {

    MonthData[] monthToData = new MonthData[12];
    int goalByStepsPerDay = 10000;
    Converter converter = new Converter();

    StepTracker(Scanner scan){
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumberStepsPerDay(Scanner scanner){
        // Номер вводимого месяца должен быть от 1 до 12 включительно
        System.out.println("Укажите номер месяца [1-12]:");

        int numberMouth = scanner.nextInt();

        if (numberMouth < 1 || numberMouth > 12){
            System.out.println("Такого мечяца нет.");
            return;
        }

        // Номер вводимого дня должен быть от 1 до 30 включительно
        System.out.println("Укажите номер дня [1-30]: ");
        int numberDay = scanner.nextInt();
        if (numberDay < 1 || numberDay > 30){
            System.out.println("Такого дня нет.");
            return;
        }

        // Количество шагов должно быть положительным числом
        System.out.println("Укажите количество шагов: ");
        int countStep = scanner.nextInt();
        if (countStep < 0){
            System.out.println("Количество щагов не может быть меньше 0");
            return;
        }
        MonthData month = monthToData[numberMouth - 1];
        month.days[numberDay - 1] = countStep;
        System.out.println("Ваши данные сохранены");
    }

    void changeStepGoal(Scanner scanner){
        System.out.println("Укажите цель шагов на день");
        int newStepsPerDay = scanner.nextInt();

        if (newStepsPerDay <= 0){
            System.out.println("Цель не может быть меньше или равна 0.");
        }
        goalByStepsPerDay = newStepsPerDay;
    }

    void printDaysAndStepsFromMonth(int[] days){
        for (int i = 0; i < days.length; i++){
            int stepDay = days[i];

            System.out.println((i + 1) + " день: " + stepDay);
        }
    }

    // Общее количество шагов за месяц
    int sumStepsFromMonth(int[] days) {
        int sumSteps = 0;

        for (int day : days) {
            sumSteps = sumSteps + day;
        }
        return sumSteps;
    }

    // Максимальное количество шагов в месяце
    int maxSteps(int[] days){
        int maxSteps = 0;

        for (int day : days) {
            if (maxSteps < day) {
                maxSteps = day;
            }
        }
        return maxSteps;
    }

    int bestSeries(int[] days, int goalByStepsPerDay){
        int finalSeries  = 0;
        int currentSeries = 0;

        for (int steps : days) {
            if (steps >= goalByStepsPerDay) {
                currentSeries++;
            } else {
                finalSeries  = Math.max(finalSeries , currentSeries);
                currentSeries = 0;
            }
        }

        // Учитываем случай, когда максимальная серия находится в конце массива
        finalSeries  = Math.max(finalSeries , currentSeries);

        return finalSeries ;
    }

    void printStatistic(Scanner scanner){
        System.out.println("Укажите номер месяца [1-12]:");

        int month = scanner.nextInt();
        MonthData monthData = monthToData[month - 1];
        int[] days = monthData.days;
        int sumSteps = sumStepsFromMonth(days);
        int maxSteps = maxSteps(days);
        int averageSteps = sumSteps / 30;
        int stepsToKm = converter.convertToKm(sumSteps);
        int stepsToKilocalories = converter.convertStepsToKilocalories(sumSteps);
        int bestSeries = bestSeries(days, goalByStepsPerDay);

        printDaysAndStepsFromMonth(days);
        System.out.println("Общее количество шагов за месяц: " + sumSteps);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxSteps);
        System.out.println("Среднее количество шагов: " + averageSteps);
        System.out.println("Пройденная дистанция (в километрах): " + stepsToKm);
        System.out.println("Количество сожжённых килокалорий: " + stepsToKilocalories);
        System.out.println("Лучшая серия: " + bestSeries);
    }
}
