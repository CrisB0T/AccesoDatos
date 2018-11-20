package P04_FicherosBytes;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AD_3_1_ListaReproduccionMain {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int numero=0;
		Scanner teclado = new Scanner (System.in);
		do {
			System.out.println("_____________________________________ ");
			System.out.println("|       LISTA DE REPRODUCCIÓN        |");
			System.out.println("|------------------------------------|");	
			System.out.println("|1.-Generar fichero                  |");
			System.out.println("|2.-Insertar datos                   |");
			System.out.println("|3.-Consultar datos de una canción   |");
			System.out.println("|4.-Modificar año de la canción      |");
			System.out.println("|5.-Borrar información de una canción|");	
			System.out.println("|6.-Mostrar canciones borradas       |");
			System.out.println("|7.-Mostrar todas las canciones      |");
			System.out.println("|8.-Salir                            |");
			System.out.println("|____________________________________|");	
		
			numero = teclado.nextInt();
			File f = new File("Unidad_01_Ficheros\\P04_FicherosBytes\\Ficheros\\listaReproduccion.dat");
			RandomAccessFile raf = new RandomAccessFile (f, "rw");
			StringBuffer sb = null;
			
			AD_3_0_ListaReproduccion l = new AD_3_0_ListaReproduccion();		
			
			int id,anio,anioAnterior;
			String titulo, artista, duracion;
			boolean cancion_espaniola;
			char[] titulos = new char [10], artistas = new char [10], duraciones = new char [10];
			char aux;
			long posicion=0;
			
			switch (numero) {
			
			case 1: 				
				f.createNewFile();
				break;
				
			case 2: 				
				do {					
					System.out.println("Introduce el ID (El ID tiene que seer mayor que 0)");			
					id=teclado.nextInt();
				}while(id<=0);
				
				if(!l.existeID(id)) {
					System.out.println("Introduce el año");			
					anio=teclado.nextInt();
					teclado.nextLine();
					System.out.println("Introduce el titulo");			
					titulo=teclado.nextLine();
					System.out.println("Introduce el artista");			
					artista=teclado.nextLine();
					System.out.println("Introduce la duracion");			
					duracion=teclado.nextLine();
					System.out.println("Introduce si es una canción española(true) o extranjera(false)");			
					cancion_espaniola=teclado.nextBoolean();
					
					l = new AD_3_0_ListaReproduccion(id,anio,titulo,artista,duracion,cancion_espaniola);
						
					posicion = raf.length();
					raf.seek(posicion);
					
					raf.writeInt(id);
					raf.writeInt(anio);					
					
					sb= new StringBuffer(titulo);
					sb.setLength(10);
					raf.writeChars(sb.toString());
					sb= new StringBuffer(artista);
					sb.setLength(10);
					raf.writeChars(sb.toString());
					sb= new StringBuffer(duracion);
					sb.setLength(10);
					raf.writeChars(sb.toString());
					
					raf.writeBoolean(cancion_espaniola);					
				}else 
					System.out.println("Error, ya existe una canción con ese ID");						
				break;
				
			case 3: 
				do {					
					System.out.println("Introduce el ID de la canción a consultar (El ID tiene que seer mayor que 0)");
					id=teclado.nextInt();
				}while(id<=0);
							
				if(l.existeID(id)) {
					posicion=(id-1)*69;
					raf.seek(posicion);
					id=raf.readInt();				
					anio=raf.readInt();	
					
					for(int i=0;i<titulos.length;i++) {
						aux=raf.readChar();
						titulos[i]=aux;
					}					
					titulo = new String(titulos);
					
					for(int i=0;i<artistas.length;i++) {
						aux=raf.readChar();
						artistas[i]=aux;
					}					
					artista = new String(artistas);
					
					for(int i=0;i<duraciones.length;i++) {
						aux=raf.readChar();
						duraciones[i]=aux;
					}					
					duracion = new String(duraciones);
					
					cancion_espaniola = raf.readBoolean();
					
					System.out.println("Id: "+id+" Año: "+anio+" Titulo: "+titulo+" Artista: "+artista+" Duración: "+duracion+" ¿Canción española?: "+cancion_espaniola);
				}else
					System.out.println("No existe ninguna canción con ese ID");				
				break;
				
			case 4: 
				do {					
					System.out.println("Introduce el ID de la canción para modificar su año (El ID tiene que seer mayor que 0)");
					id=teclado.nextInt();
				}while(id<=0);
				
				if(l.existeID(id)) {
					posicion=(id-1)*69+4;
					raf.seek(posicion);										
					anioAnterior=raf.readInt();							
					
					System.out.println("Introduce el nuevo año");					
					anio=teclado.nextInt();		
					
					raf.seek(posicion);
					
					raf.writeInt(anio);	
					
					for(int i=0;i<titulos.length;i++) {
						aux=raf.readChar();
						titulos[i]=aux;
					}	
					titulo = new String(titulos);
					
					System.out.println("Id: "+id+" Año aterior: "+anioAnterior+" Año nuevo: "+anio+" Titulo: "+titulo);
					
				}else
					System.out.println("No existe ninguna canción con ese ID");
				break;
				
			case 5: 
				do {					
					System.out.println("Introduce el ID de la canción para borrarla (El ID tiene que seer mayor que 0)");
					id=teclado.nextInt();
				}while(id<=0);
			
				if(l.existeID(id)) {
					posicion=(id-1)*69;										
					
					raf.seek(posicion);
					
					raf.writeInt(-1);	
					System.out.println("Se ha borrado la canción");
				}else
					System.out.println("No existe ninguna canción con ese ID");
				break;
				
			case 6: 
				try {
					posicion=0;
					System.out.println("CANCIONES BORRADAS:");
					do {						
						raf.seek(posicion);
						id=raf.readInt();	
						if(id==-1) {
							
							anio=raf.readInt();	
							
							for(int i=0;i<titulos.length;i++) {
								aux=raf.readChar();
								titulos[i]=aux;
							}					
							titulo = new String(titulos);
							
							for(int i=0;i<artistas.length;i++) {
								aux=raf.readChar();
								artistas[i]=aux;
							}					
							artista = new String(artistas);
							
							for(int i=0;i<duraciones.length;i++) {
								aux=raf.readChar();
								duraciones[i]=aux;
							}					
							duracion = new String(duraciones);
							
							cancion_espaniola = raf.readBoolean();
							
							System.out.println("Id: "+id+" Año: "+anio+" Titulo: "+titulo+" Artista: "+artista+" Duración: "+duracion+" ¿Canción española?: "+cancion_espaniola);
						}					
						posicion += 69;
						/*69 porque ocupan todos los elementos 69B
						2 int = 8B
						3 String = 2B * 10 * 3= 60B
						1 boolean = 1B
						Total = 69B*/	
						
					}while(raf.getFilePointer()!=raf.length());
					/*.getFilePointer nos dice hacia donde est� apuntando el puntero
					.length dice el total de bytes que ocupa la informacion escrita en el fichero*/
					
				}catch (EOFException e) {			
					System.out.println("No hay ninguna canción borrada");
				}	
				break;
			case 7: 
				try {
					posicion=0;
					do {						
						raf.seek(posicion);//Nos situamos en un lugar determinado del fichero para empezar a leer desde all�
						id=raf.readInt();	
						if(id!=-1) {
							anio=raf.readInt();	
							
							for(int i=0;i<titulos.length;i++) {
								aux=raf.readChar();
								titulos[i]=aux;
							}					
							titulo = new String(titulos);
							
							for(int i=0;i<artistas.length;i++) {
								aux=raf.readChar();
								artistas[i]=aux;
							}					
							artista = new String(artistas);
							
							for(int i=0;i<duraciones.length;i++) {
								aux=raf.readChar();
								duraciones[i]=aux;
							}					
							duracion = new String(duraciones);
							
							cancion_espaniola = raf.readBoolean();
							
							System.out.println("Id: "+id+" Año: "+anio+" Titulo: "+titulo+" Artista: "+artista+" Duración: "+duracion+" ¿Canción española?: "+cancion_espaniola);
						}					
						posicion += 69;
						/*69 porque ocupan todos los elementos 69B
						2 int = 8B
						3 String = 2B * 10 * 3= 60B
						1 boolean = 1B
						Total = 69B*/	
						
					}while(raf.getFilePointer()!=raf.length());
					/*.getFilePointer nos dice hacia donde est� apuntando el puntero
					.length dice el total de bytes que ocupa la informacion escrita en el fichero*/
					
				}catch (EOFException e) {			
					System.out.println("No hay ninguna canción en la lista");
				}	
				break;
				
			case 8:
				raf.close();
				System.out.println("Has salido del programa");
				System.out.println("                 -------------------------------");
				System.out.println("                 |            Autor            |");
				System.out.println("                 |                             |");
				System.out.println("                 |         Jorge Chueca        |");
				System.out.println("                 |                             |");
				System.out.println("                 -------------------------------");break;
				
			default:
				System.out.println("ERROR, debe introducir un número entre el 1 y el 8");
			}
			
		}while(numero!=8);		
		teclado.close();
	}
}