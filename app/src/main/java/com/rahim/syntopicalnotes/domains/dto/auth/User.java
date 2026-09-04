package com.rahim.syntopicalnotes.domains.dto.auth;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
   private Long id;

   private String email;

   private String username;

   private String password;

   private Role role;

   private Date created_at;

   private Date updated_at;
}
