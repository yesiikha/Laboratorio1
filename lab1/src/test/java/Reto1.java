import java.security.SecureRandom;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Select;
//import java.security.SecureRandom;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class Reto1 {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Establecer la ubicación del controlador de Chrome.
        System.setProperty("webdriver.chrome.driver", "C:/Users/yleon/Documents/ProyectsLeadT/TrabajosYessica/chromedriver-win64/chromedriver.exe");

        // Inicializar el navegador
        driver = new ChromeDriver();
    }

    @Test
    public void testForm() {
        // Navegar a la página
        driver.get("https://colorlib.com/polygon/metis/form-general.html");
        
        // Mapeo campo ID
        WebElement campoID = driver.findElement(By.id("text1"));
        campoID.sendKeys("Laboratorio_1");

        // Mapeo campo ID
        WebElement campoPass = driver.findElement(By.id("pass1"));
        String randomPassword = generateRandomPassword(10); // Generar la contraseña aleatoria
        campoPass.sendKeys(randomPassword); // Establecer la contraseña aleatoria en el campo

        //Mapeo campo XPath 
        WebElement deleteButton1 = driver.findElement(By.xpath("//*[@id='tags_tagsinput']/span[1]/a")); // Buscar el primer botón de eliminación
        deleteButton1.click();

        //Mapeo campo cssSelector
        WebElement deleteButton = driver.findElement(By.cssSelector("#tags_tagsinput > span:nth-child(2) > a"));
        deleteButton.click();

        //Mapeo campo cssSelector
        WebElement numericInput = driver.findElement(By.cssSelector("input.form-control[type='number']"));
        numericInput.clear();
        numericInput.sendKeys("15");


        //Mapeo campo className
        List<WebElement> checkboxes = driver.findElements(By.className("uniform"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected() && checkbox.isEnabled()) {
                checkbox.click();
            }
        }

        // Mapeo por texto parcial
        WebElement colorPicker = driver.findElement(By.partialLinkText("Change background color"));
        colorPicker.click();
        String color = "rgb(255, 0, 0)";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('data-color', arguments[1]);", colorPicker, color);
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

    @AfterAll
    public static void tearDown() {
        // Cerrar el navegador
        driver.quit();
    }
}
