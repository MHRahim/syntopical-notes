package com.rahim.syntopicalnotes.domains.dto.authors;

import java.time.LocalDate;


import com.rahim.syntopicalnotes.domains.validation.DateAfter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@DateAfter(from = "dateOfBirth", to = "dateOfDeath", message = "Date of death must be after date of birth.")
public record CreateAuthorDto(
        @NotBlank(message = "Fill in the name.")
        @Size(max = 100, message = "Name is too long (Max 100 characters)")
        String name,

        @NotNull(message = "Fill in the date of birth.")
        @Past(message = "The date has to be past.")
        LocalDate dateOfBirth,

        @PastOrPresent(message = "Date of death has to be past or present")
        LocalDate dateOfDeath
        ) {
}
