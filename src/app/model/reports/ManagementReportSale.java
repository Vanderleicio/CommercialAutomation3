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

import app.model.facades.MenuFacade;
import app.model.facades.SaleFacade;
import app.model.models.Entity;
import app.model.models.Item;
import app.model.models.Sale;
import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;

public class ManagementReportSale {
	
	/**
	 * Gera o pdf com o relatorio
	 * @param sales: Gerenciamento das vendas
	 * @param itemsMenu: Gerenciamento do cardapio
	 * @param dateBefore: Primeira data do periodo
	 * @param dateAfter: Segunda data do periodo
	 * @param idPlate: Id do prato a ser exibido
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public void generatePDF(LocalDate dateBefore, LocalDate dateAfter, String idPlate) throws IdDoesntExist, EntitiesNotRegistred {
		Document document = new Document();
		String name = "sale_" + dateHour() + ".pdf";
        
		try {
            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();

            Paragraph p = new Paragraph("Relatorio de Vendas");
            p.setAlignment(1);
            document.add(p);
            
            p = new Paragraph(" ");
            document.add(p);
            
            p = new Paragraph("Vendas realizadas: ");
            document.add(p);
            
            salesTotal(p, document);
            
            p = new Paragraph(" ");
            document.add(p);
   
            p = new Paragraph("Vendas realizadas por periodo: ");
            document.add(p);
            
            saleByPeriod(p, document, dateBefore, dateAfter);
            
            p = new Paragraph(" ");
            document.add(p);
            
            p = new Paragraph("Vendas realizadas por tipo de prato do cardapio: ");
            document.add(p);
            
            p = new Paragraph(" ");
            document.add(p);
            
            saleByPlate(p, document, idPlate);
            
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
	 * Adiciona as informacoes de todas as vendas
	 * @param sales
	 * @param p
	 * @param document
	 * @throws DocumentException
	 */
	public void salesTotal(Paragraph p, Document document) throws DocumentException {
        
        p = new Paragraph(" ");
        document.add(p);
        
    	int cont = 1;
    	for (Entity sale : SaleFacade.listSale()) {
    		Sale sales1 = (Sale) sale; 
    		p = new Paragraph(cont++ + "- ID: " + sales1.getId() + "\n" + 
					   "Dia: " + sales1.getDay()+ "\n" + 
					   "Horario: " + sales1.getHour()+ "\n" +
					   "Preco total: R$" + sales1.getPriceTotal() + "\n" +
					   "Modo de Pagamento: " + sales1.getPaymentMethod() + "\n");
        	document.add(p);
        	
        	p = new Paragraph(" ");
            document.add(p);
    	}
	}
	 
	/**
	 * Adiciona as informacoes das vendas dentro do periodo pedido
	 * @param sales
	 * @param p
	 * @param document
	 * @param dateBefore
	 * @param dateAfter
	 * @throws DocumentException
	 */
	public void saleByPeriod(Paragraph p, Document document, LocalDate dateBefore, LocalDate dateAfter) throws DocumentException {
		
		p = new Paragraph(" ");
        document.add(p);
        
        p = new Paragraph("Periodo de " + 
        			dateBefore.getDayOfMonth() + "/" + 
        			dateBefore.getMonthValue() + "/" + 
        			dateBefore.getYear() + " a  " + 
        			dateAfter.getDayOfMonth() + "/" + 
        			dateAfter.getMonthValue() + "/" + 
        			dateAfter.getYear() );
        document.add(p);
        
        p = new Paragraph(" ");
        document.add(p);
	
        int cont = 1;
        for (Entity i : SaleFacade.listSale()) {
			Sale sales1 = (Sale) i;
			boolean before = dateBefore.isBefore(sales1.getDay());
			boolean after = dateAfter.isAfter(sales1.getDay());
			if (before && after) {
				p = new Paragraph(cont++ + "- ID: " + sales1.getId() + "\n" + 
	 					   "Dia: " + sales1.getDay()+ "\n" + 
	 					   "Horario: " + sales1.getHour()+ "\n" +
	 					   "Preco total: R$" + sales1.getPriceTotal() + "\n" +
	 					   "Modo de Pagamento: " + sales1.getPaymentMethod() + "\n");
				document.add(p);
				
				p = new Paragraph(" ");
		        document.add(p);
			} 
			
        }
	}
	
	/**
	 * Adiciona as informacoes das vendas do prato pedido
	 * @param sales
	 * @param itemsMenu
	 * @param p
	 * @param document
	 * @param idPlate
	 * @throws DocumentException
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public void saleByPlate(Paragraph p, Document document, String idPlate) throws DocumentException, IdDoesntExist, EntitiesNotRegistred {
		
		Item itemChoosed = MenuFacade.chosenItem();
		p = new Paragraph(" ");
        document.add(p);
        
        p = new Paragraph("Prato: " + itemChoosed.getName());
        document.add(p);
        
        p = new Paragraph(" ");
        document.add(p);
        
        int cont = 1;
        for (Entity i : SaleFacade.listSale()) {
			Sale sale = (Sale) i;
			if (sale.getItemsPurchased().contains(itemChoosed)) {
				p = new Paragraph(cont++ + "- ID: " + sale.getId() + "\n" +
									"Dia: " + sale.getDay() + "\n" +
									"Hora: " + sale.getHour() + "\n" +
									"Preco: R$" + sale.getPriceTotal() + "\n" +
									"Metodo de pagamento: " + sale.getPaymentMethod());
				document.add(p);
				p = new Paragraph(" ");
		        document.add(p);
			} 
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
