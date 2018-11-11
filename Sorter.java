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
     */
    public static void selectionSort(int[] arr){
        int operaciones=0;
        for(int x=0;x<arr.length;x++){
            operaciones++;
            int pos=x;
            for(int i=x+1;i<arr.length;i++){
                operaciones++;
                int low=arr[pos];
                if(low>arr[i])
                    pos=i;
                if(operaciones%10000==0)
                    System.out.println("Operaciones = "+operaciones);
            }
            int temp=arr[x];
            arr[x]=arr[pos];
            arr[pos]=temp;
        }
        System.out.println("Operaciones final= "+operaciones);
    }
    
    /**
     * Bubblesort que ordena un arreglo en O(n^2)
     * @param arr 
     */
    public static void bubbleSort(int[] arr){
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
    }
    
    /**
     * Metodo insertionSort con un procedimiento de O(n^2)
     * @param arr arreglo en int[] que es ordenado
     */
    public static void insertionSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int k=arr[i];
            int j=i-1;
            while(j>=0 && arr[j]>k){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=k;
        }
    }
    
    /**
     * heapSort con complejidad O(n*lg n)
     * @param arr Arreglo a ordenar
     */
    public static void heapSort(int[] arr){
        
    }
    
    /**
     * Metodo que reordena un arbol roto usado por Sorter heapSort
     * @param tree Arbol binario a ordenar
     * @param ord Limite sobre se encuentra la parte ordenada del arreglo
     */
    public static void heapify(int[] tree,int ord){
        
    }
    
    /**
     * Metodo quickSort con complejidad O(n*lg n)
     * Ironicamente funciona bastante bien hasta con 10,000,000 elementos
     * @param A Arreglo de integers a ser ordenados 
     */
    public static void quickSort(int[] A){
        if(A.length>2){
            int w=0, pivot=A.length-1;
            for(int c=0;c<pivot;c++){
                if(A[c]<A[pivot]){
                    change(A,w,c);
                    w++;
                }
            }
            change(A,w,pivot);
            /*int r;
            r=A.length-1;
            int m=r/2;*/
            quickSort(A,0,w-1);
            quickSort(A,w+1,A.length-1);
        }
        System.out.println("Done");
        
        
    }
    
    /**
     * Metodo quickSort que funciona con parametros para las llamadas recursivas
     * @param A Arreglo a ser ordenado
     * @param walli parametro cobre el cual el arreglo comienza a ordenar
     * @param pivot parametro sobre el cual termina de arreglar y es numero sobre el cual se centra el ordenamiento
     */
    public static void quickSort(int[] A,int walli,int pivot){
        if(pivot>walli){
            int wall=walli;
            for(int c=wall;c<pivot;c++){
                if(A[c]<A[pivot]){
                    change(A,c,wall);
                    wall++;
                }
            }
            change(A,wall,pivot);
            quickSort(A,walli,wall-1);
            quickSort(A,wall+1,pivot);
        }
        
    }
    
    /**
     * Metodo mergeSort con complejidad O(n*lg n)
     * @param arr Arreglo sobre el cual comienza a ordenar
     * @return 
     */
    public static int[] mergeSort(int[] arr){
        if(arr.length>2){
            int mid=arr.length/2;
            int[] A=new int[mid];
            int[] B=new int[arr.length-mid];
            A=mergeSort(A);
            B=mergeSort(B);
        }
        return arr;
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
    public static void change(int[] A,int a,int b){
        int temp=A[a];
        A[a]=A[b];
        A[b]=temp;
    }
    
}
