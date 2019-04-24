package com.example.myapplication;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DrawInterface;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class TemplatePDF extends AppCompatActivity {



    private Context context;
    public static File archivoPDF;
    private Document documento;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private Font fHighText = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.RED);
    private Font txtTabla = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
    private Font fServicio = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.ITALIC, BaseColor.BLACK);

    private Font Celular = new Font(Font.FontFamily.TIMES_ROMAN,9, Font.NORMAL, BaseColor.BLACK);
    private Font PaginaWeb = new Font(Font.FontFamily.TIMES_ROMAN,9, Font.NORMAL, BaseColor.BLACK);

    public String nombreArchivo;

    private BaseColor verde = new BaseColor(59,152,78);


    private String NombreEmpresa = "";
    private String Direccion = "";


    public TemplatePDF(Context context) {

        this.context = context;
    }

    //prueba

    //METODO PARA ABRIR EL DOCUMENTO
    public void abrirDocumento() {

        try {

            documento = new Document(PageSize.A4);
            pdfWriter = PdfWriter.getInstance(documento, new FileOutputStream(archivoPDF));
            documento.open();


        } catch (Exception e) {
            Log.e("openDocument", e.toString());

        }

    }

    //METODO PARA CREAR EL DOCUMENTO
    public void crearPDF(String nombrexd, String direccionxd) {

        Date date = new Date() ;
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(date);
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"PDF");

        if (!folder.exists())
            folder.mkdir();
        archivoPDF = new File(folder, "OT_"+ nombrexd +"_" + direccionxd + "_" + timeStamp +".pdf");





    }

    public void closeDocument() {
        documento.close();
    }

    public void addMetaData(String title, String subject, String autor){
        documento.addTitle(title);
        documento.addSubject(subject);
        documento.addAuthor(autor);
    }

    public void addTitles(String title, String subTitle) {


        fSubTitle.setColor(59,152,78);
        try {

            paragraph = new Paragraph();
            addChildP(new Paragraph(title, fTitle));
            addChildP(new Paragraph(subTitle, fSubTitle));
            paragraph.setSpacingAfter(0);


            documento.add(paragraph);

        } catch (Exception e) {
            Log.e("addTitles", e.toString());
        }
    }


    public void addContact(String Cell, String Web, String title, String Subtitle){

        fSubTitle.setColor(59,152,78);
        Chunk celular = new Chunk(Cell, Celular);
        Chunk web = new Chunk(Web, PaginaWeb);
        Chunk titulo = new Chunk(title, fTitle);
        Chunk subtitulo = new Chunk(Subtitle, fSubTitle);


        try {
            //Solucion 1
/*
            paragraph = new Paragraph();
            //addChildP2(new Paragraph(String.valueOf(celular), Celular));
            //addChildP2(new Paragraph(String.valueOf(web), PaginaWeb));
            paragraph.setTabSettings(new TabSettings(325f));
            paragraph.add(celular);
            paragraph.add("\n");
            paragraph.add(web);

            paragraph.add(Chunk.TABBING);

            paragraph.add(titulo);
            paragraph.add("\n");
            paragraph.add(subtitulo);
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);

            paragraph.setSpacingAfter(0);
*/
            //Solucion 2

            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph p = new Paragraph();
            p.add(celular);
            p.add(new Chunk(glue));
            p.add(titulo);
            p.add("\n");
            p.add(web);
            p.add(new Chunk(glue));
            p.add(subtitulo);
            p.setAlignment(Element.ALIGN_JUSTIFIED);



            documento.add(p);

        } catch (Exception e) {
            Log.e("addTitles", e.toString());
        }
    }

    public void addFirmas(Image imgR, Image imgC){
        //Chunk firmaR = new Chunk( imgR);
        //Chunk firmaC = new Chunk( imgC);
        try{
            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph p = new Paragraph();
            imgR.scalePercent(70);
            imgC.scalePercent(15);
            p.add(new Chunk(imgR,0,0,true));
            p.add(new Chunk(glue));
            p.add(new Chunk(imgC,0,0,true));
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(p);

        } catch(Exception e){}
    }

    public void Servicio(String S){
        try{

            paragraph = new Paragraph();
            addChildp3(new Paragraph(S, fServicio));
            paragraph.setSpacingAfter(1);
            paragraph.setAlignment(Element.ALIGN_CENTER);

            documento.add(paragraph);
        } catch (Exception e){
            Log.e("addServicio", e.toString());
        }
    }


    private void addChildP2(Paragraph childParagraph){
        childParagraph.setAlignment(Element.CHUNK);
        //childParagraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add(childParagraph);
    }

    private void addChildP(Paragraph childParagraph) {
        childParagraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.add(childParagraph);
    }

    private void addChildp3(Paragraph child){
        child.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(child);
    }


    public void addImage(Image image){
        try {

            image.setAlignment(Image.LEFT);
            image.scalePercent(50);
            documento.add(image);
        }catch (Exception e){}

    }

    public void addImage2(Image image){
        try{

            image.setAlignment(Image.RIGHT);
            image.scalePercent(20);
            documento.add(image);
        }catch (Exception e){}

    }

    public void addImage3(Image image){
        try{
            image.setAlignment(Image.LEFT);
            image.scalePercent(110);
            documento.add(image);
        } catch (Exception e){}
    }

    public void addParagraph(String text) {
        try {
            paragraph = new Paragraph(text, fText);
            paragraph.setSpacingAfter(2);
            paragraph.setSpacingBefore(2);
            documento.add(paragraph);
        } catch (Exception e) {
            Log.e("addParagraph", e.toString());
        }
    }


    public void createTable(String[] header, ArrayList<String[]> clients) {
        paragraph = new Paragraph();
        paragraph.setFont(txtTabla);
        PdfPTable pdfPTable = new PdfPTable(header.length);
        pdfPTable.setWidthPercentage(100);
        pdfPTable.setSpacingBefore(20);
        PdfPCell pdfPCell;
        int indexC = 0;

        while (indexC < header.length) {
            pdfPCell = new PdfPCell(new Phrase(header[indexC++], txtTabla));
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setBackgroundColor(verde);
            pdfPTable.addCell(pdfPCell);
        }

        for (int indexR = 2; indexR < clients.size(); indexR++) {
            String[] row = clients.get(indexR);
            for (indexC = 0; indexC < clients.size(); indexC++) {
                pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setFixedHeight(60);

                pdfPTable.addCell(pdfPCell);

            }
        }

        paragraph.add(pdfPTable);

        try {

            documento.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }

    }


}
