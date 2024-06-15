package telran.java52.book.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.book.model.Author;
import telran.java52.book.model.Publisher;

public interface AuthorRepository  {
	
	Optional<Author> findById(String authorName);
	
	Author save (Author author);

	void deleteById(String authorName);

	

}
