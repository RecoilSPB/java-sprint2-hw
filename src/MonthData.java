/*
 * Логика по подсчёту статистики за месяц
 * Хранение данных конкретного месяца
 * */
class MonthData {
    int[] days;

    MonthData(){
        this.days = new int[30];
    }

    void printDaysAndStepsFromMonth() {
        for (int i = 0; i < days.length; i++) {
            int stepDay = days[i];

            System.out.println((i + 1) + " день: " + stepDay);
        }
    }

    // Общее количество шагов за месяц
    int sumStepsFromMonth() {
        int sumSteps = 0;

        for (int day : days) {
            sumSteps = sumSteps + day;
        }
        return sumSteps;
    }

    // Максимальное количество шагов в месяце
    int maxSteps() {
        int maxSteps = 0;

        for (int day : days) {
            if (maxSteps < day) {
                maxSteps = day;
            }
        }
        return maxSteps;
    }

    int bestSeries(int goalByStepsPerDay) {
        int finalSeries = 0;
        int currentSeries = 0;

        for (int steps : days) {
            if (steps >= goalByStepsPerDay) {
                currentSeries++;
            } else {
                finalSeries = Math.max(finalSeries, currentSeries);
                currentSeries = 0;
            }
        }

        // Учитываем случай, когда максимальная серия находится в конце массива
        finalSeries = Math.max(finalSeries, currentSeries);

        return finalSeries;
    }
}
