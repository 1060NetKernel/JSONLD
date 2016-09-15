/*
 * JSONFromXML.java
 *
 * Created on 19 June 2006, 11:25
 */

package org.netkernel.json.jsonld.endpoint;

import java.util.HashMap;

import org.netkernel.layer0.meta.impl.SourcedArgumentMetaImpl;
import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.nkf.INKFResponse;
import org.netkernel.layer0.representation.IReadableBinaryStreamRepresentation;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class JSONLDCompact extends StandardAccessorImpl
{
	public JSONLDCompact()
	{	//this.declareThreadSafe();
		//declareSourceRepresentation(JSONObject.class);
	}
	
	public void onSource(INKFRequestContext aContext) throws Exception
	{	IReadableBinaryStreamRepresentation opd=aContext.source("arg:operand", IReadableBinaryStreamRepresentation.class);
		Object jsonObject = JsonUtils.fromInputStream(opd.getInputStream());
		HashMap context = new HashMap();
		JsonLdOptions options = new JsonLdOptions();
		Object compact = JsonLdProcessor.compact(jsonObject, context, options);
		INKFResponse resp=aContext.createResponseFrom(JsonUtils.toPrettyString(compact));
		resp.setMimeType("application/ld+json");
	}
}
