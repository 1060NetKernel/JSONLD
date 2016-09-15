/*
 * JSONFromXML.java
 *
 * Created on 19 June 2006, 11:25
 */

package org.netkernel.json.jsonld.endpoint;

import org.netkernel.layer0.meta.impl.SourcedArgumentMetaImpl;
import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

public class JSONLDCompact extends StandardAccessorImpl
{
	public JSONLDCompact()
	{	this.declareThreadSafe();
		declareArgument(new SourcedArgumentMetaImpl("operand","input JSONLD document",null,new Class[]{String.class}));
		//declareSourceRepresentation(JSONObject.class);
	}
	
	public void onSource(INKFRequestContext context) throws Exception
	{	context.createResponseFrom("JSONLDCompact TBD");
	}
}
