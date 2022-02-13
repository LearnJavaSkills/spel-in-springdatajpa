package in.learnjavaskills.spelinspringdatajpa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer bookId;
	
	@Column(name = "unique_code", nullable = false, unique = true)
	private String uniqueCode;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id", referencedColumnName = "author_id", nullable = false)
	private AuthorEntity authorEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id", referencedColumnName = "seller_id", nullable = false)
	private SellerEntity sellerEntity;
	
	@Column(name = "quentiy_left", nullable = false)
	private Integer quentiyLeft;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "discount_available")
	private boolean discountAvailable;
	
	@Column(name = "discount")
	private Double discount;
	
	@Column(name = "rating")
	private Double rating;
}
