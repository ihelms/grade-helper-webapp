package com.grade.helper.ui.validator;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.StringToLongConverter;

public class PlainStringToLongIdConverter extends StringToLongConverter {

	public PlainStringToLongIdConverter() {
		super("Must be a number.");
	}

	@Override
	public Result<Long> convertToModel(String value, ValueContext context) {
		if (value == null || value.isEmpty() || value.equals("auto-generated")) {
			return Result.ok(null);
		}
		long val;
		try {
			val = Long.parseLong(value);
		} catch (NumberFormatException e) {
			return Result.error("Parsing Error!");
		}
		return Result.ok(val);
	}

	@Override
	public String convertToPresentation(Long value, ValueContext context) {
		if (value == null) {
			return "auto-generated";
		}
		return String.valueOf(value);
	}
}
