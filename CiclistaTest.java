import org.junit.Test;
import static org.junit.Assert.*;

public class CiclistaTest{
	@Test
	public void interpretarValoresDeVelocidadeTempo()throws Exception{
		double[] distanciaTempo=Ciclista.interpretarValoresDeVelocidadeTempo("10.0 1.0");
		assertArrayEquals(new double[]{10,1},distanciaTempo,0);
	}
	@Test
	public void interpretarValoresDeTempoMaiorQueVelocidade()throws Exception{
		double[] distanciaTempo=Ciclista.interpretarValoresDeVelocidadeTempo("20.0 1.0");
		assertArrayEquals(new double[]{20,1},distanciaTempo,0);
	}
	@Test
	public void interpretarValoresDeTempoQuebrado()throws Exception{
		double[] distanciaTempo=Ciclista.interpretarValoresDeVelocidadeTempo("40 4.0");
		assertArrayEquals(new double[]{40,4},distanciaTempo,0);
	}
	@Test
	public void interpretarValoresDeDistanciaQuebrado()throws Exception{
		double[] distanciaTempo=Ciclista.interpretarValoresDeVelocidadeTempo("50 2.5");
		assertArrayEquals(new double[]{50,2.5},distanciaTempo,0);
	}
	@Test
	public void interpretarValoresDeDistanciaTempoIgual()throws Exception{
		double[] distanciaTempo=Ciclista.interpretarValoresDeVelocidadeTempo("10 10");
		assertArrayEquals(new double[]{10,10},distanciaTempo,0);
	}
	@Test
	public void interpretarValoresDeDistanciaTempoIguaisQuebrados()throws Exception{
		double[] distanciaTempo=Ciclista.interpretarValoresDeVelocidadeTempo("9.5 9.5");
		assertArrayEquals(new double[]{9.5,9.5},distanciaTempo,0);
	}
	@Test
	public void testaCalcularVelocidadeMedia()throws Exception{
		double v=Ciclista.calcularVelocidadeMedia(new double[]{10,1});
		assertEquals(v,10,0);
	}
	@Test(expected = CiclistaException.class)
	public void testaCalcularVelocidadeComDistanciaNegativa()throws Exception{
		double v=Ciclista.calcularVelocidadeMedia(new double[]{-2,10});
	}
	@Test(expected = CiclistaException.class)
	public void testaCalcularVelocidadeComTempoNegativa()throws Exception{
		double v=Ciclista.calcularVelocidadeMedia(new double[]{11,-8});
	}
	@Test(expected = CiclistaException.class)
	public void testaCalcularVelocidadeComTempoZero()throws Exception{
		double v=Ciclista.calcularVelocidadeMedia(new double[]{11,0});
	}
	@Test(expected = CiclistaException.class)
	public void testaCalcularVelocidadeComDistanciaZero()throws Exception{
		double v=Ciclista.calcularVelocidadeMedia(new double[]{0,9});
	}
	@Test(expected = CiclistaException.class)
	public void testaCalcularVelocidadeComDistanciaTempoZero()throws Exception{
		double v=Ciclista.calcularVelocidadeMedia(new double[]{0,0});
	}
	@Test
	public void testaConversaoHoraParaSegundo() throws Exception{
		double converte=Ciclista.converteTempo(1.0,Ciclista.HORAS,Ciclista.SEGUNDOS);
		assertEquals(converte,3600,0);
	}
	@Test
	public void testaConversaoMinutosParaSegundo() throws Exception{
		double converte=Ciclista.converteTempo(1.0,Ciclista.MINUTOS,Ciclista.SEGUNDOS);
		assertEquals(converte,60,0);
	}
	@Test
	public void testaConversaoSegundo() throws Exception{
		double converte=Ciclista.converteTempo(1.0,Ciclista.SEGUNDOS,Ciclista.SEGUNDOS);
		assertEquals(converte,1,0);
	}
	@Test
	public void testaConversaoSegundoParaHora() throws Exception{
		double converte=Ciclista.converteTempo(3600.0,Ciclista.SEGUNDOS,Ciclista.HORAS);
		assertEquals(converte,1,0);
	}
	@Test
	public void testaConversaoMinutoParaHora() throws Exception{
		double converte=Ciclista.converteTempo(60.0,Ciclista.MINUTOS,Ciclista.HORAS);
		assertEquals(converte,1,0);
	}
	@Test
	public void testaConversaoHora() throws Exception{
		double converte=Ciclista.converteTempo(1.0,Ciclista.HORAS,Ciclista.HORAS);
		assertEquals(converte,1,0);
	}
	@Test
	public void testaConversaoSegundoParaMinuto() throws Exception{
		double converte=Ciclista.converteTempo(60.0,Ciclista.SEGUNDOS,Ciclista.MINUTOS);
		assertEquals(converte,1.0,0);
	}
	@Test
	public void testaConversaoHoraParaMinuto() throws Exception{
		double converte=Ciclista.converteTempo(1.0,Ciclista.HORAS,Ciclista.MINUTOS);
		assertEquals(converte,60.0,0);
	}
	@Test
	public void testaConversaoMinuto() throws Exception{
		double converte=Ciclista.converteTempo(1.0,Ciclista.MINUTOS,Ciclista.MINUTOS);
		assertEquals(converte,1.0,0);
	}
	@Test
	public void testaConversaoKilometroParaMetro() throws Exception{
		double converte=Ciclista.converteDistancia(1.0,Ciclista.KILOMETROS);
		assertEquals(converte,1000.0,0);
	}
	@Test
	public void testaConversaoMetroParaKilometro() throws Exception{
		double converte=Ciclista.converteDistancia(1000.0,Ciclista.METROS);
		assertEquals(converte,1.0,0);
	}
}

