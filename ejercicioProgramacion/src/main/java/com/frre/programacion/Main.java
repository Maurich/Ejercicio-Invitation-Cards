import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	
		static final int MAX = 1000000;  //maximo numero de vértices
		static final int INF = 1<<30;  //definimos un valor grande que represente la distancia infinita inicial, basta conque sea superior al maximo valor del peso en alguna de las aristas

		
		
		static Scanner sc = new Scanner( System.in );	   //para lectura de datos
		static List< List< Node > > ady = new ArrayList< List< Node > >(); //lista de adyacencia
		static int distancia[ ] = new int[ MAX ];          //distancia[ u ] distancia de vértice inicial a vértice con ID = u
		static boolean visitado[ ] = new boolean[ MAX ];   //para vértices visitados
		static PriorityQueue< Node > Q = new PriorityQueue<Node>(); //priority queue propia de Java, usamos el comparador definido para que el de menor valor este en el tope
		static int V;                                      //numero de vertices
		static int previo[] = new int[ MAX ];              //para la impresion de caminos
		
		//función de inicialización
		static void init(){
		    for( int i = 0 ; i <= V ; ++i ){
		        distancia[ i ] = INF;  //inicializamos todas las distancias con valor infinito
		        visitado[ i ] = false; //inicializamos todos los vértices como no visitados
		        previo[ i ] = -1;      //inicializamos el previo del vertice i con -1
		    }
		}

		//Paso de relajacion
		static void relajacion( int actual , int adyacente , int peso ){
		    //Si la distancia del origen al vertice actual + peso de su arista es menor a la distancia del origen al vertice adyacente
		    if( distancia[ actual ] + peso < distancia[ adyacente ] ){
		        distancia[ adyacente ] = distancia[ actual ] + peso;  //relajamos el vertice actualizando la distancia
		        previo[ adyacente ] = actual;                         //a su vez actualizamos el vertice previo
		        Q.add( new Node( adyacente , distancia[ adyacente ] ) ); //agregamos adyacente a la cola de prioridad
		    }
		}
		
		public static int dijkstra( int inicial, int destino ){
		    init(); //inicializamos nuestros arreglos
		    Q.add( new Node( inicial , 0 ) ); //Insertamos el vértice inicial en la Cola de Prioridad
		    distancia[ inicial ] = 0;      //Este paso es importante, inicializamos la distancia del inicial como 0
		    int actual , adyacente , peso;
		    while( !Q.isEmpty() ){                   //Mientras cola no este vacia
		        actual = Q.element().first;            //Obtengo de la cola el nodo con menor peso, en un comienzo será el inicial
		        Q.remove();                           //Sacamos el elemento de la cola
		        if( visitado[ actual ] ) continue; //Si el vértice actual ya fue visitado entonces sigo sacando elementos de la cola
		        visitado[ actual ] = true;         //Marco como visitado el vértice actual

		        for( int i = 0 ; i < ady.get( actual ).size() ; ++i ){ //reviso sus adyacentes del vertice actual
		            adyacente = ady.get( actual ).get( i ).first;   //id del vertice adyacente
		            peso = ady.get( actual ).get( i ).second;        //peso de la arista que une actual con adyacente ( actual , adyacente )
		            if( !visitado[ adyacente ] ){        //si el vertice adyacente no fue visitado
		                relajacion( actual , adyacente , peso ); //realizamos el paso de relajacion
		            }
		        }
		    }

		    int output=0;
		    	output=output+distancia[destino]; 	
		    	Q.clear();
		    return output;
		}
		public static void entradadDatos(){
			int E=0 , origen=0, destino=0 , peso=0;
			
			V = sc.nextInt();
			E = sc.nextInt();
			for( int i = 0 ; i <= V ; ++i ) ady.add(new ArrayList<Node>()) ; //inicializamos lista de adyacencia
			for( int i = 0 ; i < E ; ++i ){
				origen = sc.nextInt(); destino = sc.nextInt(); peso = sc.nextInt();
				ady.get( origen ).add( new Node( destino , peso ) );    //grafo diridigo
				//ady.get( destino ).add( new Node( destino , peso ) ); //no dirigido
			}
	
		}
		public static void clea(){
			ady.removeAll(ady);
		
		}
		
		public static void main(String[] args) {
			
			int sumatoria=0;
			int cont=sc.nextInt();
			for (int j = 0; j <cont; j++) {

			entradadDatos();
			
			for (int k = 1; k <=V; k++) {

		int a=dijkstra( 1, k );
		int b=dijkstra(k, 1);
		     sumatoria = sumatoria + a+b;

		    		 
			}System.out.println(sumatoria);
			sumatoria=0;
			clea();
			}
		}
	}

