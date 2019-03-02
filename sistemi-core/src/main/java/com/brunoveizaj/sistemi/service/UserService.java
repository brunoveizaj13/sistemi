package com.brunoveizaj.sistemi.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoveizaj.sistemi.assemblers.Assembler;
import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.dao.CrudDAO;
import com.brunoveizaj.sistemi.dao.UserDAO;
import com.brunoveizaj.sistemi.dto.UserDTO;
import com.brunoveizaj.sistemi.entities.Role;
import com.brunoveizaj.sistemi.entities.User;
import com.brunoveizaj.sistemi.exceptions.ValidationException;
import com.brunoveizaj.sistemi.forms.PasswordForm;
import com.brunoveizaj.sistemi.models.Principal;
import com.brunoveizaj.sistemi.models.UserToken;
import com.brunoveizaj.sistemi.utils.StringUtil;

@Service
public class UserService {
	
	
	@Autowired UserDAO userDAO;
	@Autowired CrudDAO crudDAO;
	@Autowired TokenService tokenService;
	
	
	
	@Transactional
	public UserToken login(Principal principal)
	{
		String username = principal.getUsername();
		String password = principal.getPassword();
		
		
		User u = userDAO.findByUsername(username);
		
		if(u == null) throw new ValidationException("Perdoruesi nuk ekziston");
		
		if(u.getStatus() != IStatus.ACTIVE) 
		{
			throw new ValidationException("Perdoruesi nuk eshte aktiv");
		}
		
		if(!password.equals(u.getSecret()))
		{
			throw new ValidationException("Fjalekalimi i gabuar");
		}
		
		
		
		String token = tokenService.generateToken(u);
		/*
		Login login = new Login();
		login.setBrowser(principal.getBrowser());
		login.setIp(principal.getIp());
		login.setLoginTime(Calendar.getInstance().getTime());
		login.setToken(token);
		login.setUsername(u.getUsername());
		
		userDAO.create(login);
		*/
		return new UserToken(new Assembler().toDto(u),token);
		
	}
	
	@Transactional
	public void changePassword(PasswordForm form, String uname)
	{
		
		User u = findUserByUsername(uname);
		
		User user = userDAO.findByUsername(form.getUsername());
		
		if(!user.getSecret().equals(form.getOldPassword()))
		{
			throw new ValidationException("Fjalekalimi i vjeter eshte i pasakte");
		}
		
		if(!StringUtil.isValid(form.getNewPassword()) || form.getNewPassword().length() < 6)
		{
			throw new ValidationException("Fjalekalimi duhet te jete te pakten 6 karaktere");
		}
		
		user.setModifyUserId(u.getId());
		user.setModifyTime(Calendar.getInstance().getTime());
		user.setSecret(form.getNewPassword());
		
		crudDAO.update(user);
		
		
	}
	
	public List<User> listUsers()
	{
		return userDAO.listUsers();
	}
	
	
	public User findUserById(Integer userId)
	{
		return crudDAO.findById(User.class, userId);
	}
	public User findUserByUsername(String username)
	{
		return userDAO.findByUsername(username);
	}
	
	@Transactional
	public User createUser(UserDTO dto, String uname)
	{
		
		if(!StringUtil.isValid(dto.getUsername()))
		{
			throw new ValidationException("Plotesoni Username");
		}
		if(!StringUtil.isValid(dto.getMunicipality()))
		{
			throw new ValidationException("Plotesoni Bashkine");
		}
		if(dto.getRole() == null)
		{
			throw new ValidationException("Plotesoni rolin");
		}
		
		User existing = userDAO.findByUsername(dto.getUsername());
		if(existing != null)
		{
			throw new ValidationException("Useri \""+existing.getUsername()+"\" ekziston ne sistem, STATUSI==>"+(existing.getStatus()==IStatus.ACTIVE?"AKTIV":"JO AKTIV"));
		}
		
		User u = findUserByUsername(uname);
		
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setCreateTime(Calendar.getInstance().getTime());
		user.setCreateUserId(u.getId());
		user.setRole(crudDAO.findById(Role.class, dto.getRole().getCode()));
		user.setSecret("123456");
		user.setStatus(IStatus.ACTIVE);
		
		return crudDAO.create(user);
		
	}

	@Transactional
	public User modifyUser(UserDTO dto, String uname)
	{
		if(!StringUtil.isValid(dto.getUsername()))
		{
			throw new ValidationException("Plotesoni Username");
		}
		if(!StringUtil.isValid(dto.getMunicipality()))
		{
			throw new ValidationException("Plotesoni Bashkine");
		}
		if(dto.getRole() == null)
		{
			throw new ValidationException("Plotesoni rolin");
		}
		
		User user = userDAO.findByUsername(dto.getUsername());
		if(user != null)
		{
			if(dto.getId() != user.getId())
			{
				throw new ValidationException("Useri \""+user.getUsername()+"\" ekziston ne sistem, STATUSI==>"+(user.getStatus()==IStatus.ACTIVE?"AKTIV":"JO AKTIV"));
			}
		}
		
		User u = findUserByUsername(uname);
		
		user.setUsername(dto.getUsername());
		user.setModifyTime(Calendar.getInstance().getTime());
		user.setModifyUserId(u.getId());
		user.setRole(crudDAO.findById(Role.class, dto.getRole().getCode()));
		user.setSecret(dto.getSecret());
		user.setStatus(dto.isActive()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
		
		return crudDAO.update(user);
	}
	

}
