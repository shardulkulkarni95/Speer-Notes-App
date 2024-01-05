package com.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.Domain.RestNotes;
import com.notes.Entity.Notes;
import com.notes.Service.JwtService;
import com.notes.Service.NotesService;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/api")
public class NotesController {
	
	@Autowired
	private NotesService notesService;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/notes")
	@ResponseStatus(value = HttpStatus.OK)
	public Object getAllNotes(HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.getAllNotes(username);
	}
	
	@GetMapping("/notes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Object getByNoteId(@PathVariable Integer id, HttpServletRequest httpRequest) {
		String username = jwtService.extractUsername(jwtService.extractJwtTokenFromRequest(httpRequest));
		return notesService.getNoteById(username, id);
	}
	
	@PostMapping("/notes")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Object saveNote(@RequestBody RestNotes entity) {
		//TODO: process POST request
		
		return entity;
	}
	
	@PutMapping("/notes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Object updateNote(@PathVariable String id, @RequestBody RestNotes entity) {
		//TODO: process PUT request
		
		return entity;
	}
	
	@DeleteMapping("/notes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Object deleteNote(@PathVariable Integer id) {
		return null;
	}
	
	@PostMapping("/notes/{id}/share")
	@ResponseStatus(value = HttpStatus.OK)
	public Object postMethodName(@PathVariable Integer id ) {
		//TODO: process POST request
		//: share a note with another user for the authenticated user.
		
		return entity;
	}
	
	@GetMapping("/search")
	@ResponseStatus(value = HttpStatus.OK)
	public Object getMethodName(@RequestParam String keywords) {
		return new SomeData();
	}
	
	
	

}
