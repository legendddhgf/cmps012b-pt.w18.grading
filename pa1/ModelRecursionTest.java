import java.util.BitSet;

class ModelListTest {

  static int test_count;

  static boolean verbose;

  static int Empty_length;
  static int Append_length;
  static int Prepend_length;
  static int InsertBefore_length;
  static int DeleteFront_equals;

  static String testName(int test) {
    if (test == Empty_length) return "Empty_length";
    if (test == Append_length) return "Append_length";
    if (test == Prepend_length) return "Prepend_length";
    if (test == InsertAfter_length) return "InsertAfter_length";
    if (test == InsertBefore_length) return "InsertBefore_length";
    if (test == DeleteFront_length) return "DeleteFront_length";
    if (test == DeleteBack_length) return "DeleteBack_length";
    if (test == Delete_length) return "Delete_length";

    if (test == EmptyList_index) return "EmptyList_index";
    if (test == MoveFront_index) return "MoveFront_index";
    if (test == MoveBack_index) return "MoveBack_index";
    if (test == MoveNext_index) return "MoveNext_index";
    if (test == MovePrev_index) return "MovePrev_index";
    if (test == Append_index) return "Append_index";
    if (test == Prepend_index) return "Prepend_index";
    if (test == InsertAfter_index) return "InsertAfter_index";
    if (test == InsertBefore_index) return "InsertBefore_index";
    if (test == DeleteFront_index) return "DeleteFront_index";
    if (test == DeleteBack_index) return "DeleteBack_index";
    if (test == Delete_index) return "Delete_index";

    if (test == Append_equals) return "Append_equals";
    if (test == Prepend_equals) return "Prepend_equals";
    if (test == InsertAfter_equals) return "InsertAfter_equals";
    if (test == InsertBefore_equals) return "InsertBefore_equals";
    if (test == DeleteFront_equals) return "DeleteFront_equals";
    if (test == DeleteBack_equals) return "DeleteBack_equals";
    if (test == Delete_equals) return "Delete_equals";

    if (test == Empty_clear) return "Empty_clear";
    if (test == NonEmpty_clear) return "NonEmpty_clear";

    if (test == Set_get) return "Set_get";
    if (test == Set_front) return "Set_front";
    if (test == NonEmpty_front) return "NonEmpty_front";
    if (test == Set_back) return "Set_back";
    if (test == NonEmpty_back) return "NonEmpty_back";

    if (test == Empty_copy) return "Empty_copy";
    if (test == NonEmpty_copy) return "NonEmpty_copy";

    if (test == Empty_toString) return "Empty_toString";
    if (test == NonEmpty_toString) return "NonEmpty_toString";

    return "";
  }

  public static int runTest(int test) {

    int A[] = new int[10000];
    int B[] = new int[10000];

    try {
      if (test == Empty_length) {
        A = new List();
        if (A.length() != 0) return 1;
      } else if (test == Append_length) {
        A = new List();
        A.append(1);
        A.append(2);
        A.append(3);
        A.append(5);
        if (A.length() != 4) return 1;
      } else if (test == Prepend_length) {
        A = new List();
        A.prepend(6);
        A.prepend(4);
        A.prepend(2);
        A.prepend(1);
        if (A.length() != 4) return 1;
      } else if (test == InsertAfter_length) {
        A = new List();
        A.append(1);
        A.append(2);
        A.append(3);
        A.append(5);
        A.moveFront();
        A.insertAfter(12);
        if (A.length() != 5) return 1;
      } else if (test == InsertBefore_length) {
        A = new List();
        A.prepend(76);
        A.prepend(4);
        A.prepend(3);
        A.prepend(1);
        A.moveFront();
        A.insertBefore(115);
        if (A.length() != 5) return 1;
      } else if (test == DeleteFront_length) {
        A = new List();
        A.prepend(76);
        A.prepend(4);
        A.deleteFront();
        A.prepend(3);
        A.prepend(1);
        A.moveFront();
        A.insertBefore(115);
        A.deleteFront();
        if (A.length() != 3) return 1;
      }
    } catch (Exception e) {
      if (verbose) {
        System.out.println("\nUnfortunately your program crashed on test " +
            testName(test) + " With an exception of:\n");
        e.printStackTrace();
        System.out.println();
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
    Empty_length = test_count++;
    Append_length = test_count++;
    Prepend_length = test_count++;
    InsertAfter_length = test_count++;
    InsertBefore_length = test_count++;
    DeleteFront_length = test_count++;
    DeleteBack_length = test_count++;
    Delete_length = test_count++;

    EmptyList_index = test_count++;
    MoveFront_index = test_count++;
    MoveBack_index = test_count++;
    MoveNext_index = test_count++;
    MovePrev_index = test_count++;
    Append_index = test_count++;
    Prepend_index = test_count++;
    InsertAfter_index = test_count++;
    InsertBefore_index = test_count++;
    DeleteFront_index = test_count++;
    DeleteBack_index = test_count++;
    Delete_index = test_count++;

    Append_equals = test_count++;
    Prepend_equals = test_count++;
    InsertAfter_equals = test_count++;
    InsertBefore_equals = test_count++;
    DeleteFront_equals = test_count++;
    DeleteBack_equals = test_count++;
    Delete_equals = test_count++;

    Empty_clear = test_count++;
    NonEmpty_clear = test_count++;

    Set_get = test_count++;
    Set_front = test_count++;
    NonEmpty_front = test_count++;
    Set_back = test_count++;
    NonEmpty_back = test_count++;

    Empty_copy = test_count++;
    NonEmpty_copy = test_count++;

    Empty_toString = test_count++;
    NonEmpty_toString = test_count++;

    int tests_passed = 0;
    if (verbose)
      System.out.println("\nList of tests passed/failed:\n");
    for (int i = 0; i < test_count; i++) {
      int test_status = runTest(i);
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

    int totalPoints = (maxScore - test_count) + tests_passed;
    if (test_status == 255) { // your code had an exception
      totalPoints = charity;
    }

    System.out.printf("\nThis gives you a score of %d out of %d " +
        "for this component of the assignment\n\n", totalPoints, maxScore);
  }
}

