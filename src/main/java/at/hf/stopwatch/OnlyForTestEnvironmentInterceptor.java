package at.hf.stopwatch;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import at.hf.stopwatch.service.EnvironmentService;

@Interceptor
@OnlyForTestEnvironment
public class OnlyForTestEnvironmentInterceptor implements Serializable {

	@Inject
	EnvironmentService environmentService;

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		if (environmentService.isTestEnvironment()) {
			System.out.println("intercept");
			return context.proceed();
		}
		throw new SecurityException("Method only allowed in TestEnvironment");
	}

}
