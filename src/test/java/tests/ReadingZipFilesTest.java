package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReadingZipFilesTest {
    
    public void readPdfFromZipTest(ZipInputStream zipInputStream) throws Exception {
        PDF pdf = new PDF(zipInputStream);
        String actual = pdf.text;
        String expectedPdf1 = "Pdf file for testng";

        assertThat(actual).contains(expectedPdf1);
    }

    public void readExcelFromZipTest(ZipInputStream zipInputStream) throws Exception {
        XLS xls = new XLS(zipInputStream);
        String firstUser = xls.excel.getSheetAt(0).getRow(0).getCell(0).toString();
        String firstUserXls = "dog1";
        String secondUser = xls.excel.getSheetAt(0).getRow(1).getCell(0).toString();
        String secondUserXls = "dog2";

        assertThat(firstUser).isEqualTo(firstUserXls);
        assertThat(secondUser).isEqualTo(secondUserXls);    }

    public void readCsvFromZipTest(ZipInputStream zipInputStream) throws Exception {
        CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream));

        List<String[]> csvData = csvReader.readAll();

        assertThat(csvData.get(0)).containsExactly("id", "Name", "Password");
        assertThat(csvData.get(1)).containsExactly("131", "iivanova", "iivanova");

    }
}
