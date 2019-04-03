package tests;

import static  org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","/Users/raphael.silva.mendes/projetos/driver/chromedriver");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit");

        //clicar no link que possui o texto "Sing in"
        navegador.findElement(By.linkText("Sign in")).click();

        //cindentificando os passos de login "Singinbox"

        WebElement formularioSingInBox = navegador.findElement(By.id("signinbox"));

        //digitar no campo de name "login" o texto "julio0001"
        formularioSingInBox.findElement(By.name("login")).sendKeys("julio0001");

        //digitar no campo com name "password" que esta dentro de formulario de id "singinbox" o texto "123456"
        formularioSingInBox.findElement(By.name("password")).sendKeys("123456");


        //clicar no link com o texto "Sing In"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //validar que dentro do elemento como class "me" esta o texto "Hi, Julio"
        WebElement me = navegador.findElement(By.className("me"));
        String textoelementome = me.getText();

        //clicar no link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //clicar no link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }

    //@Test
    public void testAdicionarumaInfomacaoAdicionaldoUsuario() {



        //clicar no botao atravez do seu Xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar a popup onde esta o formulario de id addmoredata

        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opcao "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        //No combo de name "contact" digitar "+551199999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys("559292199999");


        //clicar no link de text "SAVE" que esta na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" e validar que o texto e "Your contact has been added!"

        WebElement popupMensage = navegador.findElement(By.id("toast-container"));
        String mensagem = popupMensage.getText();
        assertEquals("Your contact has been added!",mensagem);

               }
         @Test
         public void removerUmContatoDeUmUsuario(){

       //Identificar o telefone +559292198643, elemento pelo seu Xpath: //span[text()='+559292198643']/following-sibling::a
             navegador.findElement(By.xpath("//span[text()='+559292198643']/following-sibling::a")).click();

       //Confirmar a janela de java script

             navegador.switchTo().alert().accept();

       //validar que a mensagem apresentado foi Rest in peace, dear phone!

             WebElement popupMensage = navegador.findElement(By.id("toast-container"));
             String mensagem = popupMensage.getText();
             assertEquals("Rest in peace, dear phone!",mensagem);


       //aguardar ate 10 segundos para que a janela desapareça, com espera explicita
             WebDriverWait aguardar = new WebDriverWait(navegador, 10);
              aguardar.until(ExpectedConditions.stalenessOf(popupMensage));


       //fazer o logout , clicar no link logout
            navegador.findElement(By.linkText("Logout")).click();

         }

        @After
                public void tearDown() {
            //fechar o navegador
           navegador.quit();
        }

}
