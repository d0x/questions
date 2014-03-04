package itext.onstartpage.pagebreak;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class MainTableHack
{
    public static void main(final String[] args) throws Exception
    {
        final Document document = new Document(PageSize.A7);

        PdfWriter.getInstance(document, new FileOutputStream("textTableHack.pdf"));

        document.open();

        // The content gets wrapped into an table. If the table breaks
        // we utilize the "headerRows" to print `Continuation of xx`.
        final PdfPTable table = new PdfPTable(1);
        table.setHeadersInEvent(false);
        table.setHeaderRows(1);
        table.setSkipFirstHeader(true);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        table.getDefaultCell().setPadding(0);

        // This is the header we print ONLY if the page breaks.
        table.addCell("Continuation of " + "smth.");

        // Now I add the introduction text
        final PdfPTable firstText = new PdfPTable(1);
        firstText.addCell("A nice \nIntroduction \ninto \"smth.\" \nover some lines.");
        // in the original code i add some other things here as well
        table.addCell(firstText);

        // Now I put a "huge" paragraph (it will not fit on the first page
        final PdfPTable secondText = new PdfPTable(1);
        secondText.addCell("Force \na pagebreak\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf\nasdf.");
        // in the original code i add some other things here as well
        table.addCell(secondText);

        document.add(table);

        document.close();
    }
}
