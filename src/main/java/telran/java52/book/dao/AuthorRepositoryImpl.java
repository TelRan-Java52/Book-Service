package telran.java52.book.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.book.model.Author;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Optional<Author> findById(String authorName) {
		
		return Optional.ofNullable(em.find(Author.class, authorName));
	}

	@Override
	public Author save(Author author) {
		em.persist(author);
		return author;
	}

	@Override
	public void deleteById(String authorName) {
		// TODO Auto-generated method stub

	}

}
