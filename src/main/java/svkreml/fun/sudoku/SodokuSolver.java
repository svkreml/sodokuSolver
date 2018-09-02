package svkreml.fun.sudoku;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.Vector;

public class SodokuSolver {

    Integer[][] field = new Integer[9 + 1][9 + 1];
    boolean stop = false;
    int iter = 0;

    public SodokuSolver() {
    }

    public SodokuSolver(String input) {
        input = input.replaceAll("[^1234567890\\-]{1}", "");
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {
                char c = input.charAt((y - 1) * 9 + x - 1);
                if (c == '-') continue;
                //if (line.charAt(x) >= '1' && line.charAt(x) <= '9') throw new InputMismatchException();
                field[x][y] = c - '0';
            }
        }
    }

    private static int getOffset(int i) {
        int j = i - 1;
        return j - j % 3;
    }

    public boolean check() {
        for (int y = 1; y <= 9; y++) {
            Set<Integer> line = new HashSet<Integer>();
            for (int x = 1; x <= 9; x++) {
                if (getCell(x, y) != null) {
                    if (line.contains(getCell(x, y))) return false;
                    line.add(getCell(x, y));
                }
            }
        }

        for (int x = 1; x <= 9; x++) {
            Set<Integer> col = new HashSet<Integer>();
            for (int y = 1; y <= 9; y++) {
                if (getCell(x, y) != null) {
                    if (col.contains(getCell(x, y))) return false;
                    col.add(getCell(x, y));
                }
            }
        }
        for (int yOffset = 0; yOffset <= 2; yOffset++) {
            for (int xOffset = 0; xOffset <= 2; xOffset++) {
                Set<Integer> miniField = new HashSet<Integer>();
                for (int i = 1 + xOffset * 3; i <= 3 + xOffset * 3; i++)
                    for (int j = 1 + yOffset * 3; j <= 3 + yOffset * 3; j++) {
                        if (getCell(i, j) != null) {
                            if (miniField.contains(getCell(i, j)))
                                return false;
                            miniField.add(getCell(i, j));
                        }
                    }
            }
        }
        return true;
    }

    public void setCell(int x, int y, int value) {
        if (!((y >= 1) && (y <= 9))) throw new IndexOutOfBoundsException();
        if (!((x >= 1) && (x <= 9))) throw new IndexOutOfBoundsException();
        if (!((value >= 1) && (value < 10))) throw new IndexOutOfBoundsException();

        field[x][y] = value;
    }

    public boolean printField() {
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

    public Integer getCell(int x, int y) {
        if (!((y >= 1) && (y <= 9))) throw new IndexOutOfBoundsException();
        if (!((x >= 1) && (x <= 9))) throw new IndexOutOfBoundsException();
        return field[x][y];
    }

    private boolean checkMiniField(int x, int y, int value) {
        if (!((x >= 1) && (x <= 9))) throw new IndexOutOfBoundsException();
        if (!((y >= 1) && (y <= 9))) throw new IndexOutOfBoundsException();
        if (!((value >= 1) && (value <= 9))) throw new IndexOutOfBoundsException();

        int xOffset = getOffset(x);
        int yOffset = getOffset(y);
        for (int i = 1 + xOffset; i <= 3 + xOffset; i++)
            for (int j = 1 + yOffset; j <= 3 + yOffset; j++) {
                try {
                    if (field[i][j] == value) return false;
                } catch (Exception e) {
                }
            }
        return true;
    }

    private boolean checkLine(int x, int y, int value) {
        if (!((x >= 1) && (x <= 9))) throw new IndexOutOfBoundsException();
        if (!((y >= 1) && (y <= 9))) throw new IndexOutOfBoundsException();
        if (!((value >= 1) && (value < 10))) throw new IndexOutOfBoundsException();

        for (int i = 1; i <= 9; i++) {
            try {
                if (field[x][i] == value) return false;
            } catch (Exception e) {
            }
            try {
                if (field[i][y] == value) return false;
            } catch (Exception e) {
            }
        }
        return true;
    }

    public void solve() {

        if (!check())
            throw new InputMismatchException("Поле некорректно");
        while (!stop) {
            iter++;
/*            if(iter==12)
                System.out.println("break");*/
            loop();
            System.out.println(iter);
            printField();
            if (iter > 100000) {
                System.out.println("endless loop detected");
                break;
            }
        }
        System.out.println("iters = " + iter);
    }

    private void loop() {
        int a = 0;
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {


                Vector<Integer> poss = tacticA(x, y);
                if (poss.size() == 1) {
                    a++;
                    setCell(x, y, poss.firstElement());
                    System.out.println("tacticA, put (" + x + "," + y + ")=" + poss.firstElement());
                    continue;
                } else if (poss.size() > 1) {
                    if (tacticB(x, y, poss)) {
                        a++;
                        continue;
                    } else if (tacticC(x, y, poss)) {
                        a++;
                        continue;
                    }
                }
            }
        }
        if (a == 0) {
            stop = true;
        }
    }


    /*
     * Тактика А
     * Ищем что можно записать в данную ячейку, на выходе вектор вариантов, если результат только один, то его можно записать
     * */
    private Vector<Integer> tacticA(int x, int y) {
        Vector<Integer> poss = new Vector<Integer>();
        for (int i = 1; i <= 9; i++) {
            if (getCell(x, y) == null) {
                if (checkMiniField(x, y, i) && checkLine(x, y, i)) {
                    poss.add(i);
                }
            }
        }
        return poss;
    }


    /*
     * Тактика Б
     * Ищем куда ещё можно записать числа из массива в пределах малой таблицы - если больше некуда, то пишем в данную ячейку
     * */
    private boolean tacticB(int x, int y, Vector<Integer> poss) {
        for (Integer value : poss) {
            int xOffset = getOffset(x);
            int yOffset = getOffset(y);

            int alter = 0;
            for (int i = 1 + xOffset; i <= 3 + xOffset; i++)
                for (int j = 1 + yOffset; j <= 3 + yOffset; j++) {
                    if (i == x && j == y) continue;
                    if (getCell(i, j) != null) continue;
                    if (tacticA(i, j).contains(value))
                        alter++;
                }
            if (alter == 0) {
                System.out.println("tacticB, put (" + x + "," + y + ")=" + value);
                setCell(x, y, value);
                return true;
            }
        }
        return false;
    }

    /*
     * смотрим всю строку и столбец и если чтобы в строке были все цифры ставить больше некуда кроме как в это точку, то ставим
     * */
    private boolean tacticC(int x, int y, Vector<Integer> poss) {
        // System.out.println("tacticC, trying (" + x + "," + y + ")=" + poss);
        for (Integer value : poss) {
            int otherPossX = 0;
            for (int i = 1; i <= 9; i++) {
                if (i == x) continue;
                if (getCell(i, y) != null) continue;
                if (tacticA(i, y).contains(value)) otherPossX++;
            }
            //   System.out.println("tacticC, find " + otherPossX + " X possibilities");

            int otherPossY = 0;
            for (int j = 1; j <= 9; j++) {
                if (j == y) continue;
                if (getCell(x, j) != null) continue;
                if (tacticA(x, j).contains(value)) otherPossY++;
            }
            //   System.out.println("tacticC, find " + otherPossY + " Y possibilities");
            if (otherPossY == 0 || otherPossX == 0) {
                setCell(x, y, value);
                System.out.println("tacticC, put (" + x + "," + y + ")=" + value);
                return true;
            }
        }

        return false;
    }
}