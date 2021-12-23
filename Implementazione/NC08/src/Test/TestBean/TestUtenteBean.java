package Test.TestBean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Bean.UtenteBean;

/**
 * Classe Tester per UtenteBean.java.
 *
 * @author Alfonso Cuomo
 *
 */

class TestUtenteBean {

  @Test
  //Metodo che testa il cotruttore della classe UtenteBean.java
  void testingCostruttoreUtenteBean() {
    System.out.println("Testing Costruttore UtenteBean.java");
    UtenteBean exampleUtenteBean = new UtenteBean("mail", "password", "nickname", false);
    assertEquals("mail", exampleUtenteBean.getMail());
    assertEquals("password", exampleUtenteBean.getPassword());
    assertEquals("nickname", exampleUtenteBean.getNickname());
    assertEquals(false, exampleUtenteBean.isAdmin());
  }

  @Test
  //Metodo che testa i setter della classe UtenteBean.java
  void  testingSetterUtenteBean() {
    System.out.println("Testing Setter UtenteBean.java");
    UtenteBean exampleUtenteBean = new UtenteBean();

    String mail = "mail";
    exampleUtenteBean.setMail(mail);

    String nickname = "nickname";
    exampleUtenteBean.setNickname(nickname);

    String password = "password";
    exampleUtenteBean.setPassword(password);

    boolean isAdmin = true;
    exampleUtenteBean.setAdmin(isAdmin);

    boolean isNoAdmin = false;
    exampleUtenteBean.setAdmin(isNoAdmin);
  }

  @Test
  //Metodo che testa i getter della classe UtenteBean.java
  void testerGetterUtenteBean() {
    System.out.println("Testing testerGetterUtenteBean()..");
    UtenteBean exampleUtenteBean = new UtenteBean();
    String nicknameExpResult = "nickname";
    exampleUtenteBean.setNickname(nicknameExpResult);
    String resultNickname = exampleUtenteBean.getNickname();
    assertEquals(nicknameExpResult, resultNickname);

    String passwordExpResult = "password";
    exampleUtenteBean.setPassword(passwordExpResult);
    String resultPassword = exampleUtenteBean.getPassword();
    assertEquals(passwordExpResult, resultPassword);

    String emailExpResult = "email";
    exampleUtenteBean.setMail(emailExpResult);
    String resultMail = exampleUtenteBean.getMail();
    assertEquals(emailExpResult, resultMail);

    boolean isAdminExpResult = false;
    exampleUtenteBean.setAdmin(isAdminExpResult);
    boolean resultIsAdmin = exampleUtenteBean.isAdmin();
    assertEquals(isAdminExpResult, resultIsAdmin);

  }
}
