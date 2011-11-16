/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.List;

public class SimbolosCodificados
{
	private List<Character> _simbolos;
	private List<String> _simbolosCodificados;

	public SimbolosCodificados( List<Character> simbolos,
			List<String> simbolosCodificados )
	{
		_simbolos = simbolos;
		_simbolosCodificados = simbolosCodificados;
	}

	public List<Character> getSimbolos()
	{
		return _simbolos;
	}

	public List<String> getSimbolosCodificados()
	{
		return _simbolosCodificados;
	}
}
