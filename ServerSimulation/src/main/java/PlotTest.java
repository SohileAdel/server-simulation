import org.knowm.xchart.*;

import java.util.ArrayList;
import java.util.Arrays;

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


        mergeSort(serviceTimeArray, serviceTimeListSize);
        mergeSort(interArrivalArray, interArrivalTimeListSize);

        System.out.println(Arrays.toString(serviceTimeArray));

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

    private static void mergeSort(double[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        double[] l = new double[mid];
        double[] r = new double[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    private static void merge(
            double[] a, double[] l, double[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}