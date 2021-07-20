package BL;

import BL.Constant;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

/*
 *  Name of file: BL.CustomPDF.java
 *  Author:  Nadish Maredia
 *  Purpose: This class create the pdf file in folder
 *  Description: This class is responsible for saving or download the bill
 * */
public class CustomPDF {


    public Boolean generateBill(String data)  {

        Document document = new Document();
        try  {
            Random rand = new Random();
            int random_number= rand.nextInt(Constant.RANDOM_NUMBER_LIMIT);
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(Constant.INVOICE_FILE_PATH +random_number +".pdf"));
            document.open();
            document.add(new Paragraph(data));
            document.close();
            writer.close();
            System.out.println("PDF SAVED");
            return true;
        }  catch  (DocumentException e)  {
            e.printStackTrace();
        }  catch  (FileNotFoundException e)  {
            e.printStackTrace();
        }

        return false;
    }
}
