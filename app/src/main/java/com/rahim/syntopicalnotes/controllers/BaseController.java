package com.rahim.syntopicalnotes.controllers;

import com.rahim.syntopicalnotes.utils.ResponseFormatter;

public class BaseController {
    public ResponseFormatter responseFormatter;

	public BaseController(ResponseFormatter responseFormatter) {
		this.responseFormatter = responseFormatter;
	}
}
