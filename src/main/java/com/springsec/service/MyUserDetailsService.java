package com.springsec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		if (!username.equals("bill")) {
			throw new UsernameNotFoundException(username + " not found");
		}
		UserDetails u = new UserDetails() {
			private static final long serialVersionUID = 2059202961588104658L;

			public boolean isEnabled() {
				return true;
			}

			public boolean isCredentialsNonExpired() {
				return true;
			}

			public boolean isAccountNonLocked() {
				return true;
			}

			public boolean isAccountNonExpired() {
				return true;
			}

			public String getUsername() {
				return username;
			}

			public String getPassword() {
				return "abc123";
			}

			public List<GrantedAuthority> getAuthorities() {
				Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
				setAuths.add(new SimpleGrantedAuthority("GROUP_ADMIN"));
				List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
				return Result;
			}
		};

		return u;
	}

	/*
	 * @Transactional(readOnly = true) public UserDetails
	 * loadUserByUsername(final String username) throws
	 * UsernameNotFoundException {
	 * 
	 * com.springwebsecurity.users.model.User user =
	 * userDao.findByUserName(username); List<GrantedAuthority> authorities =
	 * buildUserAuthority(user.getUserRole());
	 * 
	 * return buildUserForAuthentication(user, authorities);
	 * 
	 * }
	 * 
	 * // Converts com.mkyong.users.model.User user to //
	 * org.springframework.security.core.userdetails.User private User
	 * buildUserForAuthentication(com.springwebsecurity.users.model.User user,
	 * List<GrantedAuthority> authorities) { return new User(user.getUsername(),
	 * user.getPassword(), user.isEnabled(), true, true, true, authorities); }
	 * 
	 * private List<GrantedAuthority> buildUserAuthority(Set<UserRole>
	 * userRoles) {
	 * 
	 * Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
	 * 
	 * // Build user's authorities for (UserRole userRole : userRoles) {
	 * setAuths.add(new SimpleGrantedAuthority(userRole.getRole())); }
	 * 
	 * List<GrantedAuthority> Result = new
	 * ArrayList<GrantedAuthority>(setAuths);
	 * 
	 * return Result; }
	 */

}