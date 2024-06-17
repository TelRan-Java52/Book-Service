package telran.java52.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import telran.java52.book.model.Author;
import telran.java52.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Stream<Book> findByAuthorsName(String name) {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author.name = :authorName", Book.class);
		  query.setParameter("authorName", name);
		    List<Book> books = query.getResultList();
		    return books.stream();
	// return em.createQuery("select b from Book b join b. authors a where a.name=?1",Book.class);
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String publisherName) {
		  TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.publisher.name = :publisherName", Book.class);
		    query.setParameter("publisherName", publisherName);
		    List<Book> books = query.getResultList();
		    return books.stream();
	}

	@Override
	public void deleteByAuthorsName(String name) {
		 TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class);
	        query.setParameter("name", name);
	        List<Author> authors = query.getResultList();
	        
	        if (!authors.isEmpty()) {
	            for (Author author : authors) {
	                em.remove(author);
	            }
	        }
	    }

	

	@Override
	public boolean existsById(String isbn) {
		
		return em.find(Book.class, isbn) !=null;
	}

	@Override
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public Optional<Book> findById(String isbn) {
		
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public void deleteById(String isbn) {
		// em.remove(em.find(Book.class, isbn));
		Book book = em.find(Book.class, isbn);
		if (book !=null) {
			em.remove(book);
		}

	}

}
