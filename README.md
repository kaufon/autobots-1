## Pré-requisitos
Certifique-se de que você tenha as seguintes ferramentas instaladas no sistema:
- [Java JDK 21+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

## Passo 1: Clonar o repositório
Abra o terminal e execute o seguinte comando para clonar o repositório:
```bash
git clone https://github.com/kaufon/autobots-1.git
cd autobots-1
```

## Passo 2: Configurar o ambiente
Certifique-se de que o Java e o Maven estejam configurados corretamente. Para verificar, execute:
```bash
java -version
mvn -version
```

## Passo 3: Importar o projeto
Abra o projeto em sua IDE preferida, como [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/).

### Importar no IntelliJ IDEA
1. Selecione `File > Open`.
2. Navegue até o diretório do projeto e clique em `Open`.
3. Aguarde o IntelliJ importar o projeto Maven.

### Importar no Eclipse
1. Selecione `File > Import > Existing Maven Projects`.
2. Navegue até o diretório do projeto e clique em `Finish`.

## Passo 4: Executar o projeto
### Executar pela IDE
1. Localize a classe principal do projeto: `AutomanagerApplication`.
2. Clique com o botão direito na classe e selecione `Run`.

### Executar pelo terminal
1. Navegue até o diretório do projeto:
```bash
cd autobots-1
```
2. Execute o seguinte comando para rodar o projeto:
```bash
mvn spring-boot:run
```
