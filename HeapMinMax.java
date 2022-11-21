/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.heapminmax;

/**
 *
 * @author Daniel Carlos S. de Jesus
 */
public class HeapMinMax {
    
    private final int vetor[] = new int[20];
    private int posFinal;

    
    public int retornaPrioridadeMinima(){
        return vetor[1];
    }
   
    public int retornaPrioridadeMaxima() {
        if (posFinal == 1)
            return vetor[1];
        
        else if (posFinal > 1 && vetor[2] > vetor[3])
                return vetor[2];
        else
                return vetor[3];
    }
        
    public void inserir (int num) {
        posFinal++;
        vetor[posFinal] = num;
        
        // se não for o primeiro elemento, pode ser necessário subir
        if (posFinal > 1)
            subir(posFinal);
    }
    
    private void subir (int i) {
        // índice do possível pai
        int pai = i/2;  
        
        // o elemento i encontra-se em um nível mínimo ou máximo
        if (minimo(i))
        {
            // verifica se o elemento i é maior que o pai
            if (vetor[i] > vetor[pai]) {
                System.out.println("Precisa trocar com o pai");
                trocar(i, pai);
                subir_max(pai);
            }
            else 
                subir_min(i);
        }
        
        // o elemento i encontra-se em um nível máximo
        else 
        {
            // verifica se o elemento i é menor que o pai
            if (vetor[i] < vetor[pai]){
                System.out.println("Precisa trocar com o pai");
                trocar(i, pai);
                subir_min(pai);

            }
            else
                subir_max(i);
        }    
    }

    // subir a partir de um nível mínimo
    private void subir_min (int i) {
        //indice do possível avô
        int avo = i/4;  
        
        //verifica se o elemento é menor que o avô
        if (avo >= 1 && vetor[i] < vetor[avo]) {
            System.out.println("Precisa trocar com o avô");
            trocar(i, avo);
            subir_min(avo);

        }
    }
    
    // subir a partir de um nível máximo
    private void subir_max (int i) {
        //indice do possível avô
        int avo = i/4; 
        
        // verifica se o elemento é maior que o avô
        if (avo >= 1 && vetor[i] > vetor[avo]){
            System.out.println("Precisa trocar com o avô");
            trocar(i, avo);
            subir_max(avo);
        }
    }
    
    public void removePrioridadeMinima(){
        vetor[1] = vetor[posFinal];
        posFinal--;

        // se ainda tiver pelo menos dois elementos, tem que testar se precisa descer
        if (posFinal >= 2)
            descer(1);
    }    

    public void removePrioridadeMaxima(){
        // heap contem no máximo dois nós
        if (posFinal == 1 || posFinal == 2)
            posFinal--;
       
        else {
            // achar posicao de maior prioridade
            int maior;
            
            if (vetor[2] > vetor[3])
                maior = 2;
            else
                maior = 3;

            vetor[maior] = vetor[posFinal];
            posFinal--;
            descer(maior);
        }
    }

    private void descer(int i){
        if (minimo(i))
            // foi removido o de menor prioridade
            descer_min(i);
        
        else
            // foi removido o de maior prioridade
            descer_max(i);
    }
    
    private void descer_min(int i) {
        // verifica se i tem filhos
        if (2*i <= posFinal) {  
            
            // menor dos descendentes entre filhos e netos (se houver netos)
            int m = min_descendente(i);
            
            if (vetor[i] > vetor[m]) {
                System.out.println("Precisa trocar com "+vetor[m]);
                trocar(i, m);
                
                // m é um neto e não um filho, tem que continuar
                if (m >= 4*i) {
                    int p = m/2;   // p é o pai
                    if (vetor[m] > vetor[p])
                        trocar (p, m);
                    descer_min(m);
                }
            }
        }
    }
    
    private void descer_max(int i){
        // verifica se i tem filhos
        if (2*i <= posFinal) {
            
            // menor dos descendentes entre filhos e netos (se houver netos)
            int m = max_descendente(i);
            if (vetor[i] < vetor[m]) {
                System.out.println("Precisa trocar com "+vetor[m]);
                trocar(i, m);
                
                // m é um neto e não um filho, tem que continuar
                if (m >= 4*i) {
                    int p = m/2;   // p é o pai
                    if (vetor[m] < vetor[p])
                        trocar(p, m);
                    descer_max(m);
                }
            }
        }
    }
           
    // retorna indice do menor dos descendentes entre os filhos e netos do elemento i
    private int min_descendente(int i) {
        int m = 0; //indice do menor elemento
        
        if (2*i <= posFinal) {
            // índice do primeiro filho
            m = 2*i;
            
            // verifica se o segundo filho é menor do que o primeiro filho
            if (vetor[m+1] < vetor[m])
                m = m+1;
            
            // verifica se o menor está entre os netos
            for (int k=4*i; k<=4*i+3 && k <=posFinal;  k++)
                if (vetor[k] < vetor[m])
                    m = k;
        }
        return m;
    }
    
    // retorna indice do maior dos descendentes entre os filhos e netos do elemento i    
    private int max_descendente (int i){
        int m = 0; //indice do maior elemento
        
        if (2*i <= posFinal) {
            // índice do primeiro filho
            m = 2*i; 
            
            // verifica se o segundo filho é maior do que o primeiro filho
            if (vetor[m+1] > vetor[m])
                m = m+1;
            
            // verifica se o maior está entre os netos
            for (int k=4*i; k<=4*i+3 && k <=posFinal; k++)
                if (vetor[k] > vetor[m])
                    m = k;
        }
        return m;
    }
    
    private boolean minimo(int i) {
        int logBase2 = ((int) (Math.log(i)/Math.log(2)));
        int nivel = logBase2 + 1;
        
        if (nivel % 2 != 0)
            return true;
        else
            return false;
    }

    private void trocar (int x, int y) {
        int temp;
        
        temp = vetor[x];
        vetor[x] = vetor[y];
        vetor[y] = temp;
    }
    
    
    public void printHeap() {
        if (posFinal ==0)
            System.out.println("Heap está vazio");
        else {
            for (int j=1; j<=posFinal; j++)
                System.out.print(vetor[j]+ " ");
        }
        System.out.println();
    }
}