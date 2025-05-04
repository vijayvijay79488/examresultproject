package com.example.examresult.converter;

import java.util.List;

//package com.example.examresult.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SubjectConverter implements AttributeConverter<List<String>, String> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<String> subjectList) {
		try {
			return objectMapper.writeValueAsString(subjectList);
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not convert list to JSON string", e);
		}
	}

	@Override
	public List<String> convertToEntityAttribute(String json) {
		try {
			return objectMapper.readValue(json, new TypeReference<List<String>>() {
			});
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not convert JSON string to list", e);
		}
	}
}
