package svkreml.fun.sudoku;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class CellChecker {


    Field field;

    public CellChecker(Field field) {
        this.field = field;
    }

    public boolean check() {

        for (int y = 1; y <= 9; y++) {
            Set<Integer> line = new HashSet<>();
            Set<Integer> col = new HashSet<>();
            for (int x = 1; x <= 9; x++) {
                if (field.getCell(x, y) != null) {
                    if (line.contains(field.getCell(x, y))) return false;
                    line.add(field.getCell(x, y));
                }
                if (field.getCell(y, x) != null) {
                    if (col.contains(field.getCell(y, x))) return false;
                    col.add(field.getCell(y, x));
                }
            }
        }
        // проверка, что в каждом мини поле нет повторов
        for (int yOffset = 0; yOffset <= 2; yOffset++) {
            for (int xOffset = 0; xOffset <= 2; xOffset++) {
                Set<Integer> miniField = new HashSet<Integer>();
                for (int i = 1 + xOffset * 3; i <= 3 + xOffset * 3; i++)
                    for (int j = 1 + yOffset * 3; j <= 3 + yOffset * 3; j++) {
                        if (field.getCell(i, j) != null) {
                            if (miniField.contains(field.getCell(i, j)))
                                return false;
                            miniField.add(field.getCell(i, j));
                        }
                    }
            }
        }
        return true;
    }

    private boolean checkMiniField(int x, int y, int value) {
        if (!((x >= 1) && (x <= 9))) throw new IndexOutOfBoundsException();
        if (!((y >= 1) && (y <= 9))) throw new IndexOutOfBoundsException();
        if (!((value >= 1) && (value <= 9))) throw new IndexOutOfBoundsException();

        int xOffset = Field.getOffset(x);
        int yOffset = Field.getOffset(y);
        for (int i = 1 + xOffset; i <= 3 + xOffset; i++)
            for (int j = 1 + yOffset; j <= 3 + yOffset; j++) {
                try {
                    if (field.getCell(i, j) == value) return false;
                } catch (Exception e) {
                }
            }
        return true;
    }

    private boolean checkLines(int x, int y, int value) {
        if (!((x >= 1) && (x <= 9))) throw new IndexOutOfBoundsException();
        if (!((y >= 1) && (y <= 9))) throw new IndexOutOfBoundsException();
        if (!((value >= 1) && (value < 10))) throw new IndexOutOfBoundsException();

        for (int i = 1; i <= 9; i++) {
            try {
                if (field.getCell(x, i) == value) return false;
            } catch (Exception e) {
            }
            try {
                if (field.getCell(i, y) == value) return false;
            } catch (Exception e) {
            }
        }
        return true;
    }

    public Vector<Integer> getCellPossibilities(int x, int y) {
        Vector<Integer> poss = new Vector<Integer>();
        for (int i = 1; i <= 9; i++) {
            if (field.getCell(x, y) == null) {
                if (checkMiniField(x, y, i) && checkLines(x, y, i)) {
                    poss.add(i);
                }
            }
        }
        return poss;
    }

}
