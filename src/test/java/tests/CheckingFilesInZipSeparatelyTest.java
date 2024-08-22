package tests;

import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CheckingFilesInZipSeparatelyTest {
    private final ClassLoader cl = getClass().getClassLoader();
    ReadingZipFilesTest zipFiles = new ReadingZipFilesTest();

    @Test
    public void pdfFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestZip.zip");
             ZipInputStream zipArch = new ZipInputStream(is))
        {
            ZipEntry entry;
            boolean pdfFound = false;
            while ((entry = zipArch.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    zipFiles.readPdfFromZip2(zipArch);
                    pdfFound = true;
                    break;
                }
            }
            assertThat(pdfFound)
                    .as("PDF file not found in the ZIP archive")
                    .isTrue();
        }
    }

    @Test
    public void excelFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestZip.zip");
             ZipInputStream zipArch = new ZipInputStream(is)) {

            ZipEntry entry;
            boolean xlsFound = false;
            while ((entry = zipArch.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xls")) {
                    zipFiles.readExcelFromZip2(zipArch);
                    xlsFound = true;
                    break;
                }
            }
            assertThat(xlsFound)
                    .as("XLS file not found")
                    .isTrue();
        }
    }

    @Test
    public void csvFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestZip.zip");
             ZipInputStream zipArch = new ZipInputStream(is)) {

            ZipEntry entry;
            boolean csvFound = false;
            while ((entry = zipArch.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    zipFiles.readCsvFromZip2(zipArch);
                    csvFound = true;
                    break;
                }
            }
            assertThat(csvFound)
                    .as("CSV file not found")
                    .isTrue();
        }
    }
}

