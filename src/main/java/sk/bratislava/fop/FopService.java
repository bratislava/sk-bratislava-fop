package sk.bratislava.fop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class FopService {
    private StreamSource toStreamSource(String str) {
        return new StreamSource(new StringReader(str));
    }

    public byte[] transform(String data, String xslt) throws Exception {
        InputStream config = getClass().getClassLoader().getResourceAsStream("fop.xconf");

        // configure fopFactory as desired
        final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI(), config);
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // configure foUserAgent as desired

        // Setup output
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(toStreamSource(xslt));

            // Set the value of a <param> in the stylesheet
            transformer.setParameter("versionParam", "2.0");

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());
            // Start XSLT transformation and FOP processing
            transformer.transform(toStreamSource(data), res);

        } finally {
            out.close();
        }

        return out.toByteArray();
    }
}
