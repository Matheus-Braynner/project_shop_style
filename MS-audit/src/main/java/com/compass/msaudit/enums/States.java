package com.compass.msaudit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum States {

	  AZONAS("Amazonas", "AM", "Manaus"),
	  ALAGOAS("Alagoas", "AL", "Maceió"),
	  ACRE("Acre", "AC", "Rio Branco"),
	  AMAPA("Amapá", "AP", "Macapá"),
	  BAHIA("Bahia", "BA", "Salvador"),
	  PARA("Pará", "PA", "Belém"),
	  MATO_GROSSO("Mato Grosso", "MT", "Cuiabá"),
	  MINAS_GERAIS("Minas Gerais", "MG", "Belo Horizonte"),
	  MATO_GROSSO_DO_SUL("Mato Grosso do Sul", "MS", "Campo Grande"),
	  GOIAS("Goiás", "GO", "Goiânia"),
	  MARANHAO("Maranhão", "MA", "São Luís"),
	  RIO_GRANDE_DO_SUL("Rio Grande do Sul", "RS", "Porto Alegre"),
	  TOCANTINS("Tocantins", "TO", "Palmas"),
	  PIAUI("Piauí", "PI", "Teresina"),
	  SAO_PAULO("São Paulo", "SP", "São Paulo"),
	  RONDONIA("Rondônia", "RO", "Porto Velho"),
	  RORAIMA("Roraima", "RR", "Boa Vista"),
	  PARANA("Paraná", "PR", "Curitiba"),
	  CEARA("Ceará", "CE", "Fortaleza"),
	  PERNAMBUCO("Pernambuco", "PE", "Recife"),
	  SANTA_CATARINA("Santa Catarina", "SC", "Florianópolis"),
	  PARAIBA("Paraíba", "PB", "João Pessoa"),
	  RIO_GRANDE_DO_NORTE("Rio Grande do Norte", "RN", "Natal"),
	  ESPIRITO_SANTO("Espírito Santo", "ES", "Vitória"),
	  RIO_DE_JANEIRO("Rio de Janeiro", "RJ", "Rio de Janeiro"),
	  SERGIPE("Sergipe", "SE", "Aracaju"),
	  DISTRITO_FEDERAL("Distrito Federal", "DF", "Brasília");
	
		private String name;
		private String initials;
		private String capital;
		
		public static States fromUF(final String nomeUf) {
		    for (final States uf : States.values()) {
		      if (uf.name.equalsIgnoreCase(nomeUf)) {
		        return uf;
		      }
		    }

		    throw new IllegalArgumentException(nomeUf);
		  }
		
		  public static States fromSigla(final String sigla) {
			    for (final States uf : States.values()) {
			      if (uf.initials.equalsIgnoreCase(sigla)) {
			        return uf;
			      }
			    }

			    throw new IllegalArgumentException(sigla);
			  }

		  public static States fromCapital(final String capital) {
			    for (final States uf : States.values()) {
			      if (uf.capital.equalsIgnoreCase(capital)) {
			        return uf;
			      }
			    }

			    throw new IllegalArgumentException(capital);
			  }

		  
		  @Override
		  public String toString() {
		    final StringBuilder sb = new StringBuilder("States{");
		    sb.append("nome='").append(name).append('\'');
		    sb.append(", sigla='").append(initials).append('\'');
		    sb.append(", capital='").append(capital).append('\'');
		    sb.append('}');
		    return sb.toString();
		  }
		
		
		 
	
}
