package telran.java52.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "publisherName")
@Entity
public class Publisher implements Serializable{
	
	private static final long serialVersionUID = 4328523889665632423L;
	
	@Id
	String publisherName;
	@OneToMany(mappedBy = "publisher")//аннотация говорит от какого поле зависит
	Set<Book> books;
	
	public Publisher(String publisherName) {
		super();
		this.publisherName = publisherName;
	}
	
	@Override
	public String toString() {
		return publisherName;
	}



	
}
