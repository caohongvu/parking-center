package net.spm.service.cache;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class HostedFormCache extends HashMap<String, String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
