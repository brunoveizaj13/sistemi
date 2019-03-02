/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bruno
 */
@Getter @Setter
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String username;
    private String secret;
    private RoleDTO role;
    private boolean active;
    private String name;
    private String surname;
    private String municipality;
    private Date createTime;
    private int createUserId;
    private Date modifyTime;
    private int modifyUserId;
            
}
