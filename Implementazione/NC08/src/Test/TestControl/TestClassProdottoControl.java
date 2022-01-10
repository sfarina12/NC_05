package Test.TestControl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import Control.ProdottoControl;
import Control.ShopControl;

//Classe Di Test Non Specificata nel TCS
/**
* Classe Test per ProdottoControl.java.
*
*@author Alfonso Cuomo
*/
public class TestClassProdottoControl {

  private ProdottoControl servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  void setUp() throws Exception {

   servlet = new ProdottoControl();
   request = new MockHttpServletRequest();
   response = new MockHttpServletResponse();
  }

  @BeforeEach
  public void oneWaySetup() throws ServletException {
    ServletConfig sg = new MockServletConfig();
    servlet.init(sg);
  }

  @Test
  public void TestProdottoControl() throws ServletException, IOException {
   System.out.println("Testing (ProdottoControl)..");

    request.setAttribute("isbn", "123456789123");
    servlet.doGet(request, response);
	assertEquals("/Prodotto.jsp", response.getForwardedUrl());
  }
}
