/******************************************************************************
 *  Nome: Lucas Martins Próspero
 *  NUSP.: 15471925
 *  EP03 - MAC0323
 ******************************************************************************/

import java.util.Iterator;
import java.util.ArrayList;

// Implementação de um HashMap por lista de encadeamento
// obs.: Como a tabela de símbolos possui um número fixo de 27 elementos (alfabeto + caractere de espaçamento), não se utilizou resize()
/*
 * Compilação: javac HashMap.java
*/

/******************************************************************************
 *  Ordem de arquivos:
 *  MessageToLatex.java
 *  LatexToTree.java
 *  BinaryToLatex.java
 *  LatexToMessage.java
 *  
 *  Lembre-se de compilar o HashMap (javac HashMap.java)
 ******************************************************************************/
public class HashMap<Key,Value> implements Iterable<Key>{

    private static class Node<Key,Value>{
        Key key;
        Value val;
        Node<Key,Value> next;
        private Node(Key key, Value val){
            this.key = key;
            this.val = val;
            next = null;
        }
    }

    private int size;                   // Tamanho do vetor correspondente ao HashMap
    private Node<Key,Value>[] map;      // Vetor correspondente ao HashMap

    // Construtor
    @SuppressWarnings("unchecked")
    public HashMap(){
        this.size = 27;
        map = (Node<Key,Value>[]) new Node[size];
        for(int i = 0; i < size; i++){
            map[i] = null;
        }
    }

    // Retorna o índice da chave no vetor
    private int index(Key key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    // Verifica a presença de uma chave
    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    // Adiciona um par (chave,valor) ao HashMap
    public void put(Key key, Value val){
        Node<Key,Value> x = new Node<Key,Value>(key,val);
        x.next = map[index(key)];
        map[index(key)] = x;
    }

    // Altera o valor de uma chave já existente
    public void change(Key key, Value val){
        Node<Key,Value> x = map[index(key)];
        while(x != null && !x.key.equals(key)){
            x = x.next;
        }
        if(x == null) throw new IllegalArgumentException("Chave não encontrada.");
        x.val = val;
    }

    // Retorna o valor da chave correspondente
    public Value get(Key key){
        Node<Key,Value> x = map[index(key)];
        while(x != null && !x.key.equals(key)){
            x = x.next;
        }
        if(x == null) return null;
        return x.val;
    }

    // Iterador sobre as chaves contidas no HashMap
    @Override
    public Iterator<Key> iterator() {
        ArrayList<Key> keys = new ArrayList<>();
        for (int i = 0; i < 27; i++) {
            for (Node<Key, Value> x = map[i]; x != null; x = x.next) {
                keys.add(x.key);
            }
        }
        return keys.iterator(); 
    }
}