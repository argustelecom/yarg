package sample.incomes;

import com.haulmont.yarg.formatters.factory.DefaultFormatterFactory;
import com.haulmont.yarg.loaders.factory.DefaultLoaderFactory;
import com.haulmont.yarg.loaders.impl.GroovyDataLoader;
import com.haulmont.yarg.reporting.ReportOutputDocument;
import com.haulmont.yarg.reporting.Reporting;
import com.haulmont.yarg.reporting.RunParams;
import com.haulmont.yarg.structure.Report;
import com.haulmont.yarg.structure.xml.impl.DefaultXmlReader;
import com.haulmont.yarg.util.groovy.DefaultScriptingImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

import static org.apache.commons.io.FileUtils.readFileToString;

/**
 * @author degtyarjov
 * @version $Id$
 */

public class IncomesTest {
    @Test
    public void testIncomesReport() throws Exception {
        Report report = new DefaultXmlReader()
                .parseXml(readFileToString(new File("./modules/core/test/sample/incomes/incomes.xml")));

        Reporting reporting = new Reporting();
        reporting.setFormatterFactory(new DefaultFormatterFactory());
        reporting.setLoaderFactory(
                new DefaultLoaderFactory()
                        .setGroovyDataLoader(new GroovyDataLoader(new DefaultScriptingImpl())));

        ReportOutputDocument reportOutputDocument = reporting.runReport(
                new RunParams(report), new FileOutputStream("./result/sample/incomes.xlsx"));
    }
}
