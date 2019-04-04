package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.UserClient;
import com.brunoveizaj.sistemi.ui.forms.PasswordForm;
import com.brunoveizaj.sistemi.ui.models.Principal;
import com.brunoveizaj.sistemi.ui.models.UserDTO;
import com.brunoveizaj.sistemi.ui.models.UserToken;

public class UserService {

	public UserToken login(Principal principal)
	{
		return new UserClient().login(principal);
	}
	
	public List<UserDTO> loadUsers()
	{
		return new UserClient().loadUsers();
	}
	
	public UserDTO registerUser(UserDTO user)
	{
		return new UserClient().registerUser(user);
	}
	
	public UserDTO modifyUser(UserDTO user)
	{
		return new UserClient().modifyUser(user);
	}
	
	public void changePassword(PasswordForm form)
	{
		new UserClient().changePassword(form);
	}
}
