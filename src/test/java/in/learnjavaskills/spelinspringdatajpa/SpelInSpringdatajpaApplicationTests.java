package in.learnjavaskills.spelinspringdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.learnjavaskills.spelinspringdatajpa.entity.AuthorEntity;
import in.learnjavaskills.spelinspringdatajpa.entity.BookEntity;
import in.learnjavaskills.spelinspringdatajpa.entity.SellerEntity;
import in.learnjavaskills.spelinspringdatajpa.repository.AuthorRepository;
import in.learnjavaskills.spelinspringdatajpa.repository.BookRepository;
import in.learnjavaskills.spelinspringdatajpa.repository.SellerRepository;

@SpringBootTest
class SpelInSpringdatajpaApplicationTests 
{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private SellerRepository SellerRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	void saveBook()
	{
		AuthorEntity authorEntity = AuthorEntity.builder()
			.firstName("Joanne")
			.lastName("Rowling")
			.build();
		
		authorEntity = authorRepository.save(authorEntity);
		
		SellerEntity sellerEntity = SellerEntity.builder()
			.name("cloud tail")
			.rating(4.6d)
			.build();
		
		sellerEntity  = SellerRepository.save(sellerEntity);
		
		BookEntity bookEntity = BookEntity.builder()
			.uniqueCode("BK0001")
			.title("Harry Potter and the Prisoner of Azkaban")
			.description("Harry Potter and the Prisoner of Azkaban")
			.discountAvailable(false)
			.price(209.01d)
			.quentiyLeft(124)
			.rating(4.7d)
			.authorEntity(authorEntity)
			.sellerEntity(sellerEntity)
			.build();
		
		bookRepository.save(bookEntity);
			
	}
	
	@Test
	void testIndexQuery()
	{
		List<BookEntity> books = bookRepository.findBookByUniqueBookCode("BK0001", "Harry Potter and the Prisoner of Azkaban");
		books.stream()
			.forEach(b -> System.out.println(b.getBookId()));
	
	}
	
	@Test
	void testParamQuery()
	{
		List<BookEntity> books = bookRepository.findBookByUniqueBookCode1("BK0001", "Harry Potter and the Prisoner of Azkaban");
		books.stream()
			.forEach(b -> System.out.println(b.getBookId()));
	}
	
	
	@Test
	void testSelectPrice()
	{
		BookEntity bookEntity = BookEntity.builder()
				.uniqueCode("BK0001")
				.build();
		
		Double priceByUniqueCode = bookRepository.findBookPriceByUniqueCode(bookEntity).stream().findFirst().get();
		System.out.println("book price = " + priceByUniqueCode);
	}
	
	@Test
	void testSelectAuthorsBook()
	{
		AuthorEntity authorEntity = AuthorEntity.builder()
			.authorId(1)
			.build();
		
		BookEntity bookEntity = BookEntity.builder()
				.authorEntity(authorEntity)
				.build();
		
		List<BookEntity> findBooksByAuthor = bookRepository.findBooksByAuthor(bookEntity);
		findBooksByAuthor.stream()
			.forEach(book -> System.out.println(book.getDescription()));
	}

}
