package app.model.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import app.model.facades.ProductFacade;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.models.*;

/**
 * Classe que gera os relatorios relacionados ao estoque
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class Receipt {
	
	public void generatePDF(Sale sale) throws IdDoesntExist, EntitiesNotRegistred {
		Document document = new Document();
		String name = "venda_" + dateHour() + ".pdf";
		
        try {
            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();
            Paragraph p = new Paragraph("Nota da compra:");
            p.setAlignment(1);
            document.add(p);
            
            p = new Paragraph("Informações do cliente: ");
            document.add(p);
            
            Client client = sale.getClient();
            p = new Paragraph("ID: " + client.getId() + "\n" +
            				  "CPF: " + client.getCpf() + "\n" +
            				  "Nome: " + client.getName() + "\n" +
            				  "Nº de telefone: " + client.getPhoneNumber() + "\n" +
            				  "Email: " + client.getEmail());
            document.add(p);
            
            p = new Paragraph(" ");
            document.add(p);
            
            p = new Paragraph("Itens comprados: ");
            document.add(p);
            
            ArrayList<Item> itemsPurchased = sale.getItemsPurchased();
            
            int cont = 1;
            Item item;
            for (int i = 0; i < itemsPurchased.size(); i++) {
            	item = itemsPurchased.get(i);
            	p = new Paragraph("N." + cont++ + "- ID: " + item.getId() + "  Prato: " + item.getName() + "  Tipo: " + item.getCategoryItems() + "  Preço: R$" + item.getPrice());
                document.add(p);
            }
   
            p = new Paragraph(" ");
            document.add(p);
            
            p = new Paragraph("Preço total da compra: R$" + sale.getPriceTotal());
            document.add(p);
            
            p = new Paragraph("Modo de pagamento: " + sale.getPaymentMethod());
            document.add(p);
            
            document.close();
            Desktop.getDesktop().open(new File(name));
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
	}
	
	/**
	 * @return Retorna a data e o dia atual formatados para o nome do arquivo
	 */
	public String dateHour() {
		Date d = Calendar.getInstance().getTime();
		String formatString = "dd.MM.yyyy_hh.mm.ss" ;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat (formatString);
		String formattedDate = simpleDateFormat.format(d) ;
		
		return formattedDate;
	}
}
