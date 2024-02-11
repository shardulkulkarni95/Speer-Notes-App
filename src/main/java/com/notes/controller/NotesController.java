package com.notes.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.notes.Domain.RestNotes;
import com.notes.Domain.RestResponse;
import com.notes.Entity.Notes;
import com.notes.Service.JwtService;
import com.notes.Service.NotesService;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
@Validated
public class NotesController {
	
	@Autowired
	private NotesService notesService;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/notes")
	@ResponseStatus(value = HttpStatus.OK)
	public RestResponse getAllNotes(HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.getAllNotes(username);
	}
	
	@GetMapping("/notes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public RestResponse getByNoteId(@PathVariable Integer id, HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.getNoteById(username, id);
	}
	
	@PostMapping("/notes")
	@ResponseStatus(value = HttpStatus.CREATED)
	public RestResponse saveNote(@Valid @RequestBody RestNotes restNote, HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.saveNote(restNote,username);
	}
	
	@PutMapping("/notes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public RestResponse updateNote(@PathVariable Integer id, @RequestBody RestNotes restNote, HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.updateNote(id,username,restNote);
	}
	
	@DeleteMapping("/notes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public RestResponse deleteNote(@PathVariable Integer id, HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.deleteNote(username,id);
	}
	
	@PostMapping("/notes/{id}/share")
	@ResponseStatus(value = HttpStatus.OK)
	public RestResponse shareNote(@PathVariable Integer id, HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.shareNote(username,id);
	}
	
	@GetMapping("/search")
	@ResponseStatus(value = HttpStatus.OK)
	public RestResponse searchNotes(@RequestParam String q, HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.searchedObject(username,q);
	}
	
	
	

}
