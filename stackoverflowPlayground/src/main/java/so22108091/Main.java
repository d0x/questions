package so22108091;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * taken from: http://osdir.com/ml/java.lib.itext.general/2003-01/msg00002.html
 */
public class Main extends PdfPageEventHelper
{
    private static final Paragraph          DUMMY_PARAGRAPH = new Paragraph("Lorem ipsum dolor sit amet.");

    private final Document                  document;
    private final PdfContentByte            cb;
    private final PdfWriter                 writer;

    private final PdfTemplate               totalPagesPlaceholder;

    // table to store templates for all chapters
    private final Map<Integer, PdfTemplate> tocPlaceholder  = new HashMap<>();

    // this is the BaseFont we are going to use for the header / footer
    private final BaseFont                  baseFont        = BaseFont.createFont();
    private final Font                      chapterFont     = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.NORMAL);

    // Holds current position. Updated at the end of each paragraph
    private float                           currentY        = 0;

    public static void main(String[] args) throws Exception
    {
        Main main = new Main();

        main.document.add(new Paragraph("This is an example to generate a TOC."));

        main.createTOC(7);
        main.createChapters(7);

        main.finalzeDocument();
    }

    public Main() throws Exception
    {
        this.document = new Document(PageSize.A6);
        this.writer = PdfWriter.getInstance(document, new FileOutputStream("text.pdf"));
        this.writer.setPageEvent(this);
        this.document.open();
        this.cb = writer.getDirectContent();
        this.totalPagesPlaceholder = cb.createTemplate(50, 50);
    }

    /**
     * Add "Page x of n" at the bottom of the page.
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document)
    {
        String text = "Page " + writer.getPageNumber() + " of ";
        float len = baseFont.getWidthPoint(text, 8);
        cb.beginText();
        cb.setFontAndSize(baseFont, 8);
        cb.setTextMatrix(140, 30);
        cb.showText(text);
        cb.endText();
        cb.addTemplate(totalPagesPlaceholder, 140 + len, 30);
    }

    @Override
    public void onParagraphEnd(PdfWriter writer, Document document, float position)
    {
        currentY = position;
    }

    private void createTOC(int count) throws DocumentException
    {
        Chapter title = new Chapter(new Paragraph("This is TOC ", chapterFont), 0);
        title.setNumberDepth(0);
        document.add(title);

        for (int i = 1; i < count + 1; i++)
        {
            String text = "Chapter " + i + " - page ";
            Chunk chunk = new Chunk(text).setLocalGoto("Chapter " + i);
            document.add(new Paragraph(chunk));
            float len = baseFont.getWidthPoint(text, 12);
            PdfTemplate template = cb.createTemplate(50, 50);
            tocPlaceholder.put(i, template);
            cb.addTemplate(template, 50 + len, currentY);
        }
    }

    private void createChapters(int count) throws DocumentException
    {
        for (int i = 1; i < count + 1; i++)
        {
            Chunk chunk = new Chunk("Chapter " + i, chapterFont).setLocalDestination("Chapter " + i);
            Chapter chapter = new Chapter(new Paragraph(chunk), i);
            chapter.setNumberDepth(0);

            PdfTemplate template = tocPlaceholder.get(i);

            template.beginText();
            template.setFontAndSize(baseFont, 12);

            template.showText(String.valueOf(writer.getPageNumber()));
            template.endText();

            chapter.add(DUMMY_PARAGRAPH);

            document.add(chapter);
        }
    }

    private void finalzeDocument()
    {
        totalPagesPlaceholder.beginText();
        totalPagesPlaceholder.setFontAndSize(baseFont, 8);
        totalPagesPlaceholder.showText(String.valueOf(writer.getPageNumber()));
        totalPagesPlaceholder.endText();

        document.close();
    }
}
