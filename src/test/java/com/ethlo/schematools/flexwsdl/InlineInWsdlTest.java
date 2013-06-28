package com.ethlo.schematools.flexwsdl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

/**
 * 
 * @author mha
 *
 */
public class InlineInWsdlTest
{
	@Test
	public void inlinInWsdlTest() throws Exception
	{
		final String targetNs = "http://example.com/foreign-1.0.xsd";
		final Document wsdl = new DynamicWsdlBuilder()
		.withWrapper(URI.create("classpath:/schema/dynRequest.xsd"))
		.withTargetNameSpace(targetNs)
		.withDynamicSchema(new SchemaProvider()
		{
			@Override
			public Reader getXsdSchema() throws IOException
			{
				return new InputStreamReader(new ClassPathResource("/schema/type.xsd").getInputStream());
			}
		})
		.withWsLocationUri("http://localhost:8080/example-ws")
		.withWsdlPortTypeName("example")
		.buildWsdl();
		
		System.out.println(XmlUtil.asXmlString(wsdl));
	}
}
