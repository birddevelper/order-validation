package mst.example.ordervalidation.repositories;

import mst.example.ordervalidation.models.ValidationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRequestRepository extends JpaRepository<ValidationRequest,Integer> {

}
