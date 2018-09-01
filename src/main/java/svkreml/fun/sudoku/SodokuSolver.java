package svkreml.fun.sudoku;

import java.util.*;

public class SodokuSolver {

    Integer[][] field = new Integer[9 + 1][9 + 1];
    boolean stop = false;
    int iter = 0;
    public SodokuSolver() {
    }
    public SodokuSolver(String input) {
        Scanner s = new Scanner(input);

        for (int y = 1; y <= 9; y++) {
            String line = s.nextLine();
            for (int x = 1; x <= 9; x++) {
                if (line.charAt(x - 1) == '-') continue;
                //if (line.charAt(x) >= '1' && line.charAt(x) <= '9') throw new InputMismatchException();
                field[x][y] = line.charAt(x - 1) - '0';
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
                for (int i = 1 + xOffset*3; i <= 3 + xOffset*3; i++)
                    for (int j = 1 + yOffset*3; j <= 3 + yOffset*3; j++) {
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

    public void printField() {
        System.out.println("------------------");
        for (int y = 1; y <= 9; y++) {
            System.out.print("|");
            for (int x = 1; x <= 9; x++) {
                if (getCell(x, y) == null)
                    System.out.print(" ");
                else
                    System.out.print(getCell(x, y));
                if (x % 3 == 0) System.out.print("|");
                else System.out.print(" ");
            }

            System.out.println();
            if (y % 3 == 0) System.out.println("------------------");
        }

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
                    return;
                } else if (poss.size() > 1) {

                    a = tacticB(x, y, poss, a);

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
    private int tacticB(int x, int y, Vector<Integer> poss, int a) {


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
                return a + 1;
            }
        }
        return a;
    }


}