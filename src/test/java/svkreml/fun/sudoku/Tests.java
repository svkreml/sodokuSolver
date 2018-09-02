package svkreml.fun.sudoku;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests {


    @Test
    public void test1_easy() {


        String input =
                "|3 4 -|- 6 9|- - 1|\n" +
                "|- - -|- - -|- 5 -|\n" +
                "|- - 8|- - -|7 - -|\n" +

                "|2 - -|- - 7|- 9 -|\n" +
                "|5 - -|2 - 3|- - 6|\n" +
                "|- 7 -|9 - -|- - 2|\n" +

                "|- - 3|- - -|1 - -|\n" +
                "|- 1 -|- - -|- - -|\n" +
                "|9 - -|3 1 -|- 2 7|\n";

        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), true);

    }

    @Test
    public void test1_hard() {
        String input =
                "|7 - -|2 1 -|- - -|\n" +
                "|8 - -|- - 3|- - -|\n" +
                "|- - 1|- - -|4 5 2|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                "|- - -|- 2 7|- - -|\n" +
                "|- 5 3|- 8 -|2 9 -|\n" +
                "|- - -|6 9 -|- - -|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                "|9 8 7|- - -|1 - -|\n" +
                "|- - -|7 - -|- - 6|\n" +
                "|- - -|- 4 1|- - 5|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), true);
    }

    @Test
    public void test2_hard() {
        String input =
                "|1 - 2|- 5 7|9 - -|\n" +
                "|- - -|- 2 -|- - -|\n" +
                "|- - -|- - -|6 8 -|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                "|- - 1|3 7 -|- - -|\n" +
                "|2 - -|6 - 4|- - 8|\n" +
                "|- - -|- 8 5|1 - -|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                "|- 7 9|- - -|- - -|\n" +
                "|- - -|- 4 -|- - -|\n" +
                "|- - 6|5 3 -|4 - 7|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), true);
    }

    @Test
    public void test3_hard() {
        String input =
                      "|- 3 -|- 2 -|1 - -|\n" +
                      "|- - -|3 - -|7 5 -|\n" +
                      "|- - -|- - -|- 9 -|\n" +
                      "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                      "|7 - -|- 3 1|8 6 -|\n" +
                      "|- - -|8 - 5|- - -|\n" +
                      "|- 8 3|2 9 -|- - 1|\n" +
                      "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                      "|- 7 -|- - -|- - -|\n" +
                      "|- 4 9|- - 6|- - -|\n" +
                      "|- - 5|- 8 -|- 2 -|\n" +
                      "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), true);
    }

    @Test
    public void test4_hard_false() {
        String input =
                "|- 3 -|- 2 -|1 - -|\n" +
                "|- - -|3 - -|7 5 -|\n" +
                "|- - -|- - -|- 9 -|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                "|7 - -|- 3 1|8 - -|\n" +
                "|- - -|8 - 5|- - -|\n" +
                "|- 8 3|2 9 -|- - 1|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                "|- 7 -|- - -|- - -|\n" +
                "|- 4 9|- - 6|- - -|\n" +
                "|- - 5|- 8 -|- 2 -|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), false, "убрана одна ячейка, на данный момент не решается");
    }

    @Test
    public void test5_hard_false() {
        String input =
                "|- 3 -|- 2 -|1 - -|\n" +
                "|- - -|3 - -|7 5 -|\n" +
                "|- - -|- - -|- 9 -|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                "|7 - -|- 3 1|8 - -|\n" +
                "|- - -|8 - 5|- 6 -|\n" +
                "|- 8 3|2 9 -|- - 1|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                "|- 7 -|- - -|- - -|\n" +
                "|- 4 9|- - 6|- - -|\n" +
                "|- - 5|- 8 -|- 2 -|\n" +
                "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), false, " неправильно стоит цифра, нерешаемо");
    }

    @Test
    public void test1_evil() {
        String input =

                        "|- - -|1 7 -|- - -|\n" +
                        "|8 - -|- - 4|9 - -|\n" +
                        "|4 - -|- - -|- 2 3|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                        "|- - 7|- - 1|- 3 -|\n" +
                        "|- 8 -|- 6 -|- 4 -|\n" +
                        "|- 1 -|9 - -|7 - -|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                        "|6 5 -|- - -|- - 7|\n" +
                        "|- - 2|3 - -|- - 6|\n" +
                        "|- - -|- 9 7|- - -|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), true);
    }

    @Test
    public void test2_evil() {
        String input =

                        "|- - -|4 - -|2 - -|\n" +
                        "|6 9 8|- - 2|- - 1|\n" +
                        "|- - -|- - 7|- - -|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                        "|2 3 -|7 - -|8 - -|\n" +
                        "|- - -|- - -|- - -|\n" +
                        "|- - 4|- - 8|- 2 6|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                        "|- - -|5 - -|- - -|\n" +
                        "|4 - -|8 - -|9 5 3|\n" +
                        "|- - 6|- - 1|- - -|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), true);
    }

    @Test
    public void test3_evil() {
        String input =

                        "|- - -|- 2 -|- - 9|\n" +
                        "|- - 5|- - 4|2 - -|\n" +
                        "|3 - -|- - 9|- - 5|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                        "|9 - -|- - -|8 - -|\n" +
                        "|1 - 2|- 8 -|9 - 6|\n" +
                        "|- - 6|- - -|- - 4|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
                        "|6 - -|7 - -|- - 1|\n" +
                        "|- - 4|9 - -|6 - -|\n" +
                        "|8 - -|- 3 -|- - -|\n" +
                        "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        SodokuSolver field = new SodokuSolver(input);
        field.printField();
        field.solve();
        Assert.assertEquals(field.printField(), true);
    }
}
