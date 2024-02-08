import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker(scanner);

        while (true) {
            printMenu();

            String command = scanner.next();

            switch (command) {
                case "1":
                    // Ввести количество шагов за определённый день
                    stepTracker.addNewNumberStepsPerDay();
                    break;
                case "2":
                    // Изменить цель по количеству шагов в день
                    stepTracker.changeStepGoal();
                    break;
                case "3":
                    // Напечатать статистику за определённый месяц
                    stepTracker.printStatistic();
                    break;
                case "4":
                    // выход из программы
                    return;
                default:
                    System.out.println("Неизвестная команда!");
            }
        }

    }

    static void printMenu() {
        /*
         * Ввести количество шагов за определённый день
         * Изменить цель по количеству шагов в день
         * Напечатать статистику за определённый месяц
         * Выйти из приложения.
         * */
        System.out.println("Выберите одну из команд:");
        System.out.println("1. Ввести количество шагов за определённый день");
        System.out.println("2. Изменить цель по количеству шагов в день");
        System.out.println("3. Напечатать статистику за определённый месяц");
        System.out.println("4. Выйти из приложения");

    }

}
