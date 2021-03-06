package svkreml.fun.sudoku;

import java.util.InputMismatchException;
import java.util.Vector;

public class SodokuSolver {

    Field field;
    CellChecker cellChecker;
    boolean stop = false;
    int iter = 0;

    public SodokuSolver(Field field) {
        this.field = field;
        cellChecker = new CellChecker(field);
    }

    public boolean solve() {
        boolean print = false;
        if (!cellChecker.check())
            throw new InputMismatchException("Поле некорректно");
        while (!stop) {
            iter++;
            loop();
            System.out.println(iter);
            print = field.print();
            if (iter > 100000) {
                System.out.println("endless loop detected");
                break;
            }
        }
        System.out.println("iters = " + iter);
        return print;
    }

    private void loop() {
        int a = 0;
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {
                Vector<Integer> poss = cellChecker.getCellPossibilities(x, y);
                if (poss.size() == 1) {
                    a++;
                    field.setCell(x, y, poss.firstElement());
                    System.out.println("getCellPossibilities, put (" + x + "," + y + ")=" + poss.firstElement());
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
     * Тактика Б
     * Ищем куда ещё можно записать числа из массива в пределах малой таблицы - если больше некуда, то пишем в данную ячейку
     * */
    private boolean tacticB(int x, int y, Vector<Integer> poss) {
        for (Integer value : poss) {
            int xOffset = Field.getOffset(x);
            int yOffset = Field.getOffset(y);

            Vector<Integer> alters = new Vector<>();
            if (getAlters(x, y, value, xOffset, yOffset)) {
                alters.add(value);
            }
            if (alters.isEmpty()) {
                System.out.println("tacticB, put (" + x + "," + y + ")=" + value);
                field.setCell(x, y, value);
                return true;
            }else {
                // тут можно добавить ветвление
            }
        }
        return false;
    }

    private boolean getAlters(int x, int y, Integer value, int xOffset, int yOffset) {
        for (int i = 1 + xOffset; i <= 3 + xOffset; i++)
            for (int j = 1 + yOffset; j <= 3 + yOffset; j++) {
                if (i == x && j == y) continue;
                if (field.getCell(i, j) != null) continue;
                if (cellChecker.getCellPossibilities(i, j).contains(value))
                    return true;
            }
        return false;
    }

    /*
     * смотрим всю строку и столбец и если чтобы в строке были все цифры ставить больше некуда кроме как в это точку, то ставим
     * */
    private boolean tacticC(int x, int y, Vector<Integer> poss) {
        for (Integer value : poss) {
            Vector<Integer> otherPossX = new Vector<>();
            for (int i = 1; i <= 9; i++) {
                if (i == x) continue;
                if (field.getCell(i, y) != null) continue;
                if (cellChecker.getCellPossibilities(i, y).contains(value)) otherPossX.add(value);
            }
            Vector<Integer> otherPossY = new Vector<>();
            for (int j = 1; j <= 9; j++) {
                if (j == y) continue;
                if (field.getCell(x, j) != null) continue;
                if (cellChecker.getCellPossibilities(x, j).contains(value)) otherPossY.add(value);
            }
            if (otherPossY.isEmpty() || otherPossX.isEmpty()) {
                field.setCell(x, y, value);
                System.out.println("tacticC, put (" + x + "," + y + ")=" + value);
                return true;
            }
        }
        return false;
    }
}