package svkreml.fun.sudoku;

import org.testng.annotations.Test;

public class Tests {

    @Test
    public void test2() {


        String input =
                        "21-739-86\n" +
                        "--5---4--\n" +
                        "7---4---1\n" +
                        "-7-359-6-\n" +
                        "4-------2\n" +
                        "-5-214-9-\n" +
                        "9---3---8\n" +
                        "--6---9--\n" +
                        "34-982-15\n";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        field.printField();
    }
    @Test
    public void test3() {


        String input =
                        "-4-7--35-\n" +
                        "-2-8-----\n" +
                        "5--61---7\n" +
                        "----8--12\n" +
                        "1-------9\n" +
                        "85--2----\n" +
                        "3---79--6\n" +
                        "-----8-2-\n" +
                        "-68--4-9-\n";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        field.printField();
        /*
        * ------------------
|6 4 1|7 9 2|3 5 8|
|7 2 3|8 4 5|9 6 1|
|5 8 9|6 1 3|2 4 7|
------------------
|4 9 6|3 8 7|5 1 2|
|1 3 2|4 5 6|8 7 9|
|8 5 7|9 2 1|6 3 4|
------------------
|3 1 5|2 7 9|4 8 6|
|9 7 4|5 6 8|1 2 3|
|2 6 8|1 3 4|7 9 5|
------------------
        * */
    }

    @Test
    public void test4() {


        String input =
                        "6-7-1----\n" +
                        "9--37----\n" +
                        "-----6-34\n" +
                        "3-----54-\n" +
                        "-6-----1-\n" +
                        "-92-----7\n" +
                        "27-9-----\n" +
                        "----65--2\n" +
                        "----3-8-1\n";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        field.printField();

    }
}
