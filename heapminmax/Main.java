/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.heapminmax;
import java.util.Arrays;

/**
 *
 * @author Daniel Carlos S. de Jesus
 */
public class Main {

    public static void main(String[] args) {
        HeapMinMax heap = new HeapMinMax();
      
        int[] teste = {4, 70, 38, 20, 8, 35, 19, 30, 28, 18,  25, 36, 37, 26, 22};
        
        // Inserção
        System.out.println("Prioridades para inserção: "+Arrays.toString(teste)+"\n");
        for (int idx=0; idx<teste.length; idx++) {
            System.out.println("-- Inserindo prioridade "+teste[idx]+" --");
            heap.inserir(teste[idx]);
            System.out.print("Heap: ");
            heap.printHeap();
            System.out.println("Min: "+heap.retornaPrioridadeMinima());
            System.out.println("Max: "+heap.retornaPrioridadeMaxima());
            System.out.println();
        }

        // Remoção Prioridade Máxima
        for (int i=0; i<=1; i++){
            System.out.println("-- Removendo Prioridade Máxima--");
            System.out.println("Max: "+heap.retornaPrioridadeMaxima());
            heap.removePrioridadeMaxima();
            heap.printHeap();
            System.out.println();
        }

        // Remoção Prioridade Mínima
        for (int i=0; i<=1; i++){
            System.out.println("-- Removendo Prioridade Mínima--");
            System.out.println("Max: "+heap.retornaPrioridadeMinima());
            heap.removePrioridadeMinima();
            heap.printHeap();
            System.out.println();
        }
    }    
}
