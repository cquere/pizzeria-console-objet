package fr.pizzeria.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

public class ExportPizzaService extends MenuService {

	@Override
	public void executeUC(IPizzaDao pizzaMemDao, Scanner scan) throws StockageException {

		ResourceBundle file = ResourceBundle.getBundle("conf");
		String pdfPath = file.getString("path.absolute");
		pdfPath += "Liste_Pizza.pdf";
		
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
		} catch (FileNotFoundException e) {
			LOGERROR.error(e.getMessage());
		} catch (DocumentException e) {
			LOGERROR.error(e.getMessage());
		}
		document.open();
		for (Pizza p : pizzaMemDao.findAllPizzas()) {
			try {
				document.add(new Paragraph(p.toString()));
			} catch (DocumentException e) {
				LOGERROR.error(e.getMessage());
			}
		}
		document.close();
	}
}
