import java.lang.Throwable;
import java.io.*;

class ModelQueueTest {

  static int test_count;

  static boolean verbose;

  static int merge_test1;
  static int merge_test2;
  static int mergeSort_test1;
  static int mergeSort_test2;
  static int mergeSort_test3;

  static String testName(int test) {

    if (test == merge_test1) return "merge_test1";
    if (test == merge_test2) return "merge_test2";
    if (test == mergeSort_test1) return "mergeSort_test1";
    if (test == mergeSort_test2) return "mergeSort_test2";
    if (test == mergeSort_test3) return "mergeSort_test3";

    return "";
  }

  public static int runTest(int test) {

    Search s = new Search();

    String A[] = new String[1000];
    int B[] = new int[1000];

    try {
      if (test == merge_test1) {
        for (int i = 0; i < A.length; i++) {
          A[A.length - i - 1] = i + "";
        }
        s.merge(A, B, 0, 0, 1);
        if (!A[0].equals("998")) return 1;
        if (!A[2].equals("997")) return 2;
        s.merge(A, B, 998, 998, 999);
        if (!A[999].equals("1")) return 3;
        if (!A[997].equals("2")) return 4;
      } else if (test == merge_test2) {
        for (int i = 0; i < A.length / 2; i++) {
          A[i + A.length / 2] = i + "";
        }
        for (int i = 0; i < A.length / 2; i++) {
          A[i] = i + A.length / 2 + "";
        }
        s.merge(A, B, 249, 499, 750);
        if (!A[0].equals("500")) return 1;
        if (!A[999].equals("499")) return 2;
        if (!A[249].equals("0")) return 3;
        if (!A[750].equals("999")) return 4;
        for (int i = 0; i < A.length / 2; i++) {
          A[i + A.length / 2] = i + "";
        }
        for (int i = 0; i < A.length / 2; i++) {
          A[i] = i + A.length / 2 + "";
        }
        s.merge(A, B, 0, 499, 999);
        if (!A[0].equals("0")) return 5;
        if (!A[999].equals("999")) return 6;
      } else if (test == mergeSort_test1) {
        for (int i = 0; i < A.length; i++) {
          A[A.length - i - 1] = i + "";
        }
        s.mergeSort(A, B, 0, 1);
        if (!A[0].equals("998")) return 1;
        if (!A[2].equals("997")) return 2;
        s.mergeSort(A, B, 998, 999);
        if (!A[999].equals("1")) return 3;
        if (!A[997].equals("2")) return 4;
      } else if (test == mergeSort_test2) {
        for (int i = 0; i < A.length / 2; i++) {
          A[i + A.length / 2] = i + "";
        }
        for (int i = 0; i < A.length / 2; i++) {
          A[i] = i + A.length / 2 + "";
        }
        s.mergeSort(A, B, 249, 750);
        if (!A[0].equals("500")) return 1;
        if (!A[999].equals("499")) return 2;
        if (!A[249].equals("0")) return 3;
        if (!A[750].equals("999")) return 4;
        s.mergeSort(A, B, 0, 999);
        if (!A[0].equals("0")) return 5;
        if (!A[999].equals("999")) return 6;
      } else if (test == mergeSort_test3) {
        for (int i = 0; i < A.length; i++) {
          A[A.length - i - 1] = i + 1 + "";
        }
        A[51] = "makes";
        A[50] = "Sense,";
        A[0] = "nothing";
        s.mergeSort(A, B, 0, 50);
        if (!A[49].equals("Sense,")) return 1;
        if (!A[50].equals("nothing")) return 2;
        if (!A[51].equals("makes")) return 3;
        s.mergeSort(A, B, 0, 999);
        if (!A[997].equals("Sense,")) return 4;
        if (!A[998].equals("makes")) return 5;
        if (!A[999].equals("nothing")) return 6;
        if (!A[996].equals("999")) return 7;
      }
    } catch (Exception e) {
      if (verbose) {
        System.out.println("\nUnfortunately your program crashed on test " +
            testName(test) + " With an exception of:\n");
        e.printStackTrace();
        System.out.println();
      }
      return 255;
    } catch (StackOverflowError se) {
      if (verbose) {
        System.out.println("\nUnfortunately your program crashed on test " +
            testName(test) + " With a stack overflow error\n");
      }
      return 255;
    }
    return 0;
  }

  public static void main(String args[]) {

    if (args.length > 1 || (args.length == 1 && !args[0].equals("-v"))) {
      System.err.println("Usage: ./ListTest [-v]");
      System.exit(1);
    }
    verbose = false;
    if (args.length == 1) verbose = true;

    test_count = 0;

    // form is TESTCASE_FUNCTION
    merge_test1 = test_count++;
    merge_test2 = test_count++;
    mergeSort_test1 = test_count++;
    mergeSort_test2 = test_count++;
    mergeSort_test3 = test_count++;

    int test_status = 0;
    int tests_passed = 0;
    if (verbose)
      System.out.println("\nList of tests passed/failed:\n");
    for (int i = 0; i < test_count; i++) {
      test_status = runTest(i);
      if (verbose)
        System.out.printf("%s %s", testName(i),
            test_status == 0 ? "PASSED" : "FAILED");
      if (test_status == 0) {
        if (verbose) System.out.printf("\n");
        tests_passed++;
      } else if (test_status == 255) {
        if (verbose) System.out.printf(": due to exception\n");
        break;
      } else {
        if (verbose) System.out.printf(": in test %d\n", test_status);
      }
    }

    if (verbose && test_status != 255) {
      System.out.printf("\n\nPassed %d tests out of %d possible\n",
          tests_passed, test_count);
    } else if (verbose) {
      System.out.printf("\n\nReceiving Charity points due to an exception\n");
    }

    final int maxScore = 50;
    final int charity = 5;

    int totalPoints = (maxScore - test_count * 10) + tests_passed * 10;
    if (test_status == 255) { // your code had an exception
      totalPoints = charity;
    }

    System.out.printf("\nThis gives you a score of %d / %d " +
        "for this component of the assignment\n\n", totalPoints, maxScore);

  }
}

