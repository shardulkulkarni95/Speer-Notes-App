package com.notes.Domain;

import jakarta.validation.constraints.NotNull;

public class RestNotes {
	private Integer id;
	@NotNull(message = "note can not be empty")
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

}
