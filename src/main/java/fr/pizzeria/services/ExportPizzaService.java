package fr.pizzeria.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

public class ExportPizzaService extends MenuService {

	@Override
	public void executeUC(PizzaMemDao pizzaMemDao, Scanner scan) throws StockageException {

		ResourceBundle file = ResourceBundle.getBundle("conf");
		String pdfPath = file.getString("path.absolute");
		pdfPath += "Liste_Pizza.pdf";
		
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.open();
		for (Pizza p : pizzaMemDao.findAllPizzas()) {
			try {
				document.add(new Paragraph(p.toString()));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		document.close();
	}
}
