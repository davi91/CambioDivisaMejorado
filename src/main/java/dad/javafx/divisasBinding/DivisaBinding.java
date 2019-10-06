package dad.javafx.divisasBinding;

import dad.common.Divisa;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.StringExpression;

public class DivisaBinding extends StringBinding {

	private StringExpression text;
	private ObjectExpression<Divisa> fromSelect, toSelect;
	
	/*
	 * Creamos nuestro binding, necesitamos el texto, el select del from y el select del to.
	 * Lo que cambia es a partir del objeto Divisa.
	 */
	public DivisaBinding(StringExpression text, ObjectExpression<Divisa> fromSelect, ObjectExpression<Divisa> toSelect) {
		
		this.text = text;
		this.fromSelect = fromSelect;
		this.toSelect = toSelect;
		bind(text, fromSelect, toSelect); // Si cualquiera de los 3 cambia, entonces se ejecuta computeValue
	}
	
	private boolean isNumeric(String txt) {
		
		try {
			
			// Si falla, ya sabemos que es algo que no se parece a un número
			@SuppressWarnings("unused")
			double n = Double.parseDouble(txt);
			
		} catch( NumberFormatException e ) {
			return false;
		}
		
		return true;
	}
	
	@Override
	protected String computeValue() {
		
		if( text.getValue().isEmpty() || !isNumeric(text.getValue())) { // Por devolver algo
			return "-1";
		}
		
		Double cantidad = Double.parseDouble(text.getValue()); // Ya sabemos que no dará error
		return String.format("%.4f", toSelect.getValue().fromEuro(fromSelect.getValue().toEuro(cantidad)));
	}

}
