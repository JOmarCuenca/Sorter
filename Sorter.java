/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author omar
 */
public class Sorter {
    
    /**
     * Metodo selectionSort que tiene un tiempo de ordenamiento de O(n^2)
     * @param arr Ordena el arreglo de int[] que entra como paramentro
     * @return Regresa un arreglo de long que contiene el numero de operaciones y comparaciones
     */
    public static long[] selectionSort(int[] arr){
        long operaciones=0;
        long comparaciones=0;
        for(int x=0;x<arr.length;x++){
            comparaciones++;
            int pos=x;
            for(int i=x+1;i<arr.length;i++){
                comparaciones++;
                int low=arr[pos];
                if(low>arr[i])
                    pos=i;
            }
            operaciones++;
            int temp=arr[x];
            arr[x]=arr[pos];
            arr[pos]=temp;
        }
        long[] res = {comparaciones,operaciones};
        return res;
    }
    
    /**
     * Bubblesort que ordena un arreglo en O(n^2)
     * @param arr 
     * @return Regresa un arreglo de long que contiene el numero de operaciones y comparaciones
     */
    public static long[] bubbleSort(int[] arr){
        long comparacion=0;
        long switches=0;
        for(int x=0;x<arr.length;x++){
            for(int y=0;y<arr.length-x-1;y++){
                comparacion++;
                if(arr[y]>arr[y+1]){
                    switches++;
                    int temp=arr[y];
                    arr[y]=arr[y+1];
                    arr[y+1]=temp;
                }
            }
        }
        long[] res = {comparacion,switches};
        return res;
    }
    
    /**
     * Metodo insertionSort con un procedimiento de O(n^2)
     * @param arr arreglo en int[] que es ordenado
     * @return Regresa un arreglo de long que contiene el numero de operaciones y comparaciones
     */
    public static long[] insertionSort(int[] arr){
        long comparaciones=0,operaciones=0;
        for(int i=1;i<arr.length;i++){
            int k=arr[i];
            int j=i-1;
            while(j>=0 && arr[j]>k){
                comparaciones++;
                operaciones++;
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=k;
        }
        long[] res = {comparaciones,operaciones};
        return res;
    }
    
    /**
     * heapSort con complejidad O(n*lg n)
     * @param arr Arreglo a ordenar
     * @return Regresa un arreglo de long que contiene el numero de operaciones y comparaciones
     */
    public static long[] heapSort(int[] arr){
        long comparaciones=0,switches=0;
        long[] thrash;
        for(int i=arr.length/2;i>=0;i--){
            thrash=heapify(arr,i,arr.length-1);
            comparaciones+=thrash[0];
            switches+=thrash[1];
        }
        
        for(int i=arr.length-1;i>0;i--){
            change(arr,0,i);switches++;
            thrash=heapify(arr,0,i-1);
            comparaciones+=thrash[0];
            switches+=thrash[1];
        }
        long[] res = {comparaciones,switches};
        return res;
    }
    
    /**
     * Metodo que reordena un arbol roto usado por Sorter heapSort
     * @param tree Arbol binario a ordenar
     * @param ord Limite sobre se encuentra la parte ordenada del arreglo
     * @param comparaciones Numero de comparaciones que se llevan 
     * @param switches Numero de switches que se llevan
     */
    private static long[] heapify(int[] tree,int ord,int stop){
        long comparaciones=0,switches=0;
        long[] thrash;
        int hijoi=2*ord,hijod=hijoi+1;
        int max=ord;
        
        if(hijoi<=stop && tree[hijoi]>tree[ord])
            max=hijoi;
        comparaciones++;
        if(hijod<=stop && tree[hijod]>tree[max])
            max=hijod;
        comparaciones++;
        if(max!=ord){
            change(tree,ord,max);switches++;
            thrash=heapify(tree,max,stop);
            comparaciones+=thrash[0];
            switches+=thrash[1];
        }
        long[] res={comparaciones,switches};
        return res;
    }
    
    /**
     * Metodo quickSort con complejidad O(n*lg n)
     * Ironicamente funciona bastante bien hasta con 10,000,000 elementos
     * @param A Arreglo de integers a ser ordenados 
     * @return Regresa un arreglo de long que contiene el numero de operaciones y comparaciones
     */
    public static long[] quickSort(int[] A){
        long comparaciones=0,switches=0;
        if(A.length>2){
            int w=0, pivot=A.length-1;
            for(int c=0;c<pivot;c++){
                comparaciones++;
                if(A[c]<A[pivot]){
                    change(A,w,c);switches++;
                    w++;
                }
            }
            change(A,w,pivot);switches++;
            quickSort(A,0,w-1,comparaciones,switches);
            quickSort(A,w+1,A.length-1,comparaciones,switches);
        }
        long[] res = {comparaciones,switches};
        return res;
        
        
    }
    
    /**
     * Metodo quickSort que funciona con parametros para las llamadas recursivas
     * @param A Arreglo a ser ordenado
     * @param walli parametro cobre el cual el arreglo comienza a ordenar
     * @param pivot parametro sobre el cual termina de arreglar y es numero sobre el cual se centra el ordenamiento
     */
    private static void quickSort(int[] A,int walli,int pivot,long comp,long sw){
        comp++;
        if(pivot>walli){
            int wall=walli;
            for(int c=wall;c<pivot;c++){
                comp++;
                if(A[c]<A[pivot]){
                    change(A,c,wall);sw++;
                    wall++;
                }
            }
            change(A,wall,pivot);sw++;
            quickSort(A,walli,wall-1,comp,sw);
            quickSort(A,wall+1,pivot,comp,sw);
        }
        
    }
    
    
    /**
     * Metodo mergeSort con complejidad O(n*lg n)
     * @param arr Arreglo sobre el cual comienza a ordenar
     * @return 
     */
    public static long[] mergeSort(int[] arr) {
        long[] thrash={0,0};
        int n = arr.length;
        if (n > 1) {
          int mid = n / 2;
          int[] l = new int[mid];
          int[] r = new int[n - mid];
          for (int i = 0; i < mid; i++) {
              l[i] = arr[i];
          }
          for (int i = mid; i < n; i++) {
              r[i - mid] = arr[i];
          }
          long[] thrash2;
          thrash2=mergeSort(l);
          thrash[0]+=thrash2[0];
          thrash[1]+=thrash2[1];
          thrash2=mergeSort(r);
          thrash[0]+=thrash2[0];
          thrash[1]+=thrash2[1];
          merge(arr,l, r,thrash);
        }
        return thrash;
    }
    
    private static void merge(int[] arr, int[] l, int[] r,long[] thrash) {
        int i=0,j=0,k=0;
        
        while(i<l.length && j<r.length){
            thrash[0]++;
            if(l[i]<r[j]){
                arr[k]=l[i];
                i++;k++;
            }
            else{
                thrash[1]++;
                arr[k]=r[j];
                j++;k++;
            }
        }
        while(k<arr.length){
            if(i<l.length){
                arr[k]=l[i];
                thrash[1]++;
            }
            if(j<r.length)
                arr[k]=r[j];
            k++;
        }
    }
    
    /**
     * Metodo para determinar si un arreglo ya esta ordenado
     * Lo vamos a usar para determinar si el algoritmo de 
     * ordenamiento se ejecuto de manera correcta
     * @param arr arreglo de integers puede estar ordenado o desordenado
     * @return Regresara un true si el arreglo ya esta ordenado de manera correcta, false en caso
     * contrario
     */
    public static boolean check(int[] arr){
        //long[] time=new long[2];
        //time[0]=System.nanoTime();
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]>arr[i+1])
                return false;
        }
        return true;
    }
    
    /**
     * Metodo que crea el peor escenario posible para arreglar un arreglo
     * que este completamente al reves.
     * @param num int que representa el tamanio del arreglo
     * @return arreglo al reves
     */
    public static int[] WCE(int num){
        int[] r=new int[num];
        for(int x=num;x>0;x--)
            r[num-x]=x;
        return r;
    }
    
    /**
     * Metodo que crea un arreglo con numeros aleatorios en orden aleatorio
     * @param num int que representa el tamanio del arreglo
     * @return regresa un arreglo que tiene numeros aleatorios de manera aleatoria
     */
    public static int[] RCE(int num){
        Random ran=new Random();
        int[] res=new int[num];
        for(int i=0;i<num;i++)
            res[i]=ran.nextInt(100000);
        return res;
    }
    
    /**
     * Metodo que crea el mejor escenario para ordenar
     * @param num int que representa el tamanio del arreglo
     * @return regresa un arreglo que ya esta ordenado
     */
    public static int[] BCE(int num){
        int[] res=new int[num];
        for(int i=0;i<num;i++)
            res[i]=i;
        return res;
    }
    
    /**
     * Metodo que realiza la llamada al algoritmo deseado por el usuario
     * Tambien mide el tiempo en segundos de lo que se tarda en ordenar el arreglo
     * @param arreglo el arreglo a ordenar
     * @param scan el scanner para recibir y no crear uno nuevo
     * @return el numero de comparaciones y cambios de ese algoritmo en ese orden
     */
    public static long[] test(int[] arreglo,Scanner scan){
        System.out.println("1.- SelectionSort");
        System.out.println("2.- BubbleSort");
        System.out.println("3.- InsertionSort");
        System.out.println("4.- HeapSort");
        System.out.println("5.- QuickSort");
        System.out.println("6.- MergeSort");
        int ans=scan.nextInt();
        long[] thrash={0,0};
        long init=System.nanoTime();
        switch(ans){
            case(1):
                thrash=selectionSort(arreglo);
                break;
            case(2):
                thrash=bubbleSort(arreglo);
                break;
            case(3):
                thrash=insertionSort(arreglo);
                break;
            case(4):
                thrash=heapSort(arreglo);
                break;
            case(5):
                thrash=quickSort(arreglo);
                break;
            case(6):
                thrash=mergeSort(arreglo);
                break;
            default:
                test(arreglo,scan);
        }
        long finit=System.nanoTime();
        double tiempo=(finit-init)/Math.pow(10, 9);
        System.out.println("El tiempo transcurrido en segundos es: "+String.valueOf(tiempo));
        return thrash;
    } 
    
    /**
     * Metodo que permite obtener datos introducidos por el usuario
     * @param scan Scanner que recibe los numero (para no generar otro scanner)
     * @return Regresa el arreglo del usuario
     */
    private static int[] intro(Scanner scan){
        System.out.print("Cuantos numero vas a introducir? ");
        int [] res;
        int quantity=scan.nextInt();
        if(quantity<1){
            System.out.println("Ya deja de jugar");
            res=intro(scan);
        }
        else{
            res=new int[quantity];
            for(int i=0;i<quantity;i++){
                System.out.print("Dame un numero: ");
                res[i]=scan.nextInt();
            }
        }
        return res;
    }
    
    /**
     * Metodo que va a manejar el generador de arreglos automatico
     * @param scan Scanner para no generar otro 
     * @return Regresa el arreglo que el usuario solicito
     */
    private static int[] generate(Scanner scan){
        int[] arreglo;
        System.out.print("De que tamanio sera el arreglo? ");
        int ans=scan.nextInt();
        if(ans<1){
            System.out.println("Ya deja de jugar");
            arreglo=generate(scan);
        }
        else{
            while(true){
                System.out.println("Respuestas en forma numerica por favor");
                System.out.print("Deseas usar Worst Case (WCE,1),Random Case (RCE,2), Best Case (BCE,3)? ");
                int type=scan.nextInt();
                if(type==1){
                    arreglo=WCE(ans);
                    break;
                }
                else if(type==2){
                    arreglo=RCE(ans);
                    break;
                }
                else if(type==3){
                    arreglo=BCE(ans);
                    break;
                }
            }
        }
        return arreglo;
    }
    
    /**
     * ESTE SERA EL METODO PARA EL MENU 
     * Y SE PODRAN HACER MULTIMPLES ITERACIONES HASTA QUE EL USUARIO ESTE SATISFECHO
     */
    public static void menu(){
        boolean cont=true,o66;
        Scanner scan=new Scanner(System.in);
        while(cont){
            o66=false;
            int[] arreglo={0};
            while(true){
                System.out.println("Te gustaria introducir tu los datos? (y/n) \nEn caso de que no los introduzcas, yo generare un array para trabajar"); 
                String ans=scan.next();
                if(ans.equalsIgnoreCase("y")){
                    arreglo=intro(scan);
                    break;
                }
                else if(ans.equalsIgnoreCase("n")){
                    arreglo = generate(scan);
                    break;
                }
                else if(ans.equalsIgnoreCase("66")){
                    o66=true;
                    order66();
                    break;
                }
                else
                    System.out.println("Lo siento no te entendi \n");
            }
            if(o66)
                break;
            
            int size=arreglo.length;

            boolean order=Sorter.check(arreglo);
            if(order)
                System.out.println("El arreglo esta ordenado");
            else
                System.out.println("El arreglo NO esta ordenado");
            if(100000000>size && size>1000){
                String arregloS=Arrays.toString(arreglo).substring(0, 1000);
                System.out.println(arregloS+"...");
            }
            else
                System.out.println(Arrays.toString(arreglo));

            if(size>=100000000)
                System.out.println("Common dude... trust me");
            
            
            long[] info;

            
            info=test(arreglo,scan);
            

            order=Sorter.check(arreglo);
            if(order)
                System.out.println("El arreglo esta ordenado");
            else
                System.out.println("El arreglo NO esta ordenado");
            if(100000000>size && size>1000){
                String arregloS=Arrays.toString(arreglo).substring(0, 1000);
                System.out.println(arregloS+"...");
            }
            else
                System.out.println(Arrays.toString(arreglo));
            if(size>=100000000)
                System.out.println("Common dude... trust me");
            
            System.out.println("El numero de comparaciones realizadas por el algoritmo fueron: "+info[0]);
            System.out.println("El numero de intercambios realizados por el algoritmo fueron: "+info[1]);
            
            while(true){
                System.out.print("Otra ronda? (y/n) "); 
                String ans=scan.next();
                if(ans.equalsIgnoreCase("y")){
                    break;
                }
                else if(ans.equalsIgnoreCase("n")){
                    cont=false;
                    break;
                }
                else
                    System.out.println("Lo siento no te entendi \n");
            }
            
        }
        
        scan.close();
        System.out.println("Thank you...");
        
    }
    
    /**
     * Modulo para poder realizar los cambios de posicion en un arreglo
     * @param A arreglo de integers que se esta manejando
     * @param a posicion del primer elemento a cambiar
     * @param b posicion del segundo elemento a cambiar
     */
    private static void change(int[] A,int a,int b){
        int temp=A[a];
        A[a]=A[b];
        A[b]=temp;
    }
    
    /**
     * Metodo para generar un reporte en un .txt que contiene un arreglo dosordenado 
     * y los datos de cada algoritmo de ordenamiento tratando de ordenarlo. 
     */
    public static void order66(){
        int[] arreglo=new int[100];
        int[] dummy;
        long[] info=new long[12];
        arreglo=RCE(arreglo.length);
        
        int ans=1,insert=0;
        
        while(ans<7){
            dummy=Arrays.copyOf(arreglo,arreglo.length);
            long[] thrash={0,0};
            switch(ans){
                case(1):
                    System.out.println("Select");
                    thrash=selectionSort(dummy);
                    break;
                case(2):
                    System.out.println("Bubble");
                    thrash=bubbleSort(dummy);
                    break;
                case(3):
                    System.out.println("Insert");
                    thrash=insertionSort(dummy);
                    break;
                case(4):
                    System.out.println("Heap");
                    thrash=heapSort(dummy);
                    break;
                case(5):
                    System.out.println("Quick");
                    thrash=quickSort(dummy);
                    break;
                case(6):
                    System.out.println("Merge");
                    thrash=mergeSort(dummy);
                    break;
            }
            System.out.println(Arrays.toString(thrash));
            System.out.println(insert);
            info[insert]=thrash[0];
            info[insert+1]=thrash[1];
            ans++;
            insert+=2;
        }
        String res=Arrays.toString(info);
        System.out.println(res);
    }
    
}
