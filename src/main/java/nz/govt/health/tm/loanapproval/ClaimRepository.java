package nz.govt.health.tm.loanapproval;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long> {
	List<Claim> findByLastName(String lastName);
	Claim findById(long id);
}
