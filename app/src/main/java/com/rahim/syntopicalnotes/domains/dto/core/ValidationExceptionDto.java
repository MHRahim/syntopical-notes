package com.rahim.syntopicalnotes.domains.dto.core;

import java.util.List;
import java.util.Map;

public record ValidationExceptionDto(
  Map<String, List<String>> errors
){}



