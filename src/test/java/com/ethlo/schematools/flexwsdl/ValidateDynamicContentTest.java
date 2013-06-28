package com.ethlo.schematools.flexwsdl;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Validator;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

/**
 * 
 * @author mha
 */
public class ValidateDynamicContentTest
{
	@Test
	public void buildWsdlWithDynamicType() throws Exception
	{
		final Validator validator = new DynamicWsdlBuilder()
			.withWrapper(URI.create("classpath:/schema/dynRequest.xsd"))
			.withTargetNameSpace("http://example.com/foreign-1.0.xsd")
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
			.buildValidator();
		
		final Document payload = XmlUtil.loadDocument(new InputStreamReader(getClass().getResourceAsStream("/testdata/validinput.xml")));
		validator.validate(new DOMSource(payload));
	}
}
