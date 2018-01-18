import java.lang.Throwable;

class ModelRecursionTest {

  static int test_count;

  static boolean verbose;

  static int reverseArray1_test;
  static int reverseArray2_test;
  static int reverseArray3_test;
  static int maxArrayIndex_test;
  static int minArrayIndex_test;

  static String testName(int test) {

    if (test == reverseArray1_test) return "reverseArray1_test";
    if (test == reverseArray2_test) return "reverseArray2_test";
    if (test == reverseArray3_test) return "reverseArray3_test";
    if (test == maxArrayIndex_test) return "maxArrayIndex_test";
    if (test == minArrayIndex_test) return "minArrayIndex_test";

    return "";
  }

  public static int runTest(int test) {

    Recursion R = new Recursion();

    int A[] = new int[1000];
    int B[] = new int[1000];

    try {
      if (test == reverseArray1_test) {
        for (int i = 0; i < A.length; i++) {
          A[i] = i + 1;
        }
        R.reverseArray1(A, 1, B);
        // at this point should have copied leftmost to rightmost
        // and hence the next item in B shouldn't be set
        if (A[0] != B[B.length - 1] || B[B.length - 2] != 0) return 1;
        R.reverseArray1(A, A.length - 1, B);
        // everything copied in except the last item
        if  (A[A.length - 2] != B[1] || B[0] != 0) return 2;
        R.reverseArray1(B, B.length, A);
        // A as it was except last element is now 0
        for (int i = 0; i < A.length - 1; i++) {
          if (A[i] != i + 1) return 3;
        }
        if (A[A.length - 1] != 0) return 4;
      } else if (test == reverseArray2_test) {
        // inverse conditionals relative to previous test
        for (int i = 0; i < A.length; i++) {
          A[i] = i + 1;
        }
        R.reverseArray2(A, 1, B);
        // at this point should have copied rightmost to leftmost
        // and hence the next item in B shouldn't be set
        if (A[A.length - 1] != B[0] || B[1] != 0) return 1;
        R.reverseArray2(A, A.length - 1, B);
        // everything copied in except the last item
        if (A[1] != B[B.length - 2] || B[A.length - 1] != 0) return 2;
        R.reverseArray2(B, B.length, A);
        // A as it was except first element is now 0
        for (int i = 1; i < A.length; i++) {
          if (A[i] != i + 1) return 3;
        }
        if (A[0] != 0) return 4;
      } else if (test == reverseArray3_test) {
        for (int i = 0; i < A.length; i++) {
          A[i] = i + 1;
        }
        R.reverseArray3(A, 0, 0);
        // should do nothing
        if (A[0] != 1) return 1;
        R.reverseArray3(A, 0, 2);
        // swap [0] with [2] but leave [1] the same
        if (A[0] != 3 || A[1] != 2 || A[2] != 1) return 2;
        R.reverseArray3(A, 0, 3);
        // reverses, as you would expect, the first 4 elements
        if (A[0] != 4 || A[1] != 1 || A[2] != 2 || A[3] != 3) return 3;
        R.reverseArray3(A, 3, A.length - 1);
        // comprehension left as an excersize to the reader
        for (int i = 1; i < A.length - 4; i++) {
          if (A[A.length - i - 1] != i + 4) return 4;
        }
        if (A[3] != A.length || A[A.length - 1] != 3) return 5;
      } else if (test == maxArrayIndex_test) {
        // comprehension left as an excersize to the reader
        // because a magician shouldn't reveal his secrets
        int magic = (int) Math.ceil(Math.sqrt((double) A.length));
        for (int i = 0; i < A.length; i++) {
          A[i] = i % magic;
        }
        // the max value is (magic - 1) and the max index on both sides,
        // regardless of returned index, should contain the same value
        if (A[R.maxArrayIndex(A, 0, A.length / 2)] !=
            A[R.maxArrayIndex(A, A.length / 2 + 1, A.length - 1)]) return 1;
        A[magic - 1]++; // now the max value should be (magic) at this index
        if (A[R.maxArrayIndex(A, 0, A.length - 1)] !=
            R.maxArrayIndex(A, 0, A.length - 1) + 1) return 2;
        A[magic] = magic + 1; // yet again a new maximum value is born
        if (A[R.maxArrayIndex(A, 0, A.length - 1)] !=
            R.maxArrayIndex(A, 0, A.length - 1) + 1) return 3;
        A[A.length / 2] = A.length;
        // You had best not find that value if it's not in the range...
        if (A[R.maxArrayIndex(A, 0, A.length / 2 - 1)] == A.length ||
            A[R.maxArrayIndex(A, A.length / 2 + 1, A.length - 1)] == A.length)
          return 4;
      } else if (test == minArrayIndex_test) {
        // comprehension left as an excersize to the reader
        // because a magician shouldn't reveal his secrets
        int magic = (int) Math.ceil(Math.sqrt((double) A.length));
        for (int i = 0; i < A.length; i++) {
          A[i] = -1 * (i % magic);
        }
        // the min value is -1 * (magic - 1) and the min index on both sides,
        // regardless of returned index, should contain the same value
        if (A[R.minArrayIndex(A, 0, A.length / 2)] !=
            A[R.minArrayIndex(A, A.length / 2 + 1, A.length - 1)]) return 1;
        A[magic - 1]--; // now the min value should be (-magic) at this index
        if (-1 * A[R.minArrayIndex(A, 0, A.length - 1)] !=
            R.minArrayIndex(A, 0, A.length - 1) + 1) return 2;
        A[magic] = -1 * (magic + 1); // yet again a new minimum value is born
        if (-1 * A[R.minArrayIndex(A, 0, A.length - 1)] !=
            R.minArrayIndex(A, 0, A.length - 1) + 1) return 3;
        A[A.length / 2] = -1 * A.length;
        // You had best not find that value if it's not in the range...
        if (A[R.minArrayIndex(A, 0, A.length / 2 - 1)] == -1 * A.length ||
            A[R.minArrayIndex(A, A.length / 2 + 1, A.length - 1)] == -1 * A.length)
          return 4;
      }
    } catch (Exception e) {
      if (verbose) {
        System.out.println("\nUnfortunately your program crashed on test " +
            testName(test) + " With an exception of:\n");
        e.printStackTrace();
        System.out.println();
      }
      return 255;
    } catch (StackOverflowError s) {
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
    reverseArray1_test = test_count++;
    reverseArray2_test = test_count++;
    reverseArray3_test = test_count++;
    maxArrayIndex_test = test_count++;
    minArrayIndex_test = test_count++;

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

    final int maxScore = 75;
    final int charity = 10;

    int totalPoints = (maxScore - test_count * 15) + tests_passed * 15;
    if (test_status == 255) { // your code had an exception
      totalPoints = charity;
    }

    System.out.printf("\nThis gives you a score of %d / %d " +
        "for this component of the assignment\n\n", totalPoints, maxScore);
  }
}

