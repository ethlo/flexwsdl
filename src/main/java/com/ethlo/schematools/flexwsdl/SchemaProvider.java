package com.ethlo.schematools.flexwsdl;


import java.io.IOException;
import java.io.Reader;

/**
 * 
 * @author mha
 *
 */
public interface SchemaProvider
{
	Reader getXsdSchema() throws IOException;
}
