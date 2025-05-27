package com.quipux.automatizacion;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GmailAutomationTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().driverVersion("136.0.7103.113").setup();
        WebDriver driver = new ChromeDriver();

        // Declarar WebDriverWait desde el inicio
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //Maximize the current window
        driver.manage().window().maximize();


        try {
            driver.get("https://accounts.google.com/signin");

            // Esperar y enviar email
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));
            emailInput.sendKeys("TU CORREO");
            driver.findElement(By.id("identifierNext")).click();


            // Esperar y enviar contraseña
            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
            passwordInput.sendKeys("TU CONTRASEÑA");
            System.out.println(passwordInput);


            // Luego puedes continuar con el clic en "Siguiente" para la contraseña
            driver.findElement(By.id("passwordNext")).click();
            System.out.println("Iniciando espera");

            Thread.sleep(3000);
            System.out.println("Espera terminada");
            // Ir directamente a Gmail
            driver.get("https://mail.google.com");
            System.out.println("Ingresando al correo");


            // Esperar a que el botón "Redactar" (Compose) esté disponible
            WebElement composeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div[1]/div[1]/div/div")));
            composeBtn.click();

            // CAMPO DESTINATARIO
            WebElement paraInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='agP aFw']")));
            paraInput.sendKeys("CORREO DESTINATARIO");

            // CAMPO ASUNTO
            WebElement asuntoInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("subjectbox")));
            asuntoInput.sendKeys("Asunto de prueba automatizada");
            JavascriptExecutor js = (JavascriptExecutor) driver;


            // CUERPO DEL MENSAJE
            WebElement cuerpo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Am aiL Al editable LW-avf tS-tW']")));
            js.executeScript("arguments[0].textContent = arguments[1];", cuerpo, "Hola,\nEste es un correo automatizado con Selenium.\nSaludos.");

            // Adjuntar archivo desde Drive
            WebElement driveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='aA7 aaA aMZ']")));
            driveButton.click();

            // Cambiar al iframe de Google Drive
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@allow=\"camera 'src' https://docs.google.com\"]")));

            WebElement archivo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='eizQhe-ObfsIf-mJRMzd-PFprWc-haAclf']")));
            archivo.click();

            WebElement insertarBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 xFWpbf CZCFtc-bMElCd sj692e RCmsv jbArdc oWBWHf']")));
            insertarBtn.click();

            // Volver al contenido principal
            driver.switchTo().defaultContent();

            // ENVIAR CORREO
            WebElement enviarBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")));
            enviarBtn.click();

            System.out.println("Correo enviado correctamente.");
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}