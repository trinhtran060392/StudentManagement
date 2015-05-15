/**
 * TrinhTV3@fsoft.com.vn
 */

/**
 * @author TrinhTV3
 *
 */

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.POST;
import static play.test.Helpers.GET;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.routeAndCall;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.test.Helpers.cookie;
import static play.test.Helpers.header;

import org.junit.Test;

import play.libs.Json;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trinhtv3.fsoft.services.base.AuthenticationService;

public class ApplicationTestCase {
  
  @Test
  public void testLogin() {
    
    running(fakeApplication(), new Runnable() {
      
      @Override
      public void run() {

        ObjectNode object = Json.newObject();
        object.put("name", "student1");
        object.put("password", "pass");
        Result result = routeAndCall(fakeRequest(POST, "/api/authen/login").withJsonBody(object));
        
        assertThat(contentType(result)).isEqualTo("application/json");
        System.out.println(contentAsString(result));
      }
    });
    
  }
  
  @Test
  public void testCurrent() {
    running(fakeApplication(), new Runnable() {
      
      @Override
      public void run() {

        ObjectNode object = Json.newObject();
        object.put("name", "student1");
        object.put("password", "pass");
        Result login = routeAndCall(fakeRequest(POST, "/api/authen/login").withJsonBody(object));
        
        JsonNode json = Json.parse(contentAsString(login));
        
        String cookie = json.get("AuthenKey").asText();
        Result result = routeAndCall(fakeRequest(GET, "/api/authen/context").withHeader("X-AUTH-TOKEN", cookie));
        
        System.out.println(contentAsString(result)+"in testing");
        
      }
    });
  }
}
