package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import Model.ConnectionSingleton;
import org.junit.jupiter.api.Test;

/**
 * Classe Tester di ConnectionSingleton.java.
 *
 * @author Farina Simone
 *
 */
public class TestClassConnectionSingleton {

  @Test
  //Test del metodo getIstance di ConnectionSingleton.
  void testgetInstance() {
    System.out.println("Test di getIstance di connectionSingleton.");
    ConnectionSingleton db = ConnectionSingleton.getInstance();
    ConnectionSingleton db1 = ConnectionSingleton.getInstance();
    assertEquals(db, db1);
  }

  //Test dei metodi SET
  @Test
  void testSetterConnectionSingleton() {
    System.out.println("Test dei setter di ConnectionSingleton.java");
    ConnectionSingleton db = new ConnectionSingleton();
    db.setNomeDatabase("dodo");
    assertEquals("dodo", db.getNomeDatabase());
    
    db.setNomeUser("root");
    assertEquals("root", db.getNomeUser());
    
    db.setPassword("admin");
    assertEquals("admin", db.getPassword());
    
    db.setPortaHost(3306);
    assertEquals(3306, db.getPortaHost());

    db.setNomeHost("localhost");
    assertEquals("localhost", db.getNomeHost());
  }
  
  @Test
  //Metodo test per setConn di ConnectionSingleton.
  void testsetConn() throws Exception {
    System.out.println("Test setConn() di Connection Singleton.");
    new ConnectionSingleton().setConnessione(null);
  }
  
  //Test dei metodi GET
  @Test
  void testGetterConnectionSingleton() {
    System.out.println("test dei getter di ConnectionSingleton");
    ConnectionSingleton db = new ConnectionSingleton();
    assertEquals("dodo", db.getNomeDatabase());
    assertEquals("root", db.getNomeUser());
    assertEquals("admin", db.getPassword());
    assertEquals(3306, db.getPortaHost());
    assertEquals("localhost", db.getNomeHost());
    assertNotEquals(null, db.getConnessione());
  }
}

