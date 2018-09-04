package svkreml.fun.sudoku;

import java.util.InputMismatchException;

public class Field {
    private Integer[][] field = new Integer[9 + 1][9 + 1];

    public Field(String input) {
        input = input.replaceAll("[^1234567890\\-]{1}", "");
        if (input.length() != 81) throw new InputMismatchException("должна быть ровно 81 цифра");
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {
                char c = input.charAt((y - 1) * 9 + x - 1);
                if (c == '-') continue;
                field[x][y] = c - '0';
            }
        }
    }

    public Field() {
    }

    public static int getOffset(Integer i) {
        int j = i - 1;
        return j - j % 3;
    }

    public void setCell(int x, int y, int value) {
        if (!((y >= 1) && (y <= 9))) throw new IndexOutOfBoundsException();
        if (!((x >= 1) && (x <= 9))) throw new IndexOutOfBoundsException();
        if (!((value >= 1) && (value < 10))) throw new IndexOutOfBoundsException();

        field[x][y] = value;
    }

    public boolean print() {
        boolean solvedField = true;
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        for (int y = 1; y <= 9; y++) {
            System.out.print("|");
            for (int x = 1; x <= 9; x++) {
                if (getCell(x, y) == null) {
                    System.out.print("-");
                    solvedField = false;
                } else
                    System.out.print(getCell(x, y));
                if (x % 3 == 0) System.out.print("|");
                else System.out.print(" ");
            }
            System.out.println();
            if (y % 3 == 0) System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        }
        return solvedField;
    }

    public Integer getCell(Integer x, Integer y) {
        if (!((y >= 1) && (y <= 9))) throw new IndexOutOfBoundsException();
        if (!((x >= 1) && (x <= 9))) throw new IndexOutOfBoundsException();

        return field[x][y];
    }
}