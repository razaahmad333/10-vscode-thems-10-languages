public class Check_array_sorted {
    public static void main(String[] args) {
        // int arr[] = { -11, 2, 3, 4, 15, 6, 7, 8, 9, 10 };
        // System.out.println(isArraySorted(arr, 0));
        // System.out.println(findElement(arr, 15, 0));

        int[] rotatedArray = { 4, 5, 1, 2, 3 };
        System.out.println(binarySearchInRotatedArray(rotatedArray, 0, rotatedArray.length - 1, 4));
    }

    static boolean isArraySorted(int[] arr, int pointer) {
        if (pointer == arr.length - 1) {
            return true;
        }
        return arr[pointer] <= arr[pointer + 1] && isArraySorted(arr, pointer + 1);
    }

    static int findElement(int[] arr, int element, int index) {
        if (index == arr.length) {
            return -1;
        }

        if (arr[index] == element) {
            return index;
        }

        return findElement(arr, element, index + 1);
    }

    static int binarySearchInRotatedArray(int[] arr, int start, int end, int target) {
        int mid = start + (end - start) / 2;

        if (target == arr[mid]) {
            return mid;
        }

        if (start == end) {
            return -1;
        }
        if (arr[mid] > arr[start]) {
            if (target > arr[start] && target < arr[mid]) {
                return binarySearchInRotatedArray(arr, start, mid - 1, target);
            }
        } else if (arr[mid] < arr[start]) {
            if (target > arr[mid]) {
                return binarySearchInRotatedArray(arr, start, mid - 1, target);
            }
        }

        return binarySearchInRotatedArray(arr, mid + 1, end, target);

    }
}


public class Main {
    public static void main(String[] args) {

        // Planet earth = Planet.getPlanet("C-53");

        School fps = new School("Faizabad Public School");

        Course math = new Course("Math");
        Course physics = new Course("Physics");
        Course chemistry = new Course("Chemistry");

        Teacher fareed = new Teacher("Fareed", 30, fps, math);
        // Teacher alakh = new Teacher("Alakh", 30, fps, physics);
        Teacher sana = new Teacher("Sana Khan", 30, fps, chemistry);

        Student farhad = new Student("Farhad", 20, fps);
        Student ali = new Student("Ali", 20, fps);
        Student sajid = new Student("Sajid", 20, fps);
        // Student farhan = new Student("Farhan", 20, fps);

        System.out.println(Human.population);

        fareed.sayHello(sana);
        System.out.println(fareed.course.name);

        fps.addCourse(math);
        fps.addCourse(physics);
        fps.addCourse(chemistry);

        farhad.assignCourse(math);
        farhad.assignCourse(physics);

        ali.assignCourse(math);
        ali.assignCourse(chemistry);

        fps.printTotalStudents();
        fps.printTotalTeachers();
        System.out.println();
        fps.printTeachersNameAndCourse();
        System.out.println();

        // farhad.printCoursesAndTeacher();

        sajid.printCoursesAndTeacher();
    }
}


public class Planet {
    private String code;
    private static Planet earth;
    private static School[] schools;

    private Planet(String code) {
        this.code = code;
    }

    static Planet getPlanet(String code) {
        if (earth == null) {
            earth = new Planet(code);
        }
        return earth;
    }

    public String getCode() {
        return code;
    }

    void addSchool(School school) {
        if (schools == null) {
            schools = new School[1];
            schools[0] = school;
        } else {
            School[] newSchools = new School[schools.length + 1];
            for (int i = 0; i < schools.length; i++) {
                newSchools[i] = schools[i];
            }
            newSchools[schools.length] = school;
            schools = newSchools;
        }
    }
}


import java.util.Arrays;

public class MergeSort {
    
    public static void main(String[] args) {
        int[] arr = { 1, 0, 2 };
        mergeSortInPlace(arr, 0, arr.length / 2 - 1, arr.length / 2, arr.length - 1);
        // System.out.println(Arrays.toString(mergeSort(arr)));
        System.out.println(Arrays.toString(arr));
    }

    static int[] mergeSort(int arr[]) {

        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] firstHalf = new int[mid];
        for (int i = 0; i < mid; i++) {
            firstHalf[i] = arr[i];
        }
        mid = arr.length % 2 == 0 ? mid : mid + 1;
        int[] secondHalf = new int[mid];
        for (int i = 0; i < mid; i++) {
            secondHalf[i] = arr[firstHalf.length + i];
        }

        int[] firstHalfSorted = mergeSort(firstHalf);
        int[] secondHalfSorted = mergeSort(secondHalf);

        int m = 0, n = 0;
        for (int i = 0; i < arr.length; i++) {

            if (m == firstHalfSorted.length && n != secondHalfSorted.length) {
                arr[i] = secondHalfSorted[n];
                n++;
            } else if (m != firstHalfSorted.length && n == secondHalfSorted.length) {
                arr[i] = firstHalfSorted[m];
                m++;
            } else if (firstHalfSorted[m] < secondHalfSorted[n]) {
                arr[i] = firstHalfSorted[m];
                m++;
            } else if (firstHalfSorted[m] > secondHalfSorted[n]) {
                arr[i] = secondHalfSorted[n];
                n++;
            } else {
                arr[i] = firstHalfSorted[m];
                arr[i + 1] = secondHalfSorted[n];
                m++;
                n++;
                i++;
            }
        }
        return arr;
    }

    static void mergeSortInPlace(int arr[], int s1, int e1, int s2, int e2) {

        int l1 = e1 - s1 + 1;
        int l2 = e2 - s2 + 1;
        if (l1 <= 1 && l2 <= 0) {
            return;
        }
        int mid = (l1) / 2;
        if (l1 > 1) {
            mergeSortInPlace(arr, s1, mid - 1, mid, e1);
        }
        mid = l2 / 2;
        if (l2 > 1) {
            mergeSortInPlace(arr, s2, s2 + mid - 1, s2 + mid, e2);
        }
        int m = 0, n = 0;
        for (int i = 0; i < l1 + l2; i++) {
            if (m == l1 && n != l2) {
                swap(arr, s1 + i, s2 + n);
                n++;
            } else if (m != l1 && n == l2) {
                swap(arr, s1 + i, s1 + m);
                m++;
            } else if (arr[s1 + m] < arr[s2 + n]) {
                swap(arr, s1 + i, s1 + m);
                m++;
            } else if (arr[s1 + m] > arr[s2 + n]) {
                swap(arr, s1 + i, s2 + n);
                n++;
            } else {
                swap(arr, s1 + i, s1 + m);
                swap(arr, s1 + i, s2 + n);
                m++;
                n++;
                i++;
            }
        }
    }

    static void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

}