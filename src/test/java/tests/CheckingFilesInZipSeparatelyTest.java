package tests;

import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class CheckingFilesInZipSeparatelyTest {
    private final ClassLoader cl = getClass().getClassLoader();
    ReadingZipFilesTest zipFiles = new ReadingZipFilesTest();

    @Test
    public void pdfFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestZip.zip");
             ZipInputStream zipArch = new ZipInputStream(is))
        {
            ZipEntry entry;
            while ((entry = zipArch.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    zipFiles.readPdfFromZipTest(zipArch);
                    break;
                }
            }
        }
    }

    @Test
    public void excelFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestZip.zip");
             ZipInputStream zipArch = new ZipInputStream(is)) {

            ZipEntry entry;
            while ((entry = zipArch.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xls")) {
                    zipFiles.readExcelFromZipTest(zipArch);
                    break;
                }
            }
        }
    }

    @Test
    public void csvFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestZip.zip");
             ZipInputStream zipArch = new ZipInputStream(is)) {

            ZipEntry entry;
            while ((entry = zipArch.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    zipFiles.readCsvFromZipTest(zipArch);
                    break;
                }
            }
        }
    }
}

