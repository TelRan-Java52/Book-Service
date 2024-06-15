package telran.java52.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
	
    @PersistenceContext
	EntityManager em;
	
	@Override//don`t work
	public List<String> findPublishersByAuthor(String authorName) {
		TypedQuery<Book> query = em.createQuery(
		        "SELECT b FROM Book b JOIN b.authors a WHERE a.name = :authorName", Book.class);
		    query.setParameter("authorName", authorName);
		   
		    return 
		    		query.getResultStream()
		        .map(book -> book.getPublisher().getPublisherName())
		        .distinct()
		        .collect(Collectors.toList());

	}

	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String authorName) {
		TypedQuery<Publisher> query = em.createQuery(
                "SELECT DISTINCT b.publisher FROM Book b WHERE b.author.name = :authorName", Publisher.class);
        query.setParameter("authorName", authorName);
        List<Publisher> publishers = query.getResultList();
        return publishers.stream();
	}

	@Override
	public Optional<Publisher> findById(String publisher) {
		
		return Optional.ofNullable(em.find(Publisher.class, publisher));
	}
//@Transactional
	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);//добавляет только нового(insert)
//		em.merge(publisher);//работает как obsert если нет добавляет, если есть заменяет
		return publisher;
	}

}
