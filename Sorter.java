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
        System.out.println("Comparaciones final= "+comparaciones);
        System.out.println("Operaciones final= "+operaciones);
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
                if(comparacion%10000==0)
                    System.out.println("Comparaciones= "+comparacion);
            }
        }
        System.out.println("Final Comparaciones = "+comparacion);
        System.out.println("Final intercambios = "+switches);
        System.out.println("DONE");
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
        for(int i=arr.length/2;i>=0;i--)
            heapify(arr,i,arr.length-1,comparaciones,switches);
        for(int i=arr.length-1;i>0;i--){
            change(arr,0,i);switches++;
            heapify(arr,0,i-1,comparaciones,switches);
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
    private static void heapify(int[] tree,int ord,int stop,long comparaciones,long switches){
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
            heapify(tree,max,stop,comparaciones,switches);
        }
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
        System.out.println("Done");
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
    public static void mergeSort(int[] arr) {
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
            //System.out.println("Feel Good Inc.");
          mergeSort(l);
          mergeSort(r);
          merge(arr,l, r);
        }
    }
    
    private static void mergeSort(int[] arr,int init,int finit) {
        if(finit>init+1){
            int mid=(finit+init)/2;
            mergeSort(arr,init,mid);
            mergeSort(arr,mid,finit);
            for(int i=init;i<finit;i++){
                
            }
        
        
        
        
        }      
        
        
        /*
        int n = arr.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = arr[i];
        }
        //mergeSort(l, mid);
        //mergeSort(r, n - mid);
        //merge(arr, l, r, mid, n - mid);*/
    }
    
    private static void merge(int[] arr, int[] l, int[] r) {
        int i=0,j=0,k=0;
        
        while(i<l.length && j<r.length){
            if(l[i]<r[j]){
                arr[k]=l[i];
                i++;k++;
            }
            else{
                arr[k]=r[j];
                j++;k++;
            }
            //System.out.println(Arrays.toString(arr));
        }
        while(k<arr.length){
            if(i<l.length)
                arr[k]=l[i];
            if(j<r.length)
                arr[k]=r[j];
            k++;
        }
        
        /*int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] < r[j]) {
                arr[k++] = l[i++];
            }
            else {
                arr[k++] = r[j++];
            }
        }
        while (i < left) {
            arr[k++] = l[i++];
        }
        while (j < right) {
            arr[k++] = r[j++];
        }*/
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
        //time[1]=System.nanoTime();
        //System.out.println("N= "+String.valueOf((time[1]-time[0])/Math.pow(10, 9)));
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
            res[i]=ran.nextInt(10000);
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
     * ESTE SERA EL METODO PARA EL MENU 
     * Y SE PODRAN HACER MULTIMPLES ITERACIONES HASTA QUE EL USUARIO ESTE SATISFECHO
     */
    public static void menu(){
        boolean cont=true;
        while(cont){
         
            //Input del usuario

            Scanner scan=new Scanner(System.in);
            System.out.print("De qué tamaño será el arreglo?  ");
            int size=scan.nextInt();
            //scan.close();



            //fin del input

            //creacion del arreglo con el tamanio especificado y el metodo especificado
            int[] happy=BCE(size);
            int[] arreglo=RCE(size);
            int[] killme=WCE(size);

            System.out.println(check(arreglo));
            System.out.println("El arreglo es= "+Arrays.toString(arreglo));

            
        }
        
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
    
    public static void order66(){
        
    }
    
}
