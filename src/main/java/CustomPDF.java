import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

public class CustomPDF {
    public void generateBill(String data){
        Document document = new Document();
        try
        {
            Random rand = new Random();
            int random_number= rand.nextInt(85000);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Bills\\Invoice" +random_number +".pdf"));
            document.open();
            document.add(new Paragraph(data));
            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
