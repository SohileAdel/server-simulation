import org.knowm.xchart.*;
import java.util.ArrayList;

public class PlotTest {
    static MainClass mainClass = new MainClass();
    static int[] seconds, countList;
    static double[] serviceTimeArray, interArrivalArray;
    static int serviceTimeListSize, interArrivalTimeListSize;

    public static void main(String[] args) {
        mainClass.main(new String[0]);
        serviceTimeListSize = mainClass.serviceTimeList.size();
        interArrivalTimeListSize = mainClass.interArrivalTimeList.size();

        // Converting to array for faster operations
        serviceTimeArray = convertToArray(mainClass.serviceTimeList, serviceTimeListSize);
        interArrivalArray = convertToArray(mainClass.interArrivalTimeList, interArrivalTimeListSize);


        quickSort(serviceTimeArray, 0, serviceTimeListSize - 1);
        quickSort(interArrivalArray, 0, interArrivalTimeListSize - 1);


        plot("Service Time Distribution", "Time of task in seconds (rounded to the nearest integer)",
                "Number of tasks", (int) mainClass.maxServiceTime, serviceTimeListSize, serviceTimeArray);
        plot("Inter Arrival Time Distribution", "Arriving time of tasks in seconds (rounded to the nearest integer)",
                "Number of waits", (int) mainClass.maxInterArrivalTime, interArrivalTimeListSize, interArrivalArray);
    }


    public static void plot(String title, String xAxisTitle, String yAxisTitle,
                            int countListArraySize, int dataArraySize, double[] dataArray) {
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title).xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();
        seconds = new int[countListArraySize];
        countList = new int[countListArraySize];

        // Start after the zero seconds service time
        int start = 0;
        for (int i = 0; i < dataArraySize; i++) {
            if (Math.round(dataArray[i]) != 0) {
                start = i;
                break;
            }
        }

        int count = 0, time = 1;
        for (int i = start; i < dataArraySize; i++) {
            if (Math.round(dataArray[i]) == time) {
                count++;
            } else {
                countList[time - 1] = count;
                count = 0;
                time++;
            }
        }

        for (int i = 0; i < countListArraySize; i++) {
            seconds[i] = i + 1;
        }
        chart.addSeries("Data", seconds, countList);
        new SwingWrapper<XYChart>(chart).displayChart();
    }

    public static double[] convertToArray(ArrayList<Double> arrayList, int size) {
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    public static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(double[] arr, int low, int high) {

        double pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}