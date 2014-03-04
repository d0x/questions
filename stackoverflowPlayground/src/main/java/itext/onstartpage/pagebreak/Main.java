package itext.onstartpage.pagebreak;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class Main {
    public static String someTompic = null;

    public static void main(final String[] args) throws Exception {
        final Document document = new Document(PageSize.A7);

        final PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("text.pdf"));

        // This PageEventHelper should write a small text "Continuation: ..." at the beginning if
        // a PageBreak was forced by "keepTogether"
        writer.setPageEvent(new PdfPageEventHelper() {

            @Override
            public void onStartPage(final PdfWriter writer, final Document document) {
                try {
                    if (someTompic != null)
                        document.add(new Paragraph("Continuation: " + someTompic));
                }
                catch (final DocumentException e) {
                    e.printStackTrace();
                }
            }
        });

        document.open();

        // Now I add the introduction text
        document.add(new Paragraph("A nice \nIntroduction \nover some lines."));

        // Now I put my "huge" thing. If this breaks,
        // the first line of the new page should be Continuation of ...
        someTompic = "smth.";

        final Paragraph paragraph = new Paragraph("What is \nthe answer \nto life the \nuniverse and \neverything?\n\nThe Answer to \nthis question \nis:\n42");
        paragraph.setKeepTogether(true);
        document.add(paragraph);

        document.close();
    }
}
