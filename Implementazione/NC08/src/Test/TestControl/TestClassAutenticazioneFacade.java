package Test.TestControl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.test.annotation.Rollback;

import Control.AutenticazioneFacade;

class TestClassAutenticazioneFacade {
	
	private AutenticazioneFacade servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	//private MockHttpSession session;

	@BeforeEach
	  public void setUp() throws SQLException, ParseException {
	    servlet = new AutenticazioneFacade();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	   // request.setSession(session);
	  }
	
	@BeforeEach
	  public void oneWaySetup() throws ServletException {
	    ServletConfig sg = new MockServletConfig();
	    servlet.init(sg);
}
	
	@Test
	  @Rollback
	  void redirectUtenteControlTest() throws ServletException, IOException, SQLException,
	      ParseException {

	    request.addParameter("action", "user");
	    request.addParameter("usrMail" ,"mail@dodo.it");
        request.addParameter("usrPass", "password123");
        request.addParameter("registerOrNot", "Y");
        request.addParameter("logout", "N");
        request.addParameter("usrNick", "francesca123");

	    servlet.doGet(request, response);
	    assertEquals("/UtenteControl" , response.getForwardedUrl());

	}

}