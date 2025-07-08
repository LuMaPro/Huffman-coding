/******************************************************************************
 *  Nome: Lucas Martins Próspero
 *  NUSP.: 15471925
 *  EP03 - MAC0323
 ******************************************************************************/

// Recebe uma tabela de símbolos e a mensagem a ser codificada para Latex, ambas em arquivos .txt
// obs.: A leitura de txt é feita a partir de "In.java" de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne
/* Compilação: javac MessageToLatex.java
 * Execução: java MessageToLatex message.txt symbol_table.txt > latex.txt (gera um .txt correspondente à codificação em Latex da mensagem original)
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
public class MessageToLatex{

    // Mapeamento de chaves e valores da tabela de símbolos
    public static HashMap<String,String> map_init(In st){
        HashMap<String,String> map = new HashMap<String,String>();
        while(!st.isEmpty()){
            String key = st.readString();
            String val = st.readString();
            map.put(key,val);
        }
        map.put(" ",".");
        return map;
    }

    public static void main(String[] args) {
        In input = new In(args[0]);
        In st = new In(args[1]);
        HashMap<String,String> map = map_init(st);
        while(!input.isEmpty()){
            char c = input.readChar();
            String s = "" + c;
            System.out.print(map.get(s));
        }
    }
}