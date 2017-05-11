package gov.sba.utils.integration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighLight {
  public static void highLightElement(WebDriver webDriver, WebElement element)
      throws InterruptedException {
    JavascriptExecutor js = (JavascriptExecutor) webDriver;

    js.executeScript(
        "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
        element);
    Thread.sleep(1000);

    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {

      System.out.println(e.getMessage());
    }

    js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

  }

}
