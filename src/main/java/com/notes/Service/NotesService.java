package com.notes.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notes.Domain.RestNotes;
import com.notes.Domain.RestResponse;
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

	public RestResponse getAllNotes(String username) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		return new RestResponse(true,notesRepository.findByUserId(userInfo.getId()),null);
	}

	public RestResponse getNoteById(String username, Integer noteId) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Optional<Notes> byIdAndUserId = notesRepository.findByIdAndUserId(noteId, userInfo.getId());
		if (!byIdAndUserId.isEmpty()) {
			return new RestResponse(true,byIdAndUserId.get(),null);
		} else {
			return new RestResponse(false,null,"note not found for user " + username + " with id " + noteId);
		}
	}

	public RestResponse saveNote(RestNotes restNotes, String username) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Notes notes = new Notes();
		notes.setNote(restNotes.getNote());
		notes.setUser(userInfo);
		Notes savedNote = notesRepository.save(notes);
		return new RestResponse(true,savedNote,null);
	}

	public RestResponse updateNote(String username, RestNotes restNotes) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Optional<Notes> byIdAndUserId = notesRepository.findByIdAndUserId(restNotes.getId(), userInfo.getId());
		if (!byIdAndUserId.isEmpty()) {
			Notes previousNote = byIdAndUserId.get();
			previousNote.setNote(restNotes.getNote());
			Notes updatedNote = notesRepository.save(previousNote);
			return new RestResponse(true, updatedNote, null);
		} else {
			return new RestResponse(false,null,"note not found for user " + username + " with id " + restNotes.getId());
		}
	}

	public RestResponse deleteNote(String username, Integer id) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Optional<Notes> byIdAndUserId = notesRepository.findByIdAndUserId(id, userInfo.getId());
		if (!byIdAndUserId.isEmpty()) {
			notesRepository.delete(byIdAndUserId.get());
			return new RestResponse(true,null,"note deleted sucessfully!!");
		} else {
			return new RestResponse(false,null,"note not found for user " + username + " with id " + id);
		}

	}

	public RestResponse shareNote(String username, Integer id) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		Optional<Notes> byIdAndUserId = notesRepository.findByIdAndUserId(id, userInfo.getId());
		if (!byIdAndUserId.isEmpty()) {
			Notes sharedNote = byIdAndUserId.get();
			return new RestResponse(true,sharedNote,null);
		} else {
			return new RestResponse(false,null,"note not found for user " + username + " with note id " + id);
		}
	}
	
	public RestResponse searchedObject(String username,String keywords) {
		UserInfo userInfo = userInfoRepository.findByName(username).get();
		List<Notes> matchingNotes = notesRepository.findByUserId(userInfo.getId());
		
		return new RestResponse(true,matchingNotes,null);
	}
}
