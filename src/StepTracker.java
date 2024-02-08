import java.util.Scanner;

/*
 * Логика по сохранению количества шагов (ввод месяца, дня, количества шагов и сохранение данных)
 * Логика по изменению ежедневной нормы шагов
 * Вывод статистики
 * */
class StepTracker {
    Scanner scanner;
    final MonthData[] monthToData;
    int goalByStepsPerDay;
    final Converter converter;

    StepTracker(Scanner scan) {
        scanner = scan;
        this.monthToData = new MonthData[12];
        this.goalByStepsPerDay = 10000;
        this.converter = new Converter();

        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumberStepsPerDay() {
        // Номер вводимого месяца должен быть от 1 до 12 включительно
        System.out.println("Укажите номер месяца [1-12]:");

        int numberMouth = scanner.nextInt();

        if (numberMouth < 1 || numberMouth > 12) {
            System.out.println("Такого мечяца нет.");
            return;
        }

        // Номер вводимого дня должен быть от 1 до 30 включительно
        System.out.println("Укажите номер дня [1-30]: ");
        int numberDay = scanner.nextInt();
        if (numberDay < 1 || numberDay > 30) {
            System.out.println("Такого дня нет.");
            return;
        }

        // Количество шагов должно быть положительным числом
        System.out.println("Укажите количество шагов: ");
        int countStep = scanner.nextInt();
        if (countStep < 0) {
            System.out.println("Количество щагов не может быть меньше 0");
            return;
        }
        MonthData month = monthToData[numberMouth - 1];
        month.days[numberDay - 1] = countStep;
        System.out.println("Ваши данные сохранены");
    }

    void changeStepGoal() {
        System.out.println("Укажите цель шагов на день");
        int newStepsPerDay = scanner.nextInt();

        if (newStepsPerDay <= 0) {
            System.out.println("Цель не может быть меньше или равна 0.");
            return;
        }
        goalByStepsPerDay = newStepsPerDay;
    }

    void printStatistic() {
        System.out.println("Укажите номер месяца [1-12]:");

        int month = scanner.nextInt();

        if (month < 1 || month > 12){
            System.out.println("Такого мечяца нет.");
            return;
        }

        MonthData monthData = monthToData[month - 1];
        int sumSteps = monthData.sumStepsFromMonth();
        int maxSteps = monthData.maxSteps();
        int averageSteps = sumSteps / 30;
        int stepsToKm = converter.convertToKm(sumSteps);
        int stepsToKilocalories = converter.convertStepsToKilocalories(sumSteps);
        int bestSeries = monthData.bestSeries(goalByStepsPerDay);

        monthData.printDaysAndStepsFromMonth();
        System.out.println("Общее количество шагов за месяц: " + sumSteps);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxSteps);
        System.out.println("Среднее количество шагов: " + averageSteps);
        System.out.println("Пройденная дистанция (в километрах): " + stepsToKm);
        System.out.println("Количество сожжённых килокалорий: " + stepsToKilocalories);
        System.out.println("Лучшая серия: " + bestSeries);
    }
}
