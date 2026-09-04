package com.rahim.syntopicalnotes.domains.dto.auth;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUser {
   private String email;

   private String name;

   private String password;

   private Long role_id;
}
