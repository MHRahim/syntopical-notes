package com.rahim.syntopicalnotes.controllers.v1.notes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahim.syntopicalnotes.controllers.BaseController;
import com.rahim.syntopicalnotes.utils.ResponseFormatter;

@RestController
@RequestMapping("api/v1/notes")
public class NotesController extends BaseController {
    public NotesController(ResponseFormatter responseFormatter) {
		super(responseFormatter);
	}

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return this.responseFormatter.send(200, "Hello", "Hello from notes");
    }
}
