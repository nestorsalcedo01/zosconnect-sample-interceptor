package com.ibm.crshnburn.zosconnect.interceptor;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.osgi.service.component.ComponentContext;

import com.ibm.wsspi.zos.connect.*;

public class SimpleInterceptorImpl implements Interceptor {

	private DateFormat df = SimpleDateFormat.getDateTimeInstance();

	protected void activate(ComponentContext context,
			Map<String, Object> properties) {
		System.out.println("Sample interceptor activated");
	}

	protected void deactivate(ComponentContext context) {
		System.out.println("Sample interceptor deactivated");
	}

	protected void modified(Map<String, Object> properties) {
		
	}

	@Override
	public String getName() {
		return "zOSConnectReferenceInterceptor";
	}

	@Override
	public void preInvoke(Map<Object, Object> requestStateMap,
			HttpZosConnectRequest httpZosConnectRequest, Data data)
			throws InterceptorException {
		Principal principal = httpZosConnectRequest.getUserPrincipal();
		String user = "<unknown>";
		if(principal != null){
			user = principal.getName().trim();
		}
		String path = httpZosConnectRequest.getRequestURI().trim();
		System.out.printf("ReferenceInterceptor preInvoke\nUser %s called URI %s at %s\n", user, path,
				df.format(new Date()));
	}

	@Override
	public void postInvoke(Map<Object, Object> requestStateMap,
			HttpZosConnectRequest httpZosConnectRequest, Data data)
			throws InterceptorException {
		Principal principal = httpZosConnectRequest.getUserPrincipal();
		String user = "<unknown>";
		if(principal != null){
			user = principal.getName().trim();
		}
		String path = httpZosConnectRequest.getRequestURI().trim();
		System.out.printf("ReferenceInterceptor postInvoke\nUser %s finished calling URI %s at %s\n", user,
				path, df.format(new Date()));
	}

	@Override
	public int getSequence() {
		return 1;
	}

}