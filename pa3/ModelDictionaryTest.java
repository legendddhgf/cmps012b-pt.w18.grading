import java.lang.Throwable;
import java.io.*;

class ModelDictionaryTest {

  static int test_count;

  static int isEmpty_test1;
  static int size_test1;
  static int size_test2;
  static int lookup_test1;
  static int lookup_test2;
  static int lookup_test3;
  static int toString_test1;
  static int exception_test1;

  static String testName(int test) {

    if (test == isEmpty_test1) return "isEmpty_test1";
    if (test == size_test1) return "size_test1";
    if (test == size_test2) return "size_test2";
    if (test == lookup_test1) return "lookup_test1";
    if (test == lookup_test2) return "lookup_test2";
    if (test == lookup_test3) return "lookup_test2";
    if (test == toString_test1) return "toString_test1";
    if (test == exception_test1) return "exception_test1";

    return "";
  }

  public static int runTest(int test) {

    Dictionary A = new Dictionary();
    Dictionary B = new Dictionary();
    final int test_range = 10000;

    try {
      if(test == isEmpty_test1) {
        if (!A.isEmpty() || !B.isEmpty()) return 1;
        A.insert("test", "123");
        if (A.isEmpty() || !B.isEmpty()) return 2;
      } else if(test == size_test1) {
        if (A.size() != B.size()) return 1;
        A.insert("test", "123");
        if (A.size() == B.size()) return 2;
        B.insert("test", "123");
        if (A.size() != B.size()) return 3;
      } else if(test == size_test2) {
        for (int i = 0; i < test_range; i++) {
          A.insert(i + 1 + "", "test" + i + 1);
        }
        for (int i = 0; i < test_range / 10; i++) {
          B.insert(i + 1 + "", "test" + i + 1);
        }
        if (A.size() != test_range || B.size() != test_range / 10) return 1;
        for (int i = test_range / 10; i < test_range; i++) {
          B.insert(i + 1 + "", "test" + i + 1);
        }
        if (A.size() != B.size()) return 2;
        for (int i = test_range; i < test_range * 9 / 10; i++) {
          A.delete(i + 1 + "");
        }
        if (A.size() != test_range * 9 / 10 || B.size() != test_range) return 3;
        for (int i = test_range * 9 / 10; i > 0; i--) {
          A.delete(i + 1 + "");
        }
        B.makeEmpty();
        if (A.size() != 0 || B.size() != 0) return 4;
      } else if(test == lookup_test1) {
        return 1;
      } else if(test == lookup_test2) {
        return 1;
      } else if(test == lookup_test3) {
        return 1;
      } else if(test == toString_test1) {
        return 1;
      } else if(test == exception_test1) {
        return 1;
      }
    } catch (Exception e) {
      System.out.println("\nUnfortunately your program crashed on test " +
          testName(test) + " With an exception of:\n");
      e.printStackTrace();
      System.out.println();
      return 255;
    } catch (StackOverflowError se) {
      System.out.println("\nUnfortunately your program crashed on test " +
          testName(test) + " With a stack overflow error\n");
      return 255;
    }
    return 0;
  }

  public static void main(String args[]) {

    if (args.length > 1 || (args.length == 1 && !args[0].equals("-v"))) {
      System.err.println("Usage: ./ListTest [-v]");
      System.exit(1);
    }

    test_count = 0;

    isEmpty_test1 = test_count++;
    size_test1 = test_count++;
    size_test2 = test_count++;
    lookup_test1 = test_count++;
    lookup_test2 = test_count++;
    lookup_test3 = test_count++;
    toString_test1 = test_count++;
    exception_test1 = test_count++;

    int test_status = 0;
    int tests_passed = 0;
    System.out.println("\nList of tests passed/failed:\n");
    for (int i = 0; i < test_count; i++) {
      test_status = runTest(i);
      System.out.printf("%s %s", testName(i),
          test_status == 0 ? "PASSED" : "FAILED");
      if (test_status == 0) {
        System.out.printf("\n");
        tests_passed++;
      } else if (test_status == 255) {
        System.out.printf(": due to exception\n");
        break;
      } else {
        System.out.printf(": in test %d\n", test_status);
      }
    }

    if (test_status != 255) {
      System.out.printf("\n\nPassed %d tests out of %d possible\n",
          tests_passed, test_count);
    } else {
      System.out.printf("\n\nReceiving Charity points due to an exception\n");
    }

    final int maxScore = 80;
    final int charity = 5;
    final int pntspertest = maxScore / test_count;

    int totalPoints = (maxScore - test_count * pntspertest) +
      tests_passed * pntspertest;

    if (test_status == 255) { // your code had an exception
      totalPoints = charity;
    }

    System.out.printf("\nThis gives you a score of %d / %d " +
        "for this component of the assignment\n\n", totalPoints, maxScore);
  }
}

