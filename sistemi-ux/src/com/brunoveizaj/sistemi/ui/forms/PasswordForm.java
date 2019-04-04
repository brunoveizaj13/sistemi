package com.brunoveizaj.sistemi.ui.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PasswordForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String oldPassword;
	private String newPassword;
	private int userId;
	private String username;
	
	
	
	
	
	

}
