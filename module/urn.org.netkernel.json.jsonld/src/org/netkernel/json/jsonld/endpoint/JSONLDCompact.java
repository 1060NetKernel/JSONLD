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
		Object compact = JsonLdProcessor.compact(jsonObject, ctxObject, options);
		
		
		//4 Issue response
		INKFResponse resp=aContext.createResponseFrom(JsonUtils.toPrettyString(compact));
		
		//5. Add metadata 
		resp.setMimeType("application/ld+json");
	}
	
	class MyDL extends DocumentLoader
	{

		@Override
		public Object fromURL(URL arg0) throws JsonParseException, IOException
		{
			// TODO Auto-generated method stub
			return super.fromURL(arg0);
		}

		@Override
		public HttpClient getHttpClient()
		{
			// TODO Auto-generated method stub
			return super.getHttpClient();
		}

		@Override
		public RemoteDocument loadDocument(String arg0) throws JsonLdError
		{
			// TODO Auto-generated method stub
			return super.loadDocument(arg0);
		}

		@Override
		public InputStream openStreamFromURL(URL url) throws IOException
		{
			// TODO Auto-generated method stub
			return super.openStreamFromURL(url);
		}

		@Override
		public void setHttpClient(HttpClient nextHttpClient)
		{
			// TODO Auto-generated method stub
			super.setHttpClient(nextHttpClient);
		}
		
	}
}
