package com.notes.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notes.Domain.RestNotes;
import com.notes.Entity.Notes;
import com.notes.Entity.UserInfo;
import com.notes.Repository.NotesRepository;
import com.notes.Repository.UserInfoRepository;

@Service
public class NotesService {

	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	public Object getAllNotes(String username) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		return notesRepository.findByUserId(userInfo.getId());
	}

	public Object getNoteById(String username, Integer noteId) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Optional<Notes> byIdAndUserId = notesRepository.findByIdAndUserId(noteId, userInfo.getId());
		if (!byIdAndUserId.isEmpty()) {
			return byIdAndUserId.get();
		} else {
			return "note not found for user " + username + " with id " + noteId;
		}
	}

	public Object saveNote(RestNotes restNotes, String username) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Notes notes = new Notes();
		notes.setNote(restNotes.getNote());
		notes.setUser(userInfo);
		Notes savedNote = notesRepository.save(notes);
		return savedNote;
	}

	public Object updateNote(String username, RestNotes restNotes) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Optional<Notes> byIdAndUserId = notesRepository.findByIdAndUserId(restNotes.getId(), userInfo.getId());
		if (!byIdAndUserId.isEmpty()) {
			Notes previousNote = byIdAndUserId.get();
			previousNote.setNote(restNotes.getNote());
			Notes updatedNote = notesRepository.save(previousNote);
			return updatedNote;
		} else {
			return "note not found for user " + username + " with id " + restNotes.getId();
		}
	}

	public Object deleteNote(String username, Integer id) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Optional<Notes> byIdAndUserId = notesRepository.findByIdAndUserId(id, userInfo.getId());
		if (!byIdAndUserId.isEmpty()) {
			notesRepository.delete(byIdAndUserId.get());
			return "note deleted sucessfully!!";
		} else {
			return "note not found for user " + username + " with id " + id;
		}

	}

	public Object shareNote(String username, Integer id) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Optional<Notes> byIdAndUserId = notesRepository.findByIdAndUserId(id, userInfo.getId());
		if (!byIdAndUserId.isEmpty()) {
			Notes sharedNote = byIdAndUserId.get();
			return sharedNote;
		} else {
			return "note not found for user " + username + " with note id " + id;
		}
	}
}
