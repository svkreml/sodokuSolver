package svkreml.fun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import svkreml.fun.sudoku.Field;
import svkreml.fun.sudoku.SodokuSolver;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Solver {
    public static void main(String[] args) {
        MyWebDriver myWebDriver = null;
        try {
            myWebDriver = new MyWebDriver();


            myWebDriver.get("https://www.websudoku.com/?level=2");
            Thread.sleep(1000);
            myWebDriver.chromeDriver.switchTo().frame(0);// ервый взгляд ничего не проиходит, но содержимое странице во фрейме и без этой строчки содержимое фрейма недоступно
            for (int i = 0; i < 10; i++) {
                loop(myWebDriver);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            myWebDriver.chromeDriver.quit();
        }
    }

    private static void loop(MyWebDriver myWebDriver) throws InterruptedException {
        myWebDriver.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='puzzle_grid']/tbody/tr/td/input")));
        List<WebElement> elements = myWebDriver.chromeDriver.findElements(By.xpath("//table[@id='puzzle_grid']/tbody/tr/td/input"));

        Field field = new Field();


        Iterator<WebElement> iterator = elements.iterator();
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {
                try {
                    String value = iterator.next().getAttribute("value");
                    if (value.isEmpty()) continue;
                    field.setCell(x, y, Integer.parseInt(value));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        SodokuSolver sodokuSolver = new SodokuSolver(field);
        sodokuSolver.solve();

        Iterator<WebElement> iterator2 = elements.iterator();
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {
                try {
                    WebElement webElement = iterator2.next();
                    String value = webElement.getAttribute("value");
                    if (!value.isEmpty()) continue;
                    webElement.sendKeys(field.getCell(x, y).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep(2000);
            myWebDriver.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value=' How am I doing? ']")));
            myWebDriver.chromeDriver.findElement(By.xpath("//input[@value=' How am I doing? ']")).click();
            Thread.sleep(2000);

            myWebDriver.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value=' Bring on a new puzzle! ']")));
            myWebDriver.chromeDriver.findElement(By.xpath("//input[@value=' Bring on a new puzzle! ']")).click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("решение проблемы плохой загрузки не предусмотрено");
        }
    }
}
