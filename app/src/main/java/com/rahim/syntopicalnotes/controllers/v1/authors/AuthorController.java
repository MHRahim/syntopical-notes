package com.rahim.syntopicalnotes.controllers.v1.authors;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahim.syntopicalnotes.domains.dto.authors.AuthorDto;
import com.rahim.syntopicalnotes.domains.dto.authors.CreateAuthorDto;
import com.rahim.syntopicalnotes.services.authors.AuthorService;
import com.rahim.syntopicalnotes.utils.ResponseFormatter;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
public class AuthorController {

   private final AuthorService service; 
   private final ResponseFormatter responseFormatter;

   @GetMapping("")
   public ResponseEntity<?> getAllAuthors() {
       List<AuthorDto> authorDtos = this.service.getAllAuthors();
       return this.responseFormatter.send(200, "Ok.", authorDtos);
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
       AuthorDto author = this.service.getAuthorById(id);
       return this.responseFormatter.send(200, "Ok.", author);
   }


   @PostMapping("")
   public ResponseEntity<?> createAuthor(@Valid @RequestBody CreateAuthorDto dto) {
       throw new UnsupportedOperationException("TODO: AuthorService.createAuthor not yet implemented");
   }


   @PutMapping("/{id}")
   public ResponseEntity<?> updateAuthor(@PathVariable Long id) {
       // validate date of death

        return this.responseFormatter.send(201, "Ok.", id);
   }
}
