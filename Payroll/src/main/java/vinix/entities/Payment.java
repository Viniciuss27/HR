package vinix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // getters + setters + equals + hashCode + toString
@NoArgsConstructor  // construtor vazio
@AllArgsConstructor // construtor com todos os atributos
public class Payment {
	
	  private String name;
	  private Double dailyIncome;
	  private Integer days;
	  
	  public Double getTotal() {
		  return dailyIncome * days;
	  }

}
