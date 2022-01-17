package nz.govt.health.tm.loanapproval;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanApprovalApplicationContext {
	@Bean
	public PersistClaimToPostgresDelegate persistClaimToPostgresDelegate() {
		return new PersistClaimToPostgresDelegate();
	}
}
