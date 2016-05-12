package at.hf.stopwatch.auth;

import java.security.acl.Group;

import javax.security.auth.login.LoginException;

import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

public class rciLoginModule extends UsernamePasswordLoginModule {
	@Override

    protected Group[] getRoleSets() throws LoginException {
		System.out.println("getRoleSets");

         /**any role could be returned as the security domain declared in above standalone.xml allows permission for all roles*/

         SimpleGroup group = new SimpleGroup("Roles");

          try {

              group.addMember(new SimplePrincipal("admin"));

          } catch (Exception e) {

              throw new LoginException("Failed to create group member for " + group);

          }

          return new Group[] { group };

    }



    @Override

    protected boolean validatePassword(String inputPassword, String expectedPassword) {
    	
         /**do actual validation,'this.getUsernameAndPassword()' returns username,&password sent to this module*/

         return true;

      }



    @Override

    protected String getUsersPassword() throws LoginException {

         /** 'this.getUsernameAndPassword()' would provide username,&password sent to this module*/

         /**this method could be used if user entered password need to be hashed before validation is done by above 'validatePassword' method */

         return "sri";

    }
}
