package nz.govt.health.tm.loanapproval;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import static org.camunda.spin.Spin.JSON;
import org.camunda.spin.plugin.variable.value.JsonValue;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author andre
 *
 */
public class PersistClaimToPostgresDelegate implements JavaDelegate {
	private final static Logger LOGGER = Logger.getLogger("Persistence-Example");
	
	@Autowired
	private ClaimRepository claimRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		JsonValue claimData = execution.getVariableTyped("claim");

		if( claimData == null) {
			LOGGER.info("VARIABLE IN DELEGATE IS NULL!");
		} else {			
			Claim claim = JSON(claimData.getValue()).mapTo(Claim.class);
			if(claimRepository != null) {
				LOGGER.info("Writing to Claim Repository - " + claim.toString());
				Claim returnedClaim = claimRepository.save(claim);
				// Overwrite the process variable with the data now persisted in
				// the database.
				//execution.setVariable("claim", returnedClaim);
				execution.setVariable("claim", JSON(returnedClaim).toString());
			} else {
				LOGGER.info("CLAIM REPOSITORY IS NULL!");
			}
		}
	}
}
