package com.notes.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.notes.Entity.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

	 List<Notes> findByUserId(Integer userId);
	 
	 Optional<Notes> findByIdAndUserId(Integer noteId, Integer userId);
	 
	 @Query(value = "SELECT * FROM notes WHERE user_id = :userId AND (MATCH(note) AGAINST(:keyword IN NATURAL LANGUAGE MODE)OR note LIKE %:keyword%)", nativeQuery = true)
	 List<Notes> fullTextSearchForUser(@Param("userId") Integer userId, @Param("keyword") String keyword);
}
