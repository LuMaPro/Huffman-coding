/******************************************************************************
 *  Nome: Lucas Martins Próspero
 *  NUSP.: 15471925
 *  EP03 - MAC0323
 ******************************************************************************/

import java.util.PriorityQueue;

// Recebe um arquivo .txt (saída de MessageToLatex.java) que representa a mensagem original codificada em Latex
// obs.: A leitura de txt é feita a partir de "In.java" de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne
/* Compilação: javac LatexToTree.java
 * Execução: java LatexToTree latex.txt 0 > tree.txt (gera um .txt correspondente à árvore)
 *           java LatexToTree latex.txt 1 > binary.txt (gera um .txt correspondente à codificação binária da mensagem em Latex) 
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
public class LatexToTree {

    // Identificador de expressão em Latex
    public static String readLatex(In input){
        char c = input.readChar();
        String s = "";
        s += c;
        if(c != '.'){
            c = ' ';
            while(c != '$'){
                c = input.readChar();
                s += c;
            }
        }
        return s;
    }

    public static class Node implements Comparable<Node>{
        private String key;
        private int freq;
        private Node left;
        private Node right;

        public Node(String key, int freq, Node left, Node right){
            this.key = key;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node x){
            return Integer.compare(this.freq,x.freq); 
        }
    }

    // Construção da árvore de códigos
    public static Node buildTree(HashMap<String,Integer> freq){
        PriorityQueue<Node> minPQ = new PriorityQueue<Node>();
        for(String s : freq){
            Node x = new Node(s,freq.get(s),null,null);
            minPQ.add(x);
        }
        while(minPQ.size() > 1){
            Node x = minPQ.poll();
            Node y = minPQ.poll();
            Node z = new Node("",x.freq+y.freq,x,y);
            minPQ.add(z);
        }
        return minPQ.poll();
    }

    // Escrita da árvore de códigos
    public static String writeTree(Node x, String s){
        if(x.left == null){
            s += 1;
            s += x.key;
            return s;
        }
        s += 0;
        s = writeTree(x.left,s);
        s = writeTree(x.right,s);
        return s;
    }

    // Escrita do binário que codifica a mensagem em Latex
    public static void writeBinary(Node root){
        HashMap<String,String> code = new HashMap<String,String>();
        writeBinaryRec(root,"",code);
        In input = new In("latex.txt");
        String binary = "";
        while(!input.isEmpty()){
            String s = readLatex(input);
            binary += code.get(s);
        }
        System.out.print(binary);
    }

    private static void writeBinaryRec(Node x, String s, HashMap<String,String> code){
        if(x.left == null){
            code.put(x.key,s);
            return;
        }
        writeBinaryRec(x.left,s+"0",code);
        writeBinaryRec(x.right, s+"1",code);
        return;
    }


    public static void main(String[] args) {
        In input = new In(args[0]);
        int selector = Integer.parseInt(args[1]);
        HashMap<String,Integer> freq = new HashMap<String,Integer>();
        while(!input.isEmpty()){
            String s = readLatex(input);
            if(!freq.containsKey(s)){
                freq.put(s,0);
            }
            freq.change(s,freq.get(s)+1);
        }
        Node root = buildTree(freq);
        if(selector == 0) System.out.print(writeTree(root,""));
        if(selector == 1) writeBinary(root);
    }
}
