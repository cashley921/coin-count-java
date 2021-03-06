import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("How many monies do you have?");
  }

  @Test
    public void inputTest() {
      goTo("http://localhost:4567");
      fill("#cents").with("117");
      submit(".btn");
      assertThat(pageSource()).contains("117 cent monies! AKA 4 quarters, 1 dime, 1 nickel, and 2 pennies.");
    }
}
