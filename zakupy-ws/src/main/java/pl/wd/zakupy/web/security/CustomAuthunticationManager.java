package pl.wd.zakupy.web.security;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;


public class CustomAuthunticationManager implements AuthenticationManager, ApplicationContextAware {
	static Logger logger = Logger.getLogger("pl.wd.zakupy.web.security.CustomAuthunticationManager");
	
//	@Autowired
	private ApplicationContext appContext;

	public static final String USER_ID = "USERID:";
	public static final String CL_ID = "CLID:";
	private Hashtable<String,String> _user2Pass = new Hashtable<>();

	public Authentication authenticate( Authentication authentication ) throws AuthenticationException {

		String username = authentication.getName();
		String pass = authentication.getCredentials().toString();

		GrantedAuthority[] grantedAuthorities = null;


//		user.setLogin(username);
//		user.setPassword_encoded(pass);
		String existingPass = _user2Pass.get(username);
		if ( existingPass  == null ) {
			_user2Pass.put(username, pass);
		}
		else {
			if ( !existingPass.equals(pass)) {
				throw new BadCredentialsException("Autoryzacja odrzucona przez serwer.\r\nNiepoprawne hasło lub użytkownik.");
			}
			
		}

		String cl_id = Long.toString(System.currentTimeMillis());
		
		User user_auth = new User(username, pass, true, true, true, true, makeGrantedAuthorities( cl_id, username ));

		return new UsernamePasswordAuthenticationToken(user_auth, authentication.getCredentials(), makeGrantedAuthorities( cl_id,  username ));
	}

	private java.util.Collection<? extends GrantedAuthority> makeGrantedAuthorities( String client_id, String username ) {
		List<GrantedAuthorityImpl> auth = new ArrayList<GrantedAuthorityImpl>();
		auth.add(new GrantedAuthorityImpl("ROLE_URLACCESS"));
		auth.add(new GrantedAuthorityImpl( CL_ID + client_id ));
		auth.add(new GrantedAuthorityImpl( USER_ID + username ));

		return auth;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appContext = applicationContext;
	}

}
