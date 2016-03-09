package br.com.desp.testes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;


public class Teste {
	
	public static void main(String[] args) {
		JFrame janela = new JFrame();
		
		janela.setSize(500, 500);
		
		janela.setTitle("Calculadora");
		
		janela.setCursor(5);
		
		Font font = new Font("arial", 2, 15);
		
		janela.setFont(font);
		
		
		
		janela.setVisible(true);
		
		
	}

}
