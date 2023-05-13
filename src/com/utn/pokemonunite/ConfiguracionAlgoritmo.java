package com.utn.pokemonunite;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
public class ConfiguracionAlgoritmo {
    public void guardaAlgoritmo(int nroAlgoritmo) {
        String rutaArchivo = null;

        BufferedWriter escritor = null;
		try {
			escritor = new BufferedWriter(new FileWriter("datos.txt", false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			escritor.write(String.valueOf(nroAlgoritmo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public int leeAlgoritmo() {

        BufferedReader lector = null;
		try {
			lector = new BufferedReader(new FileReader("datos.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			return Integer.valueOf(lector.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			lector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 2;
    }
}