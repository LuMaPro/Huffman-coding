/******************************************************************************
 *  Nome: Lucas Martins Próspero
 *  NUSP.: 15471925
 *  EP03 - MAC0323
 ******************************************************************************/

// Recebe a decodificação da string binária para formato Latex e uma tabela de símbolos, ambas em arquivos .txt
// obs.: A leitura de txt é feita a partir de "In.java" de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne
/* Compilação: javac LatexToMessage.java
 * Execução: java LatexToMessage latex.txt symbol_table.txt (printa a decodificação da string em Latex para o alfabeto original)
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
public class LatexToMessage {

    // Mapeamento de chaves e valores da tabela de símbolos
    public static HashMap<String,String> map_init(In st){
        HashMap<String,String> map = new HashMap<String,String>();
        while(!st.isEmpty()){
            String val = st.readString();
            String key = st.readString();
            map.put(key,val);
        }
        map.put("."," ");
        return map;
    }

    // Identificador de expressão em Latex
    public static String readLatex(In latex){
        char c = latex.readChar();
        String s = "";
        s += c;
        if(c != '.'){
            c = ' ';
            while(c != '$'){
                c = latex.readChar();
                s += c;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        In latex = new In(args[0]);
        In st = new In(args[1]);
        HashMap<String,String> map = map_init(st);
        while(!latex.isEmpty()){
            String s = readLatex(latex);
            System.out.print(map.get(s));
        }
        System.out.println();
    }
}
