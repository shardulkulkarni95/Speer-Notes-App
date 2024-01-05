package com.notes.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.Entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

	 List<Notes> findByUserId(Integer userId);
	 
	 Optional<Notes> findByIdAndUserId(Integer noteId, Integer userId);
}
