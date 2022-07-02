/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programação II
Concluido em: 09/05/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************/

package app.model.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;

import app.model.facades.ProductFacade;
import app.model.facades.ProviderFacade;
import app.model.models.Entity;
import app.model.models.Product;
import app.model.models.Provider;

public class ManagementReportProvider {
	
	/**
	 * Gera o pdf com o relatorio
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public void generatePDF() throws IdDoesntExist, EntitiesNotRegistred {
		Document document = new Document();
		String name = "fornecedor_" + dateHour() + ".pdf";
        
		try {
            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();

            Paragraph p = new Paragraph("Relatorio de Fornecedores");
            p.setAlignment(1);
            document.add(p);
            
            p = new Paragraph(" ");
            document.add(p);
            
            p = new Paragraph("Fornecedores por Fornecedores:");
            document.add(p);
            
            p = new Paragraph(" ");
            document.add(p);
            
            providerRelationship(p, document);
            
            p = new Paragraph(" ");
            document.add(p);
            
            p = new Paragraph("Fornecedores por Produtos:");
            document.add(p);
            
            p = new Paragraph(" ");
            document.add(p);
            
            providerProduct(p, document);
            
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
	 * Adiciona no pdf as informações do fornecedor
	 * @param p
	 * @param document
	 * @throws DocumentException
	 */
	public void providerRelationship(Paragraph p, Document document) throws DocumentException {
		p = new Paragraph(" ");
        document.add(p);
        
        ProviderFacade.updateAllProducstProvided();
        int cont = 1;
        for (Provider prov : ProviderFacade.listProvider()) {
        	p = new Paragraph(cont++ + " - ID: " + prov.getId() + "\n" + 
					   "Nome: " + prov.getName()+ "\n" + 
					   "CNPJ: " + prov.getCnpj()+ "\n" +
					   "Endereco: " + prov.getAddress() + "\n\n" +
					   "Produtos fornecidos:" + "\n");
        	document.add(p);
        	
        	for (Product prod : prov.getProductsProvided()) {
        		p = new Paragraph("     ID: " + prod.getId() + "\n" +
        						  "     Nome: " + prod.getName() + "\n\n");
        		document.add(p);
        	}
        	
        	p = new Paragraph(" ");
            document.add(p);
        }
        
	}
	
	/**
	 * Adiciona as informacoes dos produtos no pdf
	 * @param p
	 * @param document
	 * @throws DocumentException
	 */
	public void providerProduct(Paragraph p, Document document) throws DocumentException {
		p = new Paragraph(" ");
        document.add(p);
        
        int cont = 1;
        for (Product prod : ProductFacade.listProduct()) {
        	p = new Paragraph(cont++ + " - ID: " + prod.getId() + "\n" + 
							   "Nome: " + prod.getName() + "\n" + 
							   "Preco: R$ " + prod.getPrice() + "\n" +
							   "Fornecedor: \n" + 
							   "     ID: " + prod.getProvider().getId() + "\n" +
							   "     Nome: "+ prod.getProvider().getName());
        	document.add(p);
        	
        	p = new Paragraph(" ");
            document.add(p);
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
