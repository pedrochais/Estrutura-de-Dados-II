package Sort;

import Generic.Generic;

public class MergeSortOtimizado extends Algoritmo implements Operacoes{
    private int tipoOrdenacao; //Crescente ou decrescente
    private long atr = 0; //Quantidade de atribuições
    private long co = 0; //Quantidade de comparações

    public Generic<?,?>[] ordenar(Generic<?, ?>[] vetor, int tipoOrdenacao){
        this.tipoOrdenacao = tipoOrdenacao;
        Generic<?,?>[] Temp = new Generic<?,?>[vetor.length];

        this.atr++;

        return MergeMain(vetor, Temp, 0, vetor.length-1);
    }

    public Generic<?,?>[] MergeMain(Generic<?,?>[] vetor, Generic<?,?>[] T, int esq, int dir){
        int meio;

        if(esq < dir){
            this.co++;
            if(dir - esq <= 15){ //Caso o tamanho do subvetor seja menor ou igual a 15 a ordenação será feita através do insertionSort
                this.co++;

                insertionSort(vetor, esq, dir+1);
            }else{
                this.atr++;
                meio = (esq + dir)/2; //Calcula o meio para dividir o vetor
                MergeMain(vetor, T, esq, meio); //Realiza a ordenação do vetor à esquerda
                MergeMain(vetor, T, meio + 1, dir); //Realiza a ordenação do vetor à direita
                Merge(vetor, T, esq, meio + 1, dir); //Junta os dois vetores
            }
        }

        return vetor;
    }

    public void Merge(Generic<?,?>[] vetor, Generic<?,?>[] T, int esqPos, int dirPos, int dirFim){
        int esqFim = dirPos - 1;
        int tempPos = esqPos;
        int numElem = dirFim - esqPos + 1;

        //Realiza a ordenação passando os valores de forma ordenada para o vetor temporário
        while(esqPos <= esqFim && dirPos <= dirFim){
            this.co += 2;
            if(this.tipoOrdenacao == 1){ //Condição para ordenação crescente
                if(vetor[esqPos].comparator(vetor[dirPos]) <= 0){
                    T[tempPos++] = vetor[esqPos++];

                    this.co++;
                    this.atr++;
                }else{
                    T[tempPos++] = vetor[dirPos++];

                    this.atr++;
                }
            }else{ //Condição para ordenação decrescente
                if(vetor[esqPos].comparator(vetor[dirPos]) >= 0){
                    T[tempPos++] = vetor[esqPos++];

                    this.co++;
                    this.atr++;
                }else{
                    T[tempPos++] = vetor[dirPos++];

                    this.atr++;
                }
            }

        }

        //Termina de copiar o resto do vetor
        while(esqPos <= esqFim) {
            T[tempPos++] = vetor[esqPos++];

            this.co++;
            this.atr++;
        }
        //Termina de copiar o resto do vetor
        while(dirPos <= dirFim) {
            T[tempPos++] = vetor[dirPos++];

            this.co++;
            this.atr++;
        }

        //Passa os valores do vetor temporário para o vetor principal
        for(int i = 0; i < numElem; i++, dirFim--){
            vetor[dirFim] = T[dirFim];

            this.co++;
            this.atr += 3;
        }

        this.atr += 3;
    }

    //InsertionSort modificado
    public void insertionSort(Generic<?,?> []vetor, int esq, int dir) {
        int i, j;
        Generic<?,?> key;

        if(this.tipoOrdenacao == 1){ //Condição para ordenação crescente
            for(j = esq; j < dir; j++){
                key = vetor[j];
                i = j - 1;
                while(i >= esq && vetor[i].comparator(key) > 0){
                    vetor[i+1] = vetor[i];
                    i--;

                    this.co += 2;
                    this.atr += 2;
                }
                vetor[i+1] = key;

                this.co++;
                this.atr += 5;
            }
        }else{ //Condição para ordenação decrescente
            for(j = esq; j < dir; j++){
                key = vetor[j];
                i = j - 1;
                while(i >= esq && vetor[i].comparator(key) < 0){
                    vetor[i+1] = vetor[i];
                    i--;

                    this.co += 2;
                    this.atr += 2;
                }
                vetor[i+1] = key;

                this.co++;
                this.atr += 5;
            }
        }
    }

    @Override
    public long getAtr() {
        return this.atr;
    }

    @Override
    public long getComp() {
        return this.co;
    }

    //Reinicia a quantidade de atribuições e de comparações
    public void reiniciar(){
        this.atr = 0;
        this.co = 0;
    }
}
