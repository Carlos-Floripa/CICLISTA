import java.util.Scanner;

public class Ciclista {
	public static final String METROS="m";
	public static final String KILOMETROS="k";
	public static final String SEGUNDOS="s";
	public static final String MINUTOS="m";
	public static final String HORAS="h";
	public static void main(String[] args){
		Scanner entrada= new Scanner(System.in);
		String controle="S";
		String controleDistancia="";
		String controleTempo="";
		double conversor=0;
		int contador=0;
		double soma=0;
		double velMinima=0;
		double velMaxima=0;
		while(controle.equalsIgnoreCase("s")){
			System.out.println("Escolha unidade distância:");
			do{
				System.out.println("M-Metros");
				System.out.println("K-Kilomêtros");
				controleDistancia=entrada.nextLine();
				if(!controleDistancia.equalsIgnoreCase(METROS)&&!controleDistancia.equalsIgnoreCase(KILOMETROS)){
					System.out.println("OPÇÃO INVÁLIDA");
				}
			}while(controleDistancia.equalsIgnoreCase(METROS)&&controleDistancia.equalsIgnoreCase(KILOMETROS));	
			System.out.println("Escolha unidade tempo:");
			do{
				System.out.println("S-Segundos");
				System.out.println("M-Minutos");
				System.out.println("H-Horas");
				controleTempo=entrada.nextLine();
				if(!controleTempo.equalsIgnoreCase(SEGUNDOS)&&!controleTempo.equalsIgnoreCase(MINUTOS)&&!controleTempo.equalsIgnoreCase(HORAS)){
					System.out.println("OPÇÃO INVÁLIDA");
				}
			}while(!controleTempo.equalsIgnoreCase(SEGUNDOS)&&!controleTempo.equalsIgnoreCase(MINUTOS)&&!controleTempo.equalsIgnoreCase(HORAS));
			System.out.println("Informe Distancia e Tempo");
			String distanciaeTempo=entrada.nextLine();
			double[] valoresTempoDistancia=interpretarValoresDeVelocidadeTempo(distanciaeTempo);
			try {
				if(!controleDistancia.equalsIgnoreCase(KILOMETROS)){
					conversor=converteDistancia(valoresTempoDistancia[0],controleDistancia);
					valoresTempoDistancia[0]=conversor;
				}
				if(!controleTempo.equalsIgnoreCase(HORAS)){
					conversor=converteTempo(valoresTempoDistancia[1],controleTempo,HORAS);
					valoresTempoDistancia[1]=conversor;
				}
				soma=soma+calcularVelocidadeMedia(valoresTempoDistancia);
				contador++;
				if(velMaxima<calcularVelocidadeMedia(valoresTempoDistancia)){
					velMaxima=calcularVelocidadeMedia(valoresTempoDistancia);
				}
				if(contador==1){
					velMinima=calcularVelocidadeMedia(valoresTempoDistancia);
				}
				if(velMinima>calcularVelocidadeMedia(valoresTempoDistancia)){
					velMinima=calcularVelocidadeMedia(valoresTempoDistancia);
				}
				System.out.println("Velocidade Km/h:");
				System.out.println(calcularVelocidadeMedia(valoresTempoDistancia));
				System.out.println("Velocidade m/s:");				
				conversor=converteDistancia(valoresTempoDistancia[0],KILOMETROS);
				valoresTempoDistancia[0]=conversor;
				conversor=converteTempo(valoresTempoDistancia[1],HORAS,SEGUNDOS);
				valoresTempoDistancia[1]=conversor;
				System.out.println(calcularVelocidadeMedia(valoresTempoDistancia));
			} catch (CiclistaException e) {
				System.out.println("VALOR INVÁLIDO");
			}
			System.out.println("Deseja Calcular outro Trajeto?");
			do{
				System.out.println("DIGITE S-SIM");
				System.out.println("DIGITE N-NÃO");
				controle=entrada.nextLine();
				if(controle.equalsIgnoreCase("n")){
					System.out.println("Velocidade média dos percusos:");
					System.out.println(soma/contador+"Km/h");
					System.out.println("Velocidade máxima:"+velMaxima+"Km/h");
					System.out.println("Velocidade mínima:"+velMinima+"Km/h");
				}
				if(!controle.equalsIgnoreCase("n")&&!controle.equalsIgnoreCase("s")){
					System.out.println("OPÇÃO INVÁLIDA");
				}
			}while(!controle.equalsIgnoreCase("s")&&!controle.equalsIgnoreCase("n"));
		}
	}	
	public static double calcularVelocidadeMedia(double[] ds) throws CiclistaException{
		if(ds[0]<=0){
			throw new CiclistaException ("VALOR INVÁLIDO");
		}
		if(ds[1]<=0){
			throw new CiclistaException("VALOR INVÁLIDO");
		}
		double media=ds[0]/ds[1];
		return media;
	}
	public static double[] interpretarValoresDeVelocidadeTempo(String ditanciaTempo) {
		String[] valores=ditanciaTempo.split(" ");
		double[] d={0,0};
		d[0]=Double.valueOf(valores[0]);
		d[1]=Double.valueOf(valores[1]);
		return d;
	}
	public static double converteTempo(double tempo, String horas, String segundos) {
		if(horas.equalsIgnoreCase(HORAS)){
			if(segundos.equalsIgnoreCase(MINUTOS)){
				tempo=tempo*60;
			}else if(segundos.equalsIgnoreCase(SEGUNDOS)){
				tempo=tempo*3600;
			}
			return tempo;
		}else if(horas.equalsIgnoreCase(MINUTOS)){
			if(segundos.equalsIgnoreCase(HORAS)){
				tempo=tempo/60;
			}else if(segundos.equalsIgnoreCase(SEGUNDOS)){
				tempo=tempo*60;
			}
			return tempo;
		}
		if(segundos.equalsIgnoreCase(HORAS)){
			tempo=tempo/3600;
		}else if(segundos.equalsIgnoreCase(MINUTOS)){
			tempo=tempo/60;
		}
		return tempo;
	}
	public static double converteDistancia(double distancia, String controleDistancia) {
		if(controleDistancia.equalsIgnoreCase(METROS)){
			distancia=distancia/1000;
		}else if(controleDistancia.equalsIgnoreCase(KILOMETROS)){
			distancia=distancia*1000;
		}
		return distancia;
	}
}
