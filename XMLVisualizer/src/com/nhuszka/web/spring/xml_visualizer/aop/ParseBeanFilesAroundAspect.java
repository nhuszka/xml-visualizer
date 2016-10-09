package com.nhuszka.web.spring.xml_visualizer.aop;

import java.util.Arrays;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParseBeanFilesAroundAspect {
	
	private static final String METHOD_NAME = 
			"com.nhuszka.web.spring.xml_visualizer.parser.SpringBeanXMLParser.parseBeanFiles(..)";

	@Around("execution(* " + METHOD_NAME +")")
	public Object logParseBeanFilesMethod(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnValue = null;
		
		System.out.println("---- AOP LOG START ---- ");
		System.out.println("Calling: " + METHOD_NAME);
		
		try {
			logArgs(proceedingJoinPoint);
			
			returnValue = proceedingJoinPoint.proceed();
			System.out.println("Return value: " + returnValue);
		} catch (Throwable e) {
			System.out.println("Error happened during call of " + METHOD_NAME);
			e.printStackTrace();
		}
		
		System.out.println("Call is done: " + METHOD_NAME);
		System.out.println("---- AOP LOG END ---- ");
		
		return returnValue;
	}

	private void logArgs(ProceedingJoinPoint proceedingJoinPoint) {
		Optional<Object[]> args = Optional.of(proceedingJoinPoint.getArgs());
		if (args.isPresent()) {
			for (Object arg : Arrays.asList(args.get())) {
				System.out.println("arg: " + arg);
			}
		}
	}
}
