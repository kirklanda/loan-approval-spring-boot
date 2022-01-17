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
		JsonValue applicationData = execution.getVariableTyped("claim");

		if( applicationData == null) {
			LOGGER.info("VARIABLE IN DELEGATE IS NULL");
		} else {			
			LOGGER.info("VARIABLE IN DELEGATE IS NOT NULL");
			Claim claim = JSON(applicationData.getValue()).mapTo(Claim.class);
			//Claim claim = JSON("{\"firstName\" : \"Bob\",  \"lastName\" : \"Hope\"}").mapTo(Claim.class);
			LOGGER.info(claim.toString());
			LOGGER.info(applicationData.getValue().toString());
			if(claimRepository != null) {
				LOGGER.info("Writing to Claim Repository");
				claimRepository.save(claim);
			} else {
				LOGGER.info("Claim Repository is null");
			}
		}
	}
}
