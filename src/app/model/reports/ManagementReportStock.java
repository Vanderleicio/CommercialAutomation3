package app.model.reports;

/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programacao II
Concluido em: 02/07/2022
Declaro que este codigo foi elaborado por mim de forma individual e nao contem nenhum
trecho de codigo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletronicos da Internet. Qualquer trecho de codigo
de outra autoria que nao a minha esta destacado com uma citacao para o autor e a fonte
do codigo, e estou ciente que estes trechos nao serao considerados para fins de avaliacao.
******************************/

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
public class ManagementReportStock {
	
	/**
	 * Metodo responsavel por gerar o relatorio em pdf
	 * @param idProd = id de um produto escolhido pelo usuario
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public void generatePDF(String idProd) throws IdDoesntExist, EntitiesNotRegistred {
		Document document = new Document();
		String name = "produto_" + dateHour() + ".pdf";
		ProductFacade.chooseAProduct(idProd);
		
        try {
            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();

            Paragraph p = new Paragraph("Relatorio de Produtos");
            p.setAlignment(1);
            document.add(p);
            
            p = new Paragraph(" ");
            document.add(p);
            
            p = new Paragraph("Todos os produtos no estoque: ");
            document.add(p);
            
            totalAmountOfStock(p, document);
            
            p = new Paragraph(" ");
            document.add(p);
   
            p = new Paragraph("Informacoes do produto selecionado: ");
            document.add(p);
            
            byProduct(idProd, p, document);
            
            p = new Paragraph(" ");
            document.add(p);
            
            p = new Paragraph("Produtos que vencem no proximo mes: ");
            document.add(p);
            
            p = new Paragraph(" ");
            document.add(p);
            
            productsToExpire(p, document);
            
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
	 * Metodo responsavel por informar os dados de quantidade total de estoque
	 * @throws DocumentException
	 */
	public void totalAmountOfStock(Paragraph p, Document document) throws DocumentException {
		p = new Paragraph(" ");
        document.add(p);
    		
		for (Product prod : ProductFacade.listProduct()) {
			p = new Paragraph("\nID: " + prod.getId() + "\n" + 
					   "Fornecedor: " + prod.getProvider().getName()+ "\n" + 
					   "Nome: " + prod.getName()+ "\n" +
					   "Quantidade: " + prod.getQuantity() + " unidades\n" +
					   "Validade: " + prod.getValidity() + "\n");
			document.add(p);
		
    	}
		
		p = new Paragraph(" ");
        document.add(p);
		
	}
	
	/**
	 * Metodo responsavel por gerar os dados do produto selecionado anteriormente pelo usuario
	 * @param idProd = id escolhido anteriormente
	 * @throws DocumentException
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public void byProduct(String idProd, Paragraph p, Document document) throws DocumentException, IdDoesntExist, EntitiesNotRegistred {
		
		Product prod = ProductFacade.chosenProduct();
		
		p = new Paragraph(" ");
        document.add(p);
    		
    	p = new Paragraph("\nID: " + prod.getId() + "\n" + 
    					  "Nome: " + prod.getName() + "\n" +
					   	  "Fornecedor: " + prod.getProvider().getName()+ "\n" + 
					      "Preco: R$" + prod.getPrice()+ "\n" +
					      "Quantidade: " + prod.getQuantity() + " unidades\n" +
					      "Validade: " + prod.getValidity() + "\n");
    	document.add(p);   
    	
        p = new Paragraph(" ");
        document.add(p);
	}
	
	/**
	 * Metodo responsavel por gerar as informacoes de vencimento do relatorio em pdf
	 * @throws DocumentException
	 */
	public void productsToExpire(Paragraph p, Document document) throws DocumentException {
		LocalDate currentDay = LocalDate.now();
		LocalDate nextMonth = currentDay.plusMonths(1);
		
		p = new Paragraph(" ");
        document.add(p);
        
		for (Product prod  : ProductFacade.listProduct()) {
			if (nextMonth.isAfter(prod.getValidity()) && currentDay.isBefore(prod.getValidity())) {		    		
		    	p = new Paragraph("\nID: " + prod.getId() + "\n" + 
		    					  "Validade: " + prod.getValidity() + "\n" +
		    					  "Nome: " + prod.getName() + "\n" +
							   	  "Fornecedor: " + prod.getProvider().getName()+ "\n" + 
							      "Preco: R$" + prod.getPrice()+ "\n" +
							      "Quantidade: " + prod.getQuantity() + " unidades\n");
		    	document.add(p);   
			}
		}
		
		p = new Paragraph("\nProdutos ja vencidos:\n");
		document.add(p);  
		
		for (Product prod : ProductFacade.listProduct()) {
			if (currentDay.isAfter(prod.getValidity())) {		    		
		    	p = new Paragraph("\nID: " + prod.getId() + "\n" + 
		    					  "Validade: " + prod.getValidity() + "\n" +
		    					  "Nome: " + prod.getName() + "\n" +
							   	  "Fornecedor: " + prod.getProvider().getName()+ "\n" + 
							      "Preco: R$" + prod.getPrice()+ "\n" +
							      "Quantidade: " + prod.getQuantity() + " unidades\n");
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
