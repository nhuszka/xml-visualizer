package com.nhuszka.web.spring.xml_visualizer.aop;

import java.util.Arrays;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParseBeanFilesAroundAspect {
	
	private static final Logger LOGGER = Logger.getLogger(ParseBeanFilesAroundAspect.class);
	
	private static final String METHOD_NAME = 
			"com.nhuszka.web.spring.xml_visualizer.parser.SpringBeanXMLParser.parseBeanFiles(..)";

	@Around("execution(* " + METHOD_NAME +")")
	public Object logParseBeanFilesMethod(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnValue = null;
		
		LOGGER.warn("---- AOP LOG START ---- ");
		LOGGER.warn("Calling: " + METHOD_NAME);

		try {
			logArgs(proceedingJoinPoint);
			
			returnValue = proceedingJoinPoint.proceed();
			LOGGER.warn("Return value: " + returnValue);
		} catch (Throwable exception) {
			LOGGER.error("Error happened during call of " + METHOD_NAME, exception);
		}
		
		LOGGER.warn("Call is done: " + METHOD_NAME);
		LOGGER.warn("---- AOP LOG END ---- ");
		
		return returnValue;
	}

	private void logArgs(ProceedingJoinPoint proceedingJoinPoint) {
		Optional<Object[]> args = Optional.of(proceedingJoinPoint.getArgs());
		if (args.isPresent()) {
			for (Object arg : Arrays.asList(args.get())) {
				LOGGER.warn("arg: " + arg);
			}
		}
	}
}
