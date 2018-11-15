/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

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
    public static long[] selectionSort(int[] arr,boolean show){
        long operaciones=0;
        long comparaciones=0;
        for(int x=0;x<arr.length;x++){
            int pos=x;
            for(int i=x+1;i<arr.length;i++){
                comparaciones++;
                int low=arr[pos];
                if(low>arr[i])
                    pos=i;
            }
            if(show)
                System.out.println(Arrays.toString(arr));
            operaciones++;
            int temp=arr[x];
            arr[x]=arr[pos];
            arr[pos]=temp;
        }
        long[] res = {comparaciones,operaciones};
        return res;
    }
    
    /**
     * Bubblesort que ordena un arreglo en Theta(n^2)
     * @param arr 
     * @return Regresa un arreglo de long que contiene el numero de operaciones y comparaciones
     */
    public static long[] bubbleSort(int[] arr,boolean show){
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
                if(show)
                    System.out.println(Arrays.toString(arr));
            }
            if(switches==0){
                    long[] res = {comparacion,switches};
                    return res;
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
    public static long[] insertionSort(int[] arr,boolean show){
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
            if(show)
                System.out.println(Arrays.toString(arr));
            arr[j+1]=k;
        }
        long[] res = {comparaciones,operaciones};
        return res;
    }
    
    /**
     * heapSort con complejidad Theta(n*lg n)
     * @param arr Arreglo a ordenar
     * @return Regresa un arreglo de long que contiene el numero de operaciones y comparaciones
     */
    public static long[] heapSort(int[] arr,boolean show){
        long comparaciones=0,switches=0;
        long[] thrash;
        for(int i=arr.length/2;i>=0;i--){
            thrash=heapify(arr,i,arr.length-1);
            comparaciones+=thrash[0];
            switches+=thrash[1];
        }
        if(show)
            System.out.println(Arrays.toString(arr));
        
        for(int i=arr.length-1;i>0;i--){
            change(arr,0,i);switches++;
            thrash=heapify(arr,0,i-1);
            if(show)
                System.out.println(Arrays.toString(arr));
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
     * sin embargo el algoritmo puede llegar a ser O(n^2) 
     * en los peores de los casos.
     * @param A Arreglo de integers a ser ordenados 
     * @return Regresa un arreglo de long que contiene el numero de operaciones y comparaciones
     */
    public static long[] quickSort(int[] A,boolean show){
        long comparaciones=0,switches=0;
        if(A.length>2){
            int w=0, pivot=A.length-1;
            for(int c=0;c<pivot;c++){
                comparaciones++;
                if(A[c]<A[pivot]){
                    change(A,w,c);switches++;
                    if(show)
                        System.out.println(Arrays.toString(A));
                    w++;
                }
            }
            change(A,w,pivot);switches++;
            if(show)
                System.out.println(Arrays.toString(A));
            quickSort(A,0,w-1,comparaciones,switches,show);
            quickSort(A,w+1,A.length-1,comparaciones,switches,show);
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
    private static void quickSort(int[] A,int walli,int pivot,long comp,long sw,boolean show){
        comp++;
        if(pivot>walli){
            int wall=walli;
            for(int c=wall;c<pivot;c++){
                comp++;
                if(A[c]<A[pivot]){
                    change(A,c,wall);sw++;
                    if(show)
                        System.out.println(Arrays.toString(A));
                    wall++;
                }
            }
            change(A,wall,pivot);sw++;
            if(show)
                System.out.println(Arrays.toString(A));
            quickSort(A,walli,wall-1,comp,sw,show);
            quickSort(A,wall+1,pivot,comp,sw,show);
        }
        
    }
    
    /**
     * Metodo mergeSort con complejidad O(n*lg n)
     * @param arr Arreglo sobre el cual comienza a ordenar
     * @return 
     */
    public static long[] mergeSort(int[] arr,boolean show) {
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
          thrash2=mergeSort(l,show);
          thrash[0]+=thrash2[0];
          thrash[1]+=thrash2[1];
          thrash2=mergeSort(r,show);
          thrash[0]+=thrash2[0];
          thrash[1]+=thrash2[1];
          merge(arr,l, r,thrash,show);
          if(show)
            System.out.println(Arrays.toString(arr));
        }
        return thrash;
    }
    
    /**
     * Metodo que utiliza mergeSort para poder unir dos pedazos del arreglo de forma ordenada
     * Este es el metodo que realmente ordena el arreglo
     * @param arr el arreglo a ordenar
     * @param l punto de inicio
     * @param r punto de terminado 
     * @param thrash el recolector de datos para que el arreglo lleve cuenta de las comparaciones y de los intercambios
     */
    private static void merge(int[] arr, int[] l, int[] r,long[] thrash,boolean show) {
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
            if(show)
                System.out.println(Arrays.toString(arr));
        }
        while(k<arr.length){
            if(i<l.length){
                arr[k]=l[i];
                thrash[1]++;
            }
            if(j<r.length)
                arr[k]=r[j];
            if(show)
                System.out.println(Arrays.toString(arr));
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
    public static long[] test(int[] arreglo,Scanner scan,boolean show){
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
                thrash=selectionSort(arreglo,show);
                break;
            case(2):
                thrash=bubbleSort(arreglo,show);
                break;
            case(3):
                thrash=insertionSort(arreglo,show);
                break;
            case(4):
                thrash=heapSort(arreglo,show);
                break;
            case(5):
                thrash=quickSort(arreglo,show);
                break;
            case(6):
                thrash=mergeSort(arreglo,show);
                break;
            default:
                test(arreglo,scan,show);
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
        boolean cont=true,o66,show=false;
        Scanner scan=new Scanner(System.in);
        while(cont){
            o66=false;
            int[] arreglo={0};
            while(true){
                System.out.println("Te gustaria introducir tu los datos? (y/n) \nEn caso de que no los introduzcas, yo generare un array para trabajar"); 
                String ans=scan.next();
                if(ans.equalsIgnoreCase("y")){
                    arreglo=intro(scan);
                    show=true;
                    break;
                }
                else if(ans.equalsIgnoreCase("n")){
                    arreglo = generate(scan);
                    show=false;
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

            
            info=test(arreglo,scan,show);
            

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
        int[] arreglo=new int[100000];
        int num=arreglo.length;
        int[] dummy;
        long[] info=new long[12];
        double[] times=new double[6],oper=new double[6];
        arreglo=RCE(arreglo.length);
        
        
        
        int ans=1,insert=0;
        
        while(ans<7){
            long init=System.nanoTime();
            double opera=0;
            dummy=Arrays.copyOf(arreglo,arreglo.length);
            long[] thrash={0,0};
            switch(ans){
                case(1):
                    System.out.println("Select");
                    thrash=selectionSort(dummy,false);
                    opera=Math.pow(num, 2);
                    break;
                case(2):
                    System.out.println("Bubble");
                    thrash=bubbleSort(dummy,false);
                    opera=Math.pow(num, 2);
                    break;
                case(3):
                    System.out.println("Insert");
                    thrash=insertionSort(dummy,false);
                    opera=Math.pow(num, 2);
                    break;
                case(4):
                    System.out.println("Heap");
                    thrash=heapSort(dummy,false);
                    opera=num*Math.log10(num)/Math.log10(2);
                    break;
                case(5):
                    System.out.println("Quick");
                    thrash=quickSort(dummy,false);
                    opera=num*Math.log10(num)/Math.log10(2);
                    break;
                case(6):
                    System.out.println("Merge");
                    thrash=mergeSort(dummy,false);
                    opera=num*Math.log10(num)/Math.log10(2);
                    break;
            }
            long finit=System.nanoTime();
            System.out.println(Arrays.toString(thrash));
            System.out.println(check(dummy));
            info[insert]=thrash[0];
            info[insert+1]=thrash[1];
            times[ans-1]=(finit-init)/Math.pow(10, 9);
            oper[ans-1]=opera;
            ans++;
            insert+=2;
        }
        dummy=Arrays.copyOf(arreglo,arreglo.length);
        mergeSort(dummy,false);
        String res=Arrays.toString(info);
        System.out.println(res);
        res=Arrays.toString(times);
        System.out.println(res);
        try{
            int count=1,infoc=0;
            BufferedWriter write=new BufferedWriter(new FileWriter("test.txt"));
            int array=0;
            write.write("El arreglo a ordenar es:\n");
            res=Arrays.toString(arreglo);
            while(array<arreglo.length-200){
                write.write(res.substring(array, array+200)+"\n");
                array+=200;
            }
            write.write(res.substring(array, res.length()));
            write.write("\ny tiene un total de "+arreglo.length+" elementos\n\n");
            while(count<7){
                String sort="",complex="";
                switch(count){
                    case(1):
                        sort="selectionSort";
                        complex="O(n^2)";
                        break;
                    case(2):
                        sort="bubbleSort";
                        complex="Theta(n^2)";
                        break;
                    case(3):
                        sort="insertionSort";
                        complex="O(n^2)";
                        break;
                    case(4):
                        sort="heapSort";
                        complex="Theta(n*lg(n))";
                        break;
                    case(5):
                        sort="quickSort";
                        complex="O(n*lg(n))";
                        break;
                    case(6):
                        sort="mergeSort";
                        complex="O(n*lg(n))";
                        break;
                }
                write.write("El metodo "+sort+" ordeno el arreglo con los siguientes datos:\n");
                write.write("El algoritmo tiene una complejidad de "+complex+", por lo tanto \nEl "
                        + "numero de operaciones que deberia realizar es "+oper[count-1]+"\n");
                write.write("Las operaciones reales que realizo son: \n");
                write.write("Comparaciones= "+info[infoc]+"\tIntercambios= "+info[infoc+1]+"\n");
                write.write("Y lo logro en un tiempo de "+times[count-1]+" segundos.");
                write.write("\n\n");
                infoc+=2;count++;
            }
            array=0;
            write.write("El arreglo ordenado es:\n");
            res=Arrays.toString(dummy);
            while(array<dummy.length-200){
                write.write(res.substring(array, array+200)+"\n");
                array+=200;
            }
            write.write(res.substring(array, dummy.length));
            write.close();
        }
        catch(Exception e){
            System.out.println("Ocurrio un error y los Jedi han sobrevivido");
        }
    }
    
}
