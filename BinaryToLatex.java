/******************************************************************************
 *  Nome: Lucas Martins Próspero
 *  NUSP.: 15471925
 *  EP03 - MAC0323
 ******************************************************************************/

// Recebe a string binária que codifica a mensagem em Latex e a árvore de códigos, ambas em arquivos .txt 
// obs.: A leitura de txt é feita a partir de "In.java" de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne
/* Compilação: javac BinaryToLatex.java
 * Execução: java BinaryToLatex binary.txt tree.txt > latex.txt (gera um .txt correspondente à codificação em Latex da string binária)
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
public class BinaryToLatex {

    public static class Node{
        private String key;
        private Node left;
        private Node right;
        private Node(String key, Node left, Node right){
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

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

    // Leitura da árvore de códigos
    public static Node readTree(In tree){
        char c = tree.readChar();
        if(c == '1'){
            String key = readLatex(tree);
            return new Node(key,null,null);
        }
        Node x = readTree(tree);
        Node y = readTree(tree);
        Node z = new Node("",x,y);
        return z;
    }

    // Decodificação da string binária para formato Latex
    public static void decode(Node root, In binary){
        String s = "";
        while(!binary.isEmpty()){
            Node x = root;
            while(x.left != null){
                char c = binary.readChar();
                if(c == '0') x = x.left;
                else x = x.right;
            }
            s += x.key;
        }
        System.out.print(s);
    }


    public static void main(String[] args) {
        In binary = new In(args[0]);
        In tree = new In(args[1]);
        Node root = readTree(tree);
        decode(root,binary);
    }   
}
