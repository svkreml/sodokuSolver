package svkreml.fun.sudoku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;

public class Solver {
    public static void main(String[] args) throws InterruptedException {
        MyWebDriver myWebDriver = null;
        try {
            myWebDriver = new MyWebDriver();


            myWebDriver.get("https://www.websudoku.com/");
            Thread.sleep(1000);
            WebDriver frame = myWebDriver.chromeDriver.switchTo().frame(0);
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

        SodokuSolver sodokuSolver = new SodokuSolver();


        Iterator<WebElement> iterator = elements.iterator();
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {
                try {
                    String value = iterator.next().getAttribute("value");
                    if (value.isEmpty()) continue;
                    sodokuSolver.setCell(x, y, Integer.parseInt(value));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        sodokuSolver.printField();
        sodokuSolver.solve();

        Iterator<WebElement> iterator2 = elements.iterator();
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {
                try {
                    WebElement webElement = iterator2.next();
                    String value = webElement.getAttribute("value");
                    if (!value.isEmpty()) continue;
                    webElement.sendKeys(sodokuSolver.getCell(x, y).toString());
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("решение проблемы плохой загрузки не предусмотрено");
        }
    }
}
