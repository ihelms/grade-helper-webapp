package com.grade.helper.ui.validator;

import com.grade.helper.businesslogic.entities.simple.GradeType;
import com.grade.helper.businesslogic.logic.GradeTypeService;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;


public class StringToGradeTypeConverter implements Converter<String, GradeType> {

    private final GradeTypeService gradeTypeService;

    public StringToGradeTypeConverter(GradeTypeService gradeTypeService) {
        this.gradeTypeService = gradeTypeService;
    }

    @Override
    public Result<GradeType> convertToModel(String value, ValueContext context) {
        if (value == null || value.isEmpty()) {
            return Result.error("Empty String");
        }

        return Result.ok(gradeTypeService.findGradeTypeByValue(value));
    }

    @Override
    public String convertToPresentation(GradeType value, ValueContext context) {
        if (value == null) {
            return "";
        }
        return value.getGradeType();
    }
}
