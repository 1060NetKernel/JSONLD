/*
 * JSONFromXML.java
 *
 * Created on 19 June 2006, 11:25
 */

package org.netkernel.json.jsonld.endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.netkernel.json.jsonld.util.ContextDocumentLoader;
import org.netkernel.layer0.meta.impl.SourcedArgumentMetaImpl;
import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.nkf.INKFResponse;
import org.netkernel.layer0.representation.IReadableBinaryStreamRepresentation;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.core.DocumentLoader;
import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.core.RemoteDocument;
import com.github.jsonldjava.utils.JsonUtils;

public class JSONLDCompact extends StandardAccessorImpl
{
	public JSONLDCompact()
	{	//this.declareThreadSafe();
		//declareSourceRepresentation(JSONObject.class);
	}
	
	public void onSource(INKFRequestContext aContext) throws Exception
	{	
		//1. What do you want?  Look at the request
		//2. What do need?
		IReadableBinaryStreamRepresentation opd=aContext.source("arg:operand", IReadableBinaryStreamRepresentation.class);
		IReadableBinaryStreamRepresentation opt=aContext.source("arg:operator", IReadableBinaryStreamRepresentation.class);
		
		//3. Add value - Steal the underpants
		Object jsonObject = JsonUtils.fromInputStream(opd.getInputStream());
		Object ctxObject = JsonUtils.fromInputStream(opt.getInputStream());
		JsonLdOptions options = new JsonLdOptions();
		options.setDocumentLoader(new ContextDocumentLoader(aContext));
		Object compact = JsonLdProcessor.compact(jsonObject, ctxObject, options);
		
		
		//4 Issue response
		INKFResponse resp=aContext.createResponseFrom(JsonUtils.toPrettyString(compact));
		
		//5. Add metadata 
		resp.setMimeType("application/ld+json");
	}	
}
