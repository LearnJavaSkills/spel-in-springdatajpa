package in.learnjavaskills.spelinspringdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learnjavaskills.spelinspringdatajpa.entity.SellerEntity;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {

}
