package org.netkernel.json.jsonld.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.representation.IReadableBinaryStreamRepresentation;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.core.DocumentLoader;
import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.RemoteDocument;

public class ContextDocumentLoader extends DocumentLoader
{
	INKFRequestContext mContext;
	public ContextDocumentLoader(INKFRequestContext aContext)
	{
		mContext=aContext;
		System.setProperty("com.github.jsonldjava.disallowRemoteContextLoading", "true");
	}
	
	@Override
	public Object fromURL(URL arg0) throws JsonParseException, IOException
	{
		System.out.println("DL fromURL");
		throw new IOException("Not supported yet");
	}

	@Override
	public HttpClient getHttpClient()
	{
		System.out.println("DL getHttpClient");
		return super.getHttpClient();
	}

	@Override
	public RemoteDocument loadDocument(String arg0) throws JsonLdError
	{
		System.out.println("DL loadDocument");
		return super.loadDocument(arg0);
	}

	@Override
	public InputStream openStreamFromURL(URL url) throws IOException
	{
		System.out.println("DL openStreamFromURL");
		try
		{
			IReadableBinaryStreamRepresentation rbs=mContext.source(url.toString(), IReadableBinaryStreamRepresentation.class);
			return rbs.getInputStream();
		}
		catch(Exception e)
		{	throw new IOException(e);
		}
	}

	@Override
	public void setHttpClient(HttpClient nextHttpClient)
	{
		System.out.println("DL setHttpClient");
		super.setHttpClient(nextHttpClient);
	}

}
