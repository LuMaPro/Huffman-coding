# Huffman coding

## 🧾 Credits

The problem was proposed in the MAC0323 class.

https://uspdigital.usp.br/jupiterweb/obterDisciplina?sgldis=MAC0323

**Note**: Other credits are mentioned in the **Java** files.

## 🔍 General Overview

Given a message, this project, developed in Java, double encodes it, first in **LaTeX** symbols and next in **Huffman's code**, and then decodes it back to its original form.

## ⚙️ How to run
### 1) ♨️ HashMap.java
#### Compilation:
- **javac HashMap.java**

### 2) ♨️ MessageToLatex.java
#### Compilation:
- **javac MessageToLatex.java**
#### Execution:
- **java MessageToLatex message.txt symbol_table.txt > latex.txt**

### 3) ♨️ LatexToTree.java
#### Compilation:
- **javac LatexToTree.java**
#### Execution:
- **java LatexToTree latex.txt 0 > tree.txt**
- **java LatexToTree latex.txt 1 > binary.txt**

### 4) ♨️ BinaryToLatex.java
#### Compilation:
- **javac BinaryToLatex.java**
#### Execution:
- **java BinaryToLatex binary.txt tree.txt > latex.txt**

### 5) ♨️ LatexToMessage.java
#### Compilation:
- **javac LatexToMessage.java**
#### Execution:
- **java LatexToMessage latex.txt symbol_table.txt**

## 👤 Author

- Lucas Martins Próspero
