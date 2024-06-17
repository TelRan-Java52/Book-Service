package telran.java52.book.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.book.dao.AuthorRepository;
import telran.java52.book.dao.BookRepository;
import telran.java52.book.dao.PublisherRepository;
import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.dto.exception.EntityNotFoundException;
import telran.java52.book.model.Author;
import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		// Publisher
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElse(new Publisher(bookDto.getPublisher()));
		
		// Authors
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName()).orElse(new Author(a.getName(), a.getBirthDate())))
				.collect(Collectors.toSet());
		
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	public BookDto[] findBooksByAuthor(String name) {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public BookDto[] findBooksByPublisher(String publisher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthorDto[] findBookAuthors(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return book.getAuthors().stream()
				.map(a -> modelMapper.map(a, AuthorDto.class))
				.toArray(AuthorDto[]::new);
	}

	@Override
	public String[] findPublishersByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto updateBookTitle(String isbn, String newTitle) {
		// TODO Auto-generated method stub
		return null;
	}
    @Transactional
	@Override
	public BookDto removeBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		bookRepository.deleteById(isbn);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	public AuthorDto removeAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

}
