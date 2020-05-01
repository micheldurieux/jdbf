package net.iryndin.jdbf.util;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

import net.iryndin.jdbf.core.DbfMetadata;
import net.iryndin.jdbf.reader.DbfReader;

/**
 * A test class for IOUtils.
 */
public class DbfMetadataUtilsTest {
	
	
    @Test
    public void shouldProvideTheRightFullHeaderLengthWhenSerializationIsPrepared() throws Exception {
    	InputStream dbf = getClass().getClassLoader().getResourceAsStream("data1/gds_im_bigheader.dbf");

        try (DbfReader reader = new DbfReader(dbf)) {
        	DbfMetadata metadata = reader.getMetadata();
			byte[] headerBytes = DbfMetadataUtils.toByteArrayHeader(metadata);
			int headerLengthFromMetadata = BitUtils.makeInt(headerBytes[8], headerBytes[9]);
	        int goodHeaderSize = 32 + 32 * metadata.getFields().size() + 1;
			assertEquals("Calculated Header Size must be adjust to jdbf format", headerLengthFromMetadata, goodHeaderSize);
        }

    }
}