package in.learnjavaskills.spelinspringdatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.learnjavaskills.spelinspringdatajpa.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> 
{
	@Query("FROM BookEntity WHERE uniqueCode = ?#{[0]} AND title = ?#{[1]}")
	List<BookEntity> findBookByUniqueBookCode(String uniqueCode, String title);
	
	@Query("FROM BookEntity WHERE uniqueCode = ?#{#uniqueCode} AND title = ?#{#title}")
	List<BookEntity> findBookByUniqueBookCode1(@Param("uniqueCode") String uniqueCode, @Param("title") String title);
	
	@Query("SELECT price FROM BookEntity where uniqueCode = :#{#bookEntity.uniqueCode}")
	List<Double> findBookPriceByUniqueCode(@Param("bookEntity") BookEntity bookEntity);
	
	@Query("FROM BookEntity WHERE authorEntity = :#{#bookEntity.authorEntity}")
	List<BookEntity> findBooksByAuthor(@Param("bookEntity") BookEntity bookEntity);
}
