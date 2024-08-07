package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class CheckingZipArchive {
    private final ClassLoader cl = getClass().getClassLoader();

    @Test
    public void openingZipArchive() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestZip.zip")) {
            if (is == null) {
                throw new FileNotFoundException("ZIP not found");
            }

            try (ZipInputStream zipArch = new ZipInputStream(is)) {
                ZipEntry entry;

                while ((entry = zipArch.getNextEntry()) != null) {
                    if (entry.getName().endsWith(".pdf")) {
                        readPdfFromZip(zipArch);
                    } else if (entry.getName().endsWith(".xls")) {
                        readExcelFromZip(zipArch);
                    }
                    else if (entry.getName().endsWith(".csv")) {
                        readCsvFromZip(zipArch);
                    }
                    zipArch.closeEntry();
                }
            }
        }
    }
     void readPdfFromZip(ZipInputStream zipInputStream) throws Exception {
        PDF pdf = new PDF(zipInputStream);
        String actual = pdf.text;
        String expectedPdf1 = "Pdf file for testng";

        assertTrue(actual.contains(expectedPdf1));
    }

     void readExcelFromZip(ZipInputStream zipInputStream) throws Exception {
        XLS xls = new XLS(zipInputStream);
        String firstUser = xls.excel.getSheetAt(0).getRow(0).getCell(0).toString();
        String firstUserXls = "dog1";
        assertEquals(firstUserXls, firstUser);

        String secondUser = xls.excel.getSheetAt(0).getRow(1).getCell(0).toString();
        String secondUserXls = "dog2";
        assertEquals(secondUserXls, secondUser);
    }

     void readCsvFromZip(ZipInputStream zipInputStream) throws Exception {
             CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream));

            List<String[]> csvData = csvReader.readAll();

            assertEquals(2,csvData.size());
            assertArrayEquals(
                    new String [] {"id", "Name", "Password"},
                    csvData.get(0)
            );
            assertArrayEquals(
                    new String [] {"131", "iivanova", "iivanova"},
                    csvData.get(1)
            );

    }
}
